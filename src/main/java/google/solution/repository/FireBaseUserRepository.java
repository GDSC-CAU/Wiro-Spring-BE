package google.solution.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import google.solution.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class FireBaseUserRepository implements UserRepository{

    public static final String COLLECTION_NAME = "user";

    @Override
    public GetUserRes getUser(String id) throws Exception {
        
    }

}
