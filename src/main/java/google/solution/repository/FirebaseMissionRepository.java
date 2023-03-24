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
import java.util.List;


@Repository
@Slf4j
public class FirebaseMissionRepository implements MissionRepository {
    public static final String COLLECTION_NAME = "mission";
    public static final String USER_COLLECTION = "user";
    public static final String MISSION = "mission";
    public static final String CHECK = "check";
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
        DocumentReference docRef = db.collection(USER_COLLECTION).document(userId).
                collection(SCORE).document(id);
        ApiFuture<DocumentSnapshot> future  = docRef.get();
        DocumentSnapshot document = future.get();
        Score score = document.toObject(Score.class);
        List<Double> scores = score.getScore();
        for (Double aDouble : scores) {
            System.out.println("aDouble = " + aDouble);
        }
        scores.set(category - 1, average);
        //
        score.setScore(scores);
        // 저장하기
        docRef.update(SCORE, score);
    }

    @Override
    public MissionCompleteRes saveMissions(List<SuccessMission> missions, String userId) throws Exception {
        return null;
    }
}
