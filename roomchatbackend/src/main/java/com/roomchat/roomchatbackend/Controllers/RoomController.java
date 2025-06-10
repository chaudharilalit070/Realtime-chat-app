package com.roomchat.roomchatbackend.Controllers;

import com.roomchat.roomchatbackend.Repositries.RoomRepository;
import com.roomchat.roomchatbackend.config.APPConstant;
import com.roomchat.roomchatbackend.entities.Message;
import com.roomchat.roomchatbackend.entities.Room;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
//
//@RestController
//
//@RequestMapping("/api/v1/rooms")
//@CrossOrigin("http://localhost:5173")
//public class RoomController {
//private  RoomRepository roomRepository;
//
//    public RoomController(RoomRepository roomRepository) {
//        this.roomRepository = roomRepository;
//    }
//
//    //create room
//    @PostMapping
//  public ResponseEntity<?> createRoom(@RequestBody  String roomId ){
//        if (roomRepository.findByRoomId(roomId)!= null){
//            //room already here
//return  ResponseEntity.badRequest().body("room already exists !");
//        }
//        //create new room
//        Room room =new Room();
//        room.setRoomId(roomId);
//        Room savedRoom=roomRepository.save(room);
//        return ResponseEntity.status(HttpStatus.CREATED).body(room);
//    }
//
//
//    @GetMapping("/{roomId}")
//    public ResponseEntity<?> joinRoom(@PathVariable String roomId) {
//        Room room = roomRepository.findByRoomId(roomId);
//        if (room == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Room not found");
//        }
//        return ResponseEntity.ok(room);
//    }
//
//
//    // Get messages of the room with pagination
//    @GetMapping("/{roomId}/messages")
//    public ResponseEntity<List<Message>> getMessages(
//            @PathVariable String roomId,
//            @RequestParam(value = "page", defaultValue = "0") int page,
//            @RequestParam(value = "size", defaultValue = "20") int size) {
//
//        Room room = roomRepository.findByRoomId(roomId);
//        if (room == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
//        }
//
//        List<Message> messages = room.getMessage();
//        int totalMessages = messages.size();
//
//        // Reverse the list to show most recent messages first (if required)
//        Collections.reverse(messages);
//
//        int start = page * size;
//        int end = Math.min(start + size, totalMessages);
//
//        if (start >= totalMessages) {
//            return ResponseEntity.ok(Collections.emptyList());
//        }
//
//        List<Message> paginatedMessages = messages.subList(start, end);
//        return ResponseEntity.ok(paginatedMessages);
//    }
//
//}
@RestController
@RequestMapping("/api/v1/rooms")
@CrossOrigin("http://localhost:5173")
public class RoomController {

    private RoomRepository roomRepository;


    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    //create room
    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody String roomId) {

        if (roomRepository.findByRoomId(roomId) != null) {
            //room is already there
            return ResponseEntity.badRequest().body("Room already exists!");

        }


        //create new room
        Room room = new Room();
        room.setRoomId(roomId);
        Room savedRoom = roomRepository.save(room);
        return ResponseEntity.status(HttpStatus.CREATED).body(room);


    }


    //get room: join
    @GetMapping("/{roomId}")
    public ResponseEntity<?> joinRoom(
            @PathVariable String roomId
    ) {

        Room room = roomRepository.findByRoomId(roomId);
        if (room == null) {
            return ResponseEntity.badRequest()
                    .body("Room not found!!");
        }
        return ResponseEntity.ok(room);
    }


    //get messages of room

    @GetMapping("/{roomId}/messages")
    public ResponseEntity<List<Message>> getMessages(
            @PathVariable String roomId,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "20", required = false) int size
    ) {
        Room room = roomRepository.findByRoomId(roomId);
        if (room == null) {
            return ResponseEntity.badRequest().build()
                    ;
        }
        //get messages :
        //pagination
        List<Message> messages = room.getMessages();
        int start = Math.max(0, messages.size() - (page + 1) * size);
        int end = Math.min(messages.size(), start + size);
        List<Message> paginatedMessages = messages.subList(start, end);
        return ResponseEntity.ok(paginatedMessages);

    }


}
