package google.solution.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.cloud.firestore.Query.Direction;
import com.google.firebase.cloud.FirestoreClient;
import google.solution.domain.Mission;
import google.solution.domain.Score;
import google.solution.domain.SuccessMission;
import google.solution.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
@Slf4j
public class FirebaseMissionRepository implements MissionRepository {
    public static final String COLLECTION_NAME = "mission";
    public static final String USER_COLLECTION = "user";
    public static final int CATEGORY_LENGTH = 7;
    public static final String MISSION = "1";
    public static final String CHECK = "2";
    public static final String SCORE = "score";

    @Override
    public Mission getMissionInfo(String code) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        String id = Character.toString(code.charAt(0));
        String category = Character.toString(code.charAt(1));
        DocumentReference document = db.collection(COLLECTION_NAME).document(id).collection(category).document(code);
        ApiFuture<DocumentSnapshot> apiFuture = document.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        Mission mission = null;
        if(documentSnapshot.exists()){
            mission = documentSnapshot.toObject(Mission.class);
            return mission;
        }
        else{
            return null;
        }
    }

    @Override
    public List<SuccessMission> getSuccessMissions(MissionCompleteReq missionCompleteReq, String userId) throws Exception {
        List<SuccessMission> successMissions = new ArrayList<>();
        String id = Character.toString(missionCompleteReq.getCode().charAt(0));
        String category = Character.toString(missionCompleteReq.getCode().charAt(1));
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference missionCategory = db.collection(USER_COLLECTION).document(userId).collection(COLLECTION_NAME).document(id).collection(category);
        Query query = missionCategory.orderBy("updateTime", Direction.DESCENDING).limit(4);
        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> missions = future.get().getDocuments();
        for (QueryDocumentSnapshot mission : missions) {
            SuccessMission successMission = mission.toObject(SuccessMission.class);
            successMissions.add(successMission);
        }
        return successMissions;
    }

    @Override
    public void saveScore(String code, double average, String userId) throws Exception {
        String id = Character.toString(code.charAt(0));
        int category = (code.charAt(1)) - '0';
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference collection = db.collection(USER_COLLECTION).document(userId).collection(SCORE);
        DocumentReference docRef = collection.document(id);
        ApiFuture<DocumentSnapshot> future  = docRef.get();
        DocumentSnapshot document = future.get();
        if (document.getData() == null) {
            List<Double> scores = new ArrayList<Double>();
            for (int i = 0; i < 7; i++) {
                scores.add(0.0);
            }
            scores.set(category - 1, average);
            // 저장하기
            Map<String, Object> docData = new HashMap<>();
            docData.put("scores", scores);
            collection.document(id).set(docData);
        } else {
            Score score = document.toObject(Score.class);
            List<Double> scores = score.getScores();
            scores.set(category - 1, average);
            // 저장하기
            docRef.update("scores", scores);
        }
    }

    @Override
    public MissionCompleteRes saveMissions(List<SuccessMission> missions, String userId) throws Exception {
        String code = missions.get(0).getCode();
        Firestore db = FirestoreClient.getFirestore();
        String id = Character.toString(code.charAt(0));
        String category = Character.toString(code.charAt(1));
        CollectionReference collection = db.collection(USER_COLLECTION).document(userId).
                collection(COLLECTION_NAME).document(id).collection(category);
        for (int i = 0; i < missions.size(); i++) {
            Map<String, Object> docData = new HashMap<>();
            docData.put("code", missions.get(i).getCode());
            docData.put("score", missions.get(i).getScore());
            docData.put("updateTime", missions.get(i).getUpdateTime());
            collection.document(missions.get(i).getCode()).set(docData);
        }
        return new MissionCompleteRes();
    }

    @Override
    public GetMissionHistoryRes getMissionHistory(String userId) throws Exception {
        List<SuccessMission> missionHistory = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference document = db.collection(USER_COLLECTION).document(userId).
                collection(COLLECTION_NAME).document(MISSION);

        for (int i = 0; i < CATEGORY_LENGTH; i++) {
            CollectionReference collection = document.collection(Integer.toString(i + 1));
            Query query = collection.orderBy("updateTime", Direction.DESCENDING).limit(1);
            ApiFuture<QuerySnapshot> future = query.get();
            List<QueryDocumentSnapshot> missions = future.get().getDocuments();

            for (QueryDocumentSnapshot mission : missions) {
                SuccessMission successMission = mission.toObject(SuccessMission.class);
                missionHistory.add(successMission);
            }
        }
        return new GetMissionHistoryRes(missionHistory);
    }

}
