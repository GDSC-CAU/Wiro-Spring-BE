package google.solution.repository;

import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import google.solution.domain.Message;
import google.solution.dto.GetChatRoomRes;
import google.solution.dto.SendMessageRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<GetChatRoomRes> getChatRooms(String id) throws Exception {
        List<GetChatRoomRes> list = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        Iterable<CollectionReference> collections =
                db.collection(COLLECTION_NAME).document(id).listCollections();
        for (CollectionReference collRef : collections) {
            GetChatRoomRes chatRoom = new GetChatRoomRes(collRef.getId());
            list.add(chatRoom);
        }
        return list;
    }

}
