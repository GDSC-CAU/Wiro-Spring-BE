package google.solution.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import google.solution.domain.Message;
import google.solution.domain.User;
import google.solution.dto.GetChatContentRes;
import google.solution.dto.GetChatMessageRes;
import google.solution.dto.GetChatRoomRes;
import google.solution.dto.SendMessageRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class FireBaseChatRepository implements ChatRepository {

    public static final String COLLECTION_NAME = "user";
    public static final String NICKNAME_FIELD = "nickname";

    @Override
    public SendMessageRes sendMessage(String id, Message message) throws Exception {
        String nickname = findUserNickname(id);
        String destinationId = findUserId(message.getDestinationNickname());
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference sourceIdCollectionRef = db.collection(COLLECTION_NAME).document(id).collection(destinationId);
        sourceIdCollectionRef.add(message);
        CollectionReference destinationIdCollectionRef = db.collection(COLLECTION_NAME).document(destinationId).collection(nickname);
        destinationIdCollectionRef.add(message);
        return new SendMessageRes();
    }

    private String findUserId(String nickname) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        Query query = db.collection(COLLECTION_NAME).whereEqualTo(NICKNAME_FIELD, nickname);
        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> users = future.get().getDocuments();
        return users.get(0).getId();
    }

    private String findUserNickname(String id) throws Exception {
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                db.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> apiFuture = documentReference.get();
        DocumentSnapshot documentSnapshot = apiFuture.get();
        User user = null;
        if(documentSnapshot.exists()){
            user = documentSnapshot.toObject(User.class);
            return user.getNickname();
        }
        else{
            return null;
        }
    }

    @Override
    public GetChatRoomRes getChatRooms(String id) throws Exception {
        List<String> getChatRoomRes = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        Iterable<CollectionReference> collections =
                db.collection(COLLECTION_NAME).document(id).listCollections();
        for (CollectionReference collRef : collections) {
            getChatRoomRes.add(collRef.getId());
        }
        return new GetChatRoomRes(getChatRoomRes);
    }

    @Override
    public List<GetChatContentRes> getChatContent(String roomId, String id) throws Exception {
        List<GetChatContentRes> chatContents = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        CollectionReference chatContent = db.collection(COLLECTION_NAME).document(id).collection(roomId);
        Query query = chatContent.orderBy("updateTime");
        ApiFuture<QuerySnapshot> future = query.get();
        List<QueryDocumentSnapshot> contents = future.get().getDocuments();
        for (QueryDocumentSnapshot content : contents) {
            GetChatContentRes getChatContentRes = content.toObject(GetChatContentRes.class);
            chatContents.add(getChatContentRes);
        }

        return chatContents;
    }

    @Override
    public List<GetChatMessageRes> getChatMessages(String id) throws Exception {
        List<GetChatMessageRes> chatMessages = new ArrayList<>();
        Firestore db = FirestoreClient.getFirestore();
        DocumentReference user = db.collection(COLLECTION_NAME).document(id);
        Iterable<CollectionReference> collections = user.listCollections();

        for (CollectionReference collRef : collections) {
            CollectionReference chatMessage = user.collection(collRef.getId());
            Query query = chatMessage.orderBy("updateTime");
            ApiFuture<QuerySnapshot> future = query.get();
            List<QueryDocumentSnapshot> messages = future.get().getDocuments();

            for (QueryDocumentSnapshot message : messages) {
                GetChatMessageRes getChatMessageRes = message.toObject(GetChatMessageRes.class);
                chatMessages.add(getChatMessageRes);
            }
        }
        return chatMessages;
    }


}
