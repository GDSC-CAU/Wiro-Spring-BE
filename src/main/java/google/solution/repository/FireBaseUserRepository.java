package google.solution.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import google.solution.domain.User;
import google.solution.dto.GetUserRes;
import google.solution.dto.UpdateUserReq;
import google.solution.dto.UpdateUserRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class FireBaseUserRepository implements UserRepository{

    public static final String COLLECTION_NAME = "user";
    public static final String USER_EMAIL = "email";
    public static final String USER_NICKNAME = "nickname";
    public static final String USER_BLOOD = "blood";
    public static final String USER_DISEASE = "disease";
    public static final String USER_MEDICINE = "medicine";
    public static final String USER_ID = "id";

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
        ApiFuture<WriteResult> future = docRef.update(USER_EMAIL, updateUserReq.getEmail(), USER_NICKNAME, updateUserReq.getNickname(),
                USER_DISEASE, updateUserReq.getDisease(), USER_BLOOD, updateUserReq.getBlood(),
                USER_MEDICINE, updateUserReq.getMedicine(), USER_ID, updateUserReq.getId());
        UpdateUserRes updateUserRes = new UpdateUserRes(future.get().getUpdateTime().toString());
        return updateUserRes;
    }

    @Override
    public String saveUser(User user) throws Exception{
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<com.google.cloud.firestore.WriteResult> apiFuture =
                firestore.collection(COLLECTION_NAME).document(user.getUsername()).set(user);
        return apiFuture.get().getUpdateTime().toString();
    }
}
