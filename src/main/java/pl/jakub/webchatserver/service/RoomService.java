package pl.jakub.webchatserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import pl.jakub.webchatserver.dao.room.RoomDao;
import pl.jakub.webchatserver.model.Room;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Provides rooms data directly
 * from Data Access Objects.
 *
 * @author Jakub Zelmanowicz
 */
@Service
public class RoomService {

    /**
     * Rooms access object.
     */
    private final RoomDao roomDao;

    /**
     * Creates a service basing on a Room DAO.
     *
     * @param roomDao - room dao.
     */
    @Autowired
    public RoomService(@Qualifier("memoryRoomDao") RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    /**
     * Adds a new room to data object.
     *
     * @param room - added room.
     * @return id of newly created room.
     */
    public UUID addRoom(Room room) {
        return roomDao.insertRoom(room);
    }

    /**
     * Selects all the rooms from datasource.
     *
     * @return list of users.
     */
    public List<Room> selectAll() {
        return roomDao.selectAll();
    }

    /**
     * Selects a room identified by
     * id.
     *
     * @param id - if of the user.
     * @return optional room.
     */
    public Optional<Room> select(UUID id) {
        return roomDao.select(id);
    }

    /**
     * Selects a room with a
     * given name.
     *
     * @param name - name of the room.
     * @return
     */
    public Optional<Room> select(String name) {
        return roomDao.select(name);
    }

    /**
     * Select a room with a specific user.
     *
     * @param userId - id of the user.
     * @return optional room. It may not exist.
     */
    public Optional<Room> selectRoomByUser(UUID userId) {
        return roomDao.selectRoomByUser(userId);
    }

    /**
     * Adds a user to the room.
     *
     * @param id - id of the room,
     * @param userId - id of the user.
     * @return -1 if room does not exists. 1 if operation
     * was successful.
     */
    public int updateRoomByUser(UUID id, UUID userId) {
        return roomDao.updateRoomByUser(id, userId);
    }

    /**
     * Removes a user from the room.
     *
     * @param id - id of the room,
     * @param userId - id of the user.
     * @return -1 if room does not exists. 1 if operation
     * was successful.
     */
    public int removeUserFromRoom(UUID id, UUID userId) {
        return roomDao.removeUserFromRoom(id, userId);
    }

}
