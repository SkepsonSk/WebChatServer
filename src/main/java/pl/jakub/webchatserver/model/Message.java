package pl.jakub.webchatserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * Represents a message delivered by sender
 * to be forwarded to all other subscribers in the
 * room.
 *
 * @see User
 *
 * @author Jakub Zelmanowicz
 */
public class Message {

    /**
     * ID of the sending user.
     */
    private final UUID sender;
    private final String content;

    public Message(@JsonProperty("sender") UUID sender,
                   @JsonProperty("content") String content) {

        this.sender = sender;
        this.content = content;
    }

    public UUID getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }
}
