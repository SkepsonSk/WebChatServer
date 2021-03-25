package pl.jakub.webchatserver.fcm;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service providing messaging between
 * clients using FCM.
 *
 * @author Jakub Zelmanowicz
 */
@Service
public class FirebaseMessagingService {

    private final FirebaseMessaging firebaseMessaging;

    @Autowired
    public FirebaseMessagingService(FirebaseMessaging firebaseMessaging) {
        this.firebaseMessaging = firebaseMessaging;
    }

    /**
     * Sends a notification to users
     * in the specific room.
     *
     * @param token - token (id FCM) of the receiver,
     * @param sender - name of the sender of the message.
     * @param content - content of the message
     * @param roomId - id of the room we want to push the notification to.
     * @return id of the pushed notification.
     * @throws FirebaseMessagingException - threw on any FCM error.
     */
    public String sendNotification(String token,
                                   String sender,
                                   String content,
                                   String roomId)
            throws FirebaseMessagingException {

        Notification notification = Notification
                .builder()
                .setTitle(sender)
                .setBody(content)
                .build();

        Message message = Message
                .builder()
                .setToken(token)
                .setNotification(notification)
                .putData("room", roomId)
                .build();

        try {

            return firebaseMessaging.send(message);

        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
        }

        return firebaseMessaging.send(message);

    }

}
