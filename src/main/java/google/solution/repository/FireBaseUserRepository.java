package google.solution.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import google.solution.domain.User;
import google.solution.dto.UpdateUserReq;
import google.solution.dto.UpdateUserRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Slf4j
public class FireBaseUserRepository implements UserRepository{

    public static final String COLLECTION_NAME = "user";
    public static final String USER_NICKNAME = "nickname";
    public static final String USER_BLOOD = "blood";
    public static final String USER_DISEASE = "disease";
    public static final String USER_ID = "id";
    public static final String USER_MEDICINE = "medicine";
    public static final String RECOMMEND_MISSION = "recommend_mission";
    public static final String RECOMMEND_CHECKLIST = "recommend_checklist";
    public static final String CODE = "code";
    public static final String CONTENT = "content";
    public static final String SCORE = "score";



    @Override
    public User getUser(String id) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                db.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        User user = null;
        if(documentSnapshot.exists()){
            user = documentSnapshot.toObject(User.class);
            return user;
        }
        else{
            return null;
        }
    }

    @Override
    public UpdateUserRes updateUser(String id, UpdateUserReq updateUserReq) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(id);
        Map<String, Object> docData = new HashMap<>();
        if (updateUserReq.getNickname() != null) {
            docData.put(USER_NICKNAME, updateUserReq.getNickname());
        }
        if (updateUserReq.getId() != null) {
            docData.put(USER_ID, updateUserReq.getId());
        }
        if (updateUserReq.getDisease() != null) {
            docData.put(USER_DISEASE, updateUserReq.getDisease());
        }
        if (updateUserReq.getBlood() != null) {
            docData.put(USER_BLOOD, updateUserReq.getBlood());
        }
        if (updateUserReq.getMedicine() != null) {
            docData.put(USER_MEDICINE, updateUserReq.getMedicine());
        }
        ApiFuture<WriteResult> future = docRef.update(docData);
        UpdateUserRes updateUserRes = new UpdateUserRes(future.get().getUpdateTime().toString());
        return updateUserRes;
    }

    @Override
    public String saveUser(User user) throws Exception{
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<com.google.cloud.firestore.WriteResult> apiFuture =
                firestore.collection(COLLECTION_NAME).document(user.getUsername()).set(user);
        Map<String, Object> data = new HashMap<>();
        data.put(CODE, "-1");
        data.put(CONTENT, "-1");
        firestore.collection(COLLECTION_NAME).document(user.getUsername()).collection(RECOMMEND_MISSION).document("-1").set(data);
        firestore.collection(COLLECTION_NAME).document(user.getUsername()).collection(RECOMMEND_CHECKLIST).document("-1").set(data);
        Map<String, Object> score = new HashMap<>();
        List<Integer> scores = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            scores.add(0);
        }
        score.put("scores", scores);
        firestore.collection(COLLECTION_NAME).document(user.getUsername()).collection(SCORE).document(RECOMMEND_MISSION).set(score);
        firestore.collection(COLLECTION_NAME).document(user.getUsername()).collection(SCORE).document(RECOMMEND_CHECKLIST).set(score);
        return apiFuture.get().getUpdateTime().toString();
    }
}
