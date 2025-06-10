package com.roomchat.roomchatbackend.Controllers;

import com.roomchat.roomchatbackend.Playload.MessageRequest;
import com.roomchat.roomchatbackend.Repositries.RoomRepository;
import com.roomchat.roomchatbackend.config.APPConstant;
import com.roomchat.roomchatbackend.entities.Message;
import com.roomchat.roomchatbackend.entities.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

//@Controller
//@CrossOrigin("http://localhost:5173")
//public class ChatController {
//    @Autowired
//    private RoomRepository roomRepository;
//
//    public ChatController(RoomRepository roomRepository) {
//        this.roomRepository = roomRepository;
//    }
////for sending and receiving msg
//    @MessageMapping("/sendMessage/{roomId}")//  /app/sendMessage/roomId
//    @SendTo("/topic/room/{roomId}")//clint subscribe the app
//    public Message sendMessage(
//            @DestinationVariable String roomId,
//            @RequestBody MessageRequest request
//    ){
//        Room room = roomRepository.findByRoomId(request.getRoomId());
//        Message message=new Message();
//        message.setContent(request.getContent());
//        message.setSender(request.getSender());
//        message.setTimeStamop(LocalDateTime.now());
//        if (room!= null){
//            room.getMessage().add(message);
//            roomRepository.save(room);
//        }
//        else{
//            throw new RuntimeException("room not found");
//        }
//
//return message;
//    }
//}
@Controller
@CrossOrigin("http://localhost:5173")
public class ChatController {


    private RoomRepository roomRepository;

    public ChatController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    //for sending and receiving messages
    @MessageMapping("/sendMessage/{roomId}")// /app/sendMessage/roomId
    @SendTo("/topic/room/{roomId}")//subscribe
    public Message sendMessage(
            @DestinationVariable String roomId,
            @RequestBody MessageRequest request
    ) {

        Room room = roomRepository.findByRoomId(request.getRoomId());
        Message message = new Message();
        message.setContent(request.getContent());
        message.setSender(request.getSender());
        message.setTimeStamp(LocalDateTime.now());
        if (room != null) {
            room.getMessages().add(message);
            roomRepository.save(room);
        } else {
            throw new RuntimeException("room not found !!");
        }

        return message;


    }
}
