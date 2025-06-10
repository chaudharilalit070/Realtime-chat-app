package com.roomchat.roomchatbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//
//public class Message {
//    private  String sender;
//    private  String content;
//    private LocalDateTime timeStamop;
//
//    public Message(String sender, String content) {
//        sender = sender;
//        this.content = content;
//        this.timeStamop = LocalDateTime.now();
//    }
//}


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Message {

    private String sender;
    private String content;
    private LocalDateTime timeStamp;

    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timeStamp = LocalDateTime.now();
    }
}
