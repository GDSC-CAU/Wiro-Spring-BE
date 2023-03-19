package google.solution.repository;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import google.solution.domain.Message;
import google.solution.dto.SendMessageRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class FireBaseChatRepository implements ChatRepository{

    public static final String USER_COLLECTION = "user";
    public static final String MESSAGE_COLLECTION = "message";

    @Override
    public SendMessageRes sendMessage(String id, Message message) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference collectionReference = db.collection(USER_COLLECTION).document(id).collection(MESSAGE_COLLECTION);
        collectionReference.add(message);
        return new SendMessageRes();
    }
}
