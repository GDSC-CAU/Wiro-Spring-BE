package google.solution.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.cloud.firestore.Query.Direction;
import com.google.firebase.cloud.FirestoreClient;
import google.solution.domain.Mission;
import google.solution.domain.Score;
import google.solution.domain.SuccessMission;
import google.solution.domain.User;
import google.solution.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.impl.auth.GGSSchemeBase;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
@Slf4j
public class FirebaseMissionRepository implements MissionRepository {
    public static final String COLLECTION_NAME = "mission";
    public static final String USER_COLLECTION = "user";
    public static final String RECOMMEND_MISSION = "recommend_mission";
    public static final String RECOMMEND_CHECKLIST = "recommend_checklist";
    public static final int CATEGORY_LENGTH = 7;
    public static final String MISSION = "1";
    public static final String CHECKLIST = "2";
    public static final String SCORE = "score";

    @Override
    public Mission getMissionInfo(String userId, String code) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        String id = Character.toString(code.charAt(0));
        String category = Character.toString(code.charAt(1));
        DocumentReference document = db.collection(COLLECTION_NAME).document(id).collection(category).document(code);
        ApiFuture<DocumentSnapshot> apiFuture = document.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        Mission mission = null;
        if(documentSnapshot.exists()){
            mission = documentSnapshot.toObject(Mission.class);
            SaveRecommendMissionReq recommendMission = SaveRecommendMissionReq.createSaveRecommendMissionReq(code, mission.getContent());
//            saveRecommendMission(userId, code, recommendMission);
            return mission;
        }
        else{
            return null;
        }
    }

    private void saveRecommendMission(String id, String code, SaveRecommendMissionReq mission) {
        Firestore db = FirestoreClient.getFirestore();
        String type = Character.toString(code.charAt(0));
        if (type.equals(MISSION)) {
            db.collection(USER_COLLECTION).document(id).collection(RECOMMEND_MISSION).document(code).set(mission);

        } else {
            db.collection(USER_COLLECTION).document(id).collection(RECOMMEND_CHECKLIST).document(code).set(mission);
        }
    }

    @Override
    public List<SuccessMission> getSuccessMissions(MissionCompleteReq missionCompleteReq, String userId) throws Exception {
        List<SuccessMission> successMissions = new ArrayList<>();
        String id = Character.toString(missionCompleteReq.getCode().charAt(0));
        String category = Character.toString(missionCompleteReq.getCode().charAt(1));
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference missionCategory = db.collection(USER_COLLECTION).document(userId).collection(COLLECTION_NAME).document(id).collection(category);
        Query query = missionCategory.orderBy("updateTime", Direction.DESCENDING).limit(5);
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
        DocumentReference docRef = collection.document(userId);
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
    public void saveOneMission(MissionCompleteReq missionCompleteReq, String userId) throws Exception {
        String code = missionCompleteReq.getCode();
        Firestore db = FirestoreClient.getFirestore();
        String id = Character.toString(code.charAt(0));
        String category = Character.toString(code.charAt(1));
        CollectionReference collection = db.collection(USER_COLLECTION).document(userId).
                collection(COLLECTION_NAME).document(id).collection(category);
        collection.document(code).set(missionCompleteReq);
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

    @Override
    public GetCheckListHistoryRes getCheckListHistory(String userId) throws Exception {
        List<SuccessMission> checkListHistory = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference document = db.collection(USER_COLLECTION).document(userId).
                collection(COLLECTION_NAME).document(CHECKLIST);

        for (int i = 0; i < CATEGORY_LENGTH; i++) {
            CollectionReference collection = document.collection(Integer.toString(i + 1));
            Query query = collection.orderBy("updateTime", Direction.DESCENDING).limit(1);
            ApiFuture<QuerySnapshot> future = query.get();
            List<QueryDocumentSnapshot> missions = future.get().getDocuments();

            for (QueryDocumentSnapshot mission : missions) {
                SuccessMission successMission = mission.toObject(SuccessMission.class);
                checkListHistory.add(successMission);
            }
        }
        return new GetCheckListHistoryRes(checkListHistory);
    }

    @Override
    public void deleteRecommendMission(String id, String code) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        String type = Character.toString(code.charAt(0));
        if (type.equals(MISSION)) {
            db.collection(USER_COLLECTION).document(id).collection(RECOMMEND_MISSION).document(code).delete();

        } else {
            db.collection(USER_COLLECTION).document(id).collection(RECOMMEND_CHECKLIST).document(code).delete();
        }
    }

    public List<GetRecommendMissionRes> getRecommendMissionAtMissionCollection(String userId) throws Exception {
        ArrayList<GetRecommendMissionRes> recommendMissions = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReferences = db.collection(USER_COLLECTION).document(userId).collection(RECOMMEND_MISSION).listDocuments();
        for (DocumentReference documentReference : documentReferences) {
            GetRecommendMissionRes recommendMission = documentReference.get().get().toObject(GetRecommendMissionRes.class);
            if (recommendMission.getCode().equals("-1")) {
                continue;
            }
            recommendMissions.add(recommendMission);
        }
        return recommendMissions;
    }
@Override
public List<GetRecommendMissionRes> getRecommendMission(String userId) throws Exception {
    ArrayList<GetRecommendMissionRes> recommendMissions = new ArrayList<>();
    Firestore db = FirestoreClient.getFirestore();
    ApiFuture<DocumentSnapshot> documentSnapshotApiFuture = db.collection(USER_COLLECTION).document(userId).get();
    User user = documentSnapshotApiFuture.get().toObject(User.class);
    Map<String, String> recommendedMission = user.getRecommended_mission();
    Set<String> keys = recommendedMission.keySet();
    List<String> keyList = new ArrayList<>(keys);
    Collection<String> values = recommendedMission.values();
    List<String> valueList = new ArrayList<>(values);
    for (int i = 0; i < keys.size(); i++) {
        GetRecommendMissionRes getRecommendMissionRes = new GetRecommendMissionRes();
        getRecommendMissionRes.setCode(keyList.get(i));
        getRecommendMissionRes.setContent(valueList.get(i));
        recommendMissions.add(getRecommendMissionRes);
    }
    return recommendMissions;
}

    public List<GetRecommendChecklistRes> getRecommendChecklistAtChecklistCollection(String userId) throws Exception {
        ArrayList<GetRecommendChecklistRes> recommendChecklists = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        Iterable<DocumentReference> documentReferences = db.collection(USER_COLLECTION).document(userId).collection(RECOMMEND_CHECKLIST).listDocuments();
        for (DocumentReference documentReference : documentReferences) {
            GetRecommendChecklistRes recommendChecklist = documentReference.get().get().toObject(GetRecommendChecklistRes.class);
            if (recommendChecklist.getCode().equals("-1")) {
                continue;
            }
            recommendChecklists.add(recommendChecklist);
        }
        return recommendChecklists;
    }

    @Override
    public List<GetRecommendChecklistRes> getRecommendChecklist(String userId) throws Exception {
        ArrayList<GetRecommendChecklistRes> recommendMissions = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<DocumentSnapshot> documentSnapshotApiFuture = db.collection(USER_COLLECTION).document(userId).get();
        User user = documentSnapshotApiFuture.get().toObject(User.class);
        Map<String, String> recommendedMission = user.getRecommended_checklist();
        Set<String> keys = recommendedMission.keySet();
        List<String> keyList = new ArrayList<>(keys);
        Collection<String> values = recommendedMission.values();
        List<String> valueList = new ArrayList<>(values);
        for (int i = 0; i < keys.size(); i++) {
            GetRecommendChecklistRes getRecommendChecklistRes = new GetRecommendChecklistRes();
            getRecommendChecklistRes.setCode(keyList.get(i));
            getRecommendChecklistRes.setContent(valueList.get(i));
            recommendMissions.add(getRecommendChecklistRes);
        }
        return recommendMissions;
    }

}
