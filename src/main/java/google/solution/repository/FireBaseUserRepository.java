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
    
    @Override
    public GetUserRes getUser(String id) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                db.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        User user = null;
        if(documentSnapshot.exists()){
            user = documentSnapshot.toObject(User.class);
            GetUserRes getUserRes = GetUserRes.userToGetUserRes(user);
            return getUserRes;
        }
        else{
            return null;
        }
    }

    @Override
    public UpdateUserRes updateUser(UpdateUserReq user) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(user.getId());
        ApiFuture<WriteResult> future = docRef.update(USER_EMAIL, user.getEmail(), USER_NICKNAME, user.getNickname());
        UpdateUserRes updateUserRes = new UpdateUserRes(future.get().getUpdateTime().toString());
        return updateUserRes;
    }

}
