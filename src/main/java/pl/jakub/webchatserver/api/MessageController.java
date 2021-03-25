package pl.jakub.webchatserver.api;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.jakub.webchatserver.fcm.FirebaseMessagingService;
import pl.jakub.webchatserver.model.Message;
import pl.jakub.webchatserver.model.Room;
import pl.jakub.webchatserver.model.User;
import pl.jakub.webchatserver.service.RoomService;
import pl.jakub.webchatserver.service.UserService;

import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides an HTTP gateway for
 * sending messages.
 *
 * @author Jakub Zelmanowicz
 */
@RequestMapping("api/v1/message")
@RestController
public class MessageController {

    private final Logger logger
            = Logger.getLogger(getClass().getName());

    private final UserService userService;
    private final RoomService roomService;
    private final FirebaseMessagingService firebaseMessagingService;

    public MessageController(@Autowired UserService userService,
                             @Autowired RoomService roomService,
                             @Autowired FirebaseMessagingService firebaseMessagingService) {

        this.userService = userService;
        this.roomService = roomService;
        this.firebaseMessagingService = firebaseMessagingService;
    }

    /**
     * Creates a new message model.
     * Handles sending a message to other
     * room users.
     *
     * @param message - message to send.
     *
     * @return boolean indicating whether a message was sent.
     */
    @PostMapping
    public boolean addMessage(@RequestBody Message message) {

        UUID userId = message.getSender();
        Optional<Room> roomOpt = roomService.selectRoomByUser(userId);

        logger.log(Level.INFO, "Message: " + message.getContent());

        User sender = userService.select(message.getSender()).get();

        if (roomOpt.isPresent()) {

            Room room = roomOpt.get();
            logger.log(Level.INFO, "User belongs to the room: " + room.getId());

            room.getUsers()
                    .stream()
                    .filter( uuid -> !uuid.equals(sender.getId()) )
                    .forEach( uuid -> {

                logger.log(Level.INFO, "Resolving: " + uuid);

                Optional<User> userOpt = userService.select(uuid);
                userOpt.ifPresent(user -> {

                    logger.log(Level.INFO, "Sending to: " + uuid);

                    try {

                        firebaseMessagingService.sendNotification(user.getToken(), sender.getName(), message.getContent(), room.getId().toString());

                    } catch (FirebaseMessagingException e) {
                        e.printStackTrace();
                    }

                });

            });

            return true;

        }
        else
            return false;

    }

}
