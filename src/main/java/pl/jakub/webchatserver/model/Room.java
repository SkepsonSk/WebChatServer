package pl.jakub.webchatserver.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * A room is a group of
 * users chatting together.
 *
 * @see User
 *
 * @author Jakub Zelmanowicz
 */
public class Room {

    private final UUID id;
    private final String name;
    private final List<UUID> users;

    public Room(@JsonProperty("id") UUID id,
                @JsonProperty("name") String name) {

        this.id = id;
        this.name = name;
        this.users = new ArrayList<>();
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<UUID> getUsers() {
        return users;
    }
}
