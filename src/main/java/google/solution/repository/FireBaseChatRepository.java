package google.solution.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import google.solution.domain.Message;
import google.solution.dto.SendMessageRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@Slf4j
public class FireBaseChatRepository implements ChatRepository{

    public static final String COLLECTION_NAME = "user";

    @Override
    public SendMessageRes sendMessage(String id, Message message) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference sourceIdCollectionRef = db.collection(COLLECTION_NAME).document(id).collection(message.getDestinationId());
        sourceIdCollectionRef.add(message);
        String destinationId = message.getDestinationId();
        CollectionReference destinationIdCollectionRef = db.collection(COLLECTION_NAME).document(destinationId).collection(message.getSourceId());
        destinationIdCollectionRef.add(message);
        return new SendMessageRes();
    }
}
