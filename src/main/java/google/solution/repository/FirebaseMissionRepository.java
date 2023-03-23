package google.solution.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import google.solution.dto.GetMissionInfoRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;


@Repository
@Slf4j
public class FirebaseMissionRepository {
    public static final String COLLECTION_NAME = "mission";
    public static final String MISSION = "mission";
    public static final String CHECK = "check";

    @Override
    public GetMissionInfoRes getMissionInfo(String code) throws Exception {
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
}
