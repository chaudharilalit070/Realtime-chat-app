package com.roomchat.roomchatbackend.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

//@Document(collection = "rooms")
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class Room {
//    @Id
//    private String id;//mongo db unique identifier
//    private  String roomId;
//    private List<Message> message= new AbstractList<>() {
//        @Override
//        public int size() {
//            return 0;
//        }
//
//        @Override
//        public Message get(int index) {
//            return null;
//        }
//    };
//}

@Document(collection = "rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Room {
    @Id
    private String id;//Mongo db : unique identifier
    private String roomId;
    private List<Message> messages = new ArrayList<>();


}
