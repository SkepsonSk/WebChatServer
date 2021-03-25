package pl.jakub.webchatserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

/**
 * Represents a chatting user.
 *
 * @see Room
 *
 * @author Jakub Zelmanowicz
 */
public class User {

    private final UUID id;
    private final String name;
    private final String token;

    public User(@JsonProperty("id") UUID id,
                @JsonProperty("name") String name,
                @JsonProperty("token") String token) {

        this.id = id;
        this.name = name;
        this.token = token;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }
}
