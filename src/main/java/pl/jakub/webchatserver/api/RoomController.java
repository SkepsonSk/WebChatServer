package pl.jakub.webchatserver.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.jakub.webchatserver.model.Room;
import pl.jakub.webchatserver.service.RoomService;

import java.util.List;
import java.util.UUID;

/**
 * Provides an HTTP gateway for
 * managing rooms.
 *
 * @author Jakub Zelmanowicz
 */
@RequestMapping("api/v1/room")
@RestController
public class RoomController {

    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Gets all the rooms.
     *
     * @return list of rooms.
     */
    @GetMapping
    public List<Room> selectAll() {
        return roomService.selectAll();
    }

    /**
     * Gets the room under specific id.
     *
     * @param id - id of the room.
     * @return room. May return null.
     */
    @GetMapping(path = "{id}")
    public Room select(@PathVariable("id") UUID id) {
        return roomService.select(id)
                .orElse(null);
    }

    /**
     * Gets the room under specific name.
     *
     * @param name - name of the room.
     * @return room. May return null.
     */
    @GetMapping(path = "name/{name}")
    public Room select(@PathVariable("name") String name) {
        return roomService.select(name)
                .orElse(null);
    }

    /**
     * Adds a user to the room.
     *
     * @param userId - id of the user,
     * @param roomId - id of the room.
     * @return -1 if room does not exists. 1 if operation
     * was successful.
     */
    @PutMapping(path = "{user}/{room}")
    public int updateRoomByUser(@PathVariable("user") UUID userId,
                                @PathVariable("room") UUID roomId) {

        return roomService.updateRoomByUser(roomId, userId);
    }

    /**
     * Removes the user to the room.
     *
     * @param userId - id of the user,
     * @param roomId - id of the room.
     * @return -1 if room does not exists. 1 if operation
     * was successful.
     */
    @PutMapping(path = "leave/{user}/{room}")
    public int removeUserFromRoom(@PathVariable("user") UUID userId,
                                  @PathVariable("room") UUID roomId) {

        return roomService.removeUserFromRoom(roomId, userId);
    }

    /**
     * Adds a room to the DAO.
     *
     * @param room - room data object.
     * @return id of newly added room.
     */
    @PostMapping
    public UUID addRoom(@RequestBody Room room) {
        return roomService.addRoom(room);
    }


}
