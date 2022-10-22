package com.example.hola.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import com.example.hola.model.Message;

@RestController
public class MessageController {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	
	@MessageMapping("/message")   //app/message
	@SendTo("/chatroom/public")
	public Message receiverPublicMessage(@Payload Message message) {
		return message;
	}
	
	
	@MessageMapping("/private-message")
	public Message receiverPrivateMessage(@Payload Message message) {
		
		simpMessagingTemplate.convertAndSendToUser(message.getReceiverName(), "/private", message);    //user/Sinha/private
		return message;
	}
	
}
