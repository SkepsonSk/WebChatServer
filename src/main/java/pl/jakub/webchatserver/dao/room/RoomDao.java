package pl.jakub.webchatserver.dao.room;

import pl.jakub.webchatserver.model.Room;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Strategical interface for room data.
 * Provides an easy migration from any datasource.
 *
 * @author Jakub Zelmanowicz
 */
public interface RoomDao {

    /**
     * Inserts a new room to the datasource.
     *
     * @param id - id of the new room,
     * @param room - room body data.
     * @return id of the newly created room.
     */
    UUID insertRoom(UUID id, Room room);

    /**
     * Inserts a new room without provided ID
     * to the datasource.
     *
     * @param room - room body data.
     * @return id of the newly created room.
     */
    default UUID insertRoom(Room room) {
        UUID id = UUID.randomUUID();
        return insertRoom(id, room);
    }

    /**
     * Gets all the rooms.
     *
     * @return list of the rooms.
     */
    List<Room> selectAll();

    /**
     * Selects a specific room under
     * given id.
     *
     * @param id - id of the room.
     * @return optional room. It may not be found.
     */
    Optional<Room> select(UUID id);

    /**
     * Selects a specific room under
     * given name.
     *
     * @param name - name of the room.
     * @return optional room. It may not be found.
     */
    Optional<Room> select(String name);

    /**
     * Selects a specific with
     * a specific user belonging to it.
     *
     * @param user - id of the user.
     * @return optional room. It may not be found.
     */
    Optional<Room> selectRoomByUser(UUID user);

    /**
     * Adds a user to the room.
     *
     * @param id - id of the room,
     * @param userId - id of the user we want to add.
     * @return -1 if room was not found, 1 if operation
     * was successful.
     */
    int updateRoomByUser(UUID id, UUID userId);

    /**
     * Removes a user from the room.
     *
     * @param id - id of the room,
     * @param userId - id of the user we want to remove.
     * @return -1 if room was not found, 1 if operation
     * was successful.
     */
    int removeUserFromRoom(UUID id, UUID userId);

}
