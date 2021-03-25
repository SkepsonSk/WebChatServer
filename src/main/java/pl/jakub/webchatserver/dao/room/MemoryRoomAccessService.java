package pl.jakub.webchatserver.dao.room;

import org.springframework.stereotype.Repository;
import pl.jakub.webchatserver.model.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Provides rooms data from DB.
 *
 * @author Jakub Zelmanowicz
 */
@Repository("memoryRoomDao")
public class MemoryRoomAccessService implements RoomDao {

    /**
     * Represents an actual database.
     */
    private final static List<Room> DB = new ArrayList<>();

    /**
     * Creates a new room.
     *
     * @param id - id of the room.
     * @param room - actual room data.
     * @return id of a new room.
     */
    @Override
    public UUID insertRoom(UUID id, Room room) {
        DB.add(new Room(id, room.getName()));
        return id;
    }

    /**
     * Gets all the rooms.
     *
     * @return list of roomss.
     */
    @Override
    public List<Room> selectAll() {
        return DB;
    }

    /**
     * Finds room identified by id.
     *
     * @param id - identification of the room.
     * @return optional room. It may not exist.
     */
    @Override
    public Optional<Room> select(UUID id) {
        return DB.stream()
                .filter(room -> room.getId().equals(id))
                .findAny();
    }

    /**
     * Finds room under the certain name.
     *
     * @param name - name of the room.
     * @return optional room. It may not exist.
     */
    @Override
    public Optional<Room> select(String name) {
        return DB.stream()
                .filter(room -> room.getName().equals(name.replaceAll("-", " ")))
                .findAny();
    }

    /**
     * Finds room with certain user belonging
     * to it.
     *
     * @param user - id of the user.
     * @return optional room. It may not exist.
     */
    @Override
    public Optional<Room> selectRoomByUser(UUID user) {
        return DB.stream()
                .filter(room -> room.getUsers().contains(user))
                .findFirst();
    }

    /**
     * Adds an user to a room
     * provided the room exists.
     *
     * @param id - id of the room,
     * @param userId - id of the user.
     * @return -1 if room was not found, 1 if operation
     * was successful.
     */
    @Override
    public int updateRoomByUser(UUID id, UUID userId) {
        Optional<Room> room = select(id);

        if (room.isEmpty())
            return -1;

        room.get().getUsers().add(userId);

        return 1;
    }

    /**
     * Removes the user from a room
     * provided the room exists.
     *
     * @param id - id of the room,
     * @param userId - id of the user.
     * @return -1 if room was not found, 1 if operation
     * was successful.
     */
    @Override
    public int removeUserFromRoom(UUID id, UUID userId) {
        Optional<Room> room = select(id);

        if (room.isEmpty())
            return -1;

        room.get().getUsers().remove(userId);

        return 1;
    }

}
