package google.solution.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import google.solution.domain.User;
import google.solution.dto.GetUserRes;
import google.solution.dto.UpdateUserRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class FireBaseUserRepository implements UserRepository{

    public static final String COLLECTION_NAME = "user";

    @Override
    public GetUserRes getUser(String id) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                firestore.collection(COLLECTION_NAME).document(id);
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
    public UpdateUserRes updateUser(User user) throws Exception {
        Firestore firestore = FirestoreClient.getFirestore();
        ApiFuture<com.google.cloud.firestore.WriteResult> apiFuture
                = firestore.collection(COLLECTION_NAME).document(user.getId()).set(user);
        UpdateUserRes updateUserRes = new UpdateUserRes("업데이트 성공", apiFuture.get().getUpdateTime().toString());
        return updateUserRes;
    }

}
