package google.solution.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import google.solution.domain.Mission;
import google.solution.dto.GetChatContentRes;
import google.solution.dto.GetMissionInfoRes;
import google.solution.dto.MissionCompleteReq;
import google.solution.dto.MissionCompleteRes;
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
    public List<MissionCompleteReq> getSuccessMissions(MissionCompleteReq missionCompleteReq, String userId) throws Exception {
        List<MissionCompleteReq> successMissions = new ArrayList<>();
        String id = Character.toString(missionCompleteReq.getCode().charAt(0));
        String category = Character.toString(missionCompleteReq.getCode().charAt(1));
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference missionCategory = db.collection(USER_COLLECTION).document(userId).collection(COLLECTION_NAME).document(id).collection(category);
        Query query = missionCategory.orderBy("updateTime").limit(4);
        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> missions = future.get().getDocuments();
        for (QueryDocumentSnapshot mission : missions) {
            MissionCompleteReq successMission = mission.toObject(MissionCompleteReq.class);
            successMissions.add(successMission);
        }

        return successMissions;
    }

    @Override
    public void saveScore(double score, String userId) throws Exception {
    }

    @Override
    public MissionCompleteRes saveMissions(List<MissionCompleteReq> missions, String userId) throws Exception {
        return null;
    }
}
