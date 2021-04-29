package com.nebula.wisys.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.Date;
import java.util.Objects;

@Controller
public class WebsocketAppController {

    final static Logger logger = LoggerFactory.getLogger(WebsocketAppController.class);

    private SimpMessagingTemplate simpMessagingTemplate;

    @Value("${websocket.scheduled.channel}")
    private String webSocketScheduledChannel;

    @Autowired
    public WebsocketAppController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/message/broadcast")
    @SendTo("/channel/broadcast")
    public String processMessageForBroadcast(@Payload String message, Principal principal) throws Exception {
        String wsMessage = String.format("Websocket Broadcast Message: %s, Principal: %s", Objects.toString(message, "null"), Objects.toString(principal, "null"));
        logger.info(wsMessage);
        return wsMessage;
    }

    @MessageMapping("/message/unicast")
    @SendToUser("/channel/unicast")
    public String processMessageForUnicast(@Payload String message, Principal principal) throws Exception {
        String wsMessage = String.format("Websocket Unicast Message: %s, Principal: %s", Objects.toString(message, "null"), Objects.toString(principal, "null"));
        logger.info(wsMessage);
        return wsMessage;
    }

    @MessageExceptionHandler
    @SendToUser("/channel/error")
    public String handleWebSocketException(Throwable exception) {
        String exMessage = String.format("Websocket Exception: %s", exception.getLocalizedMessage());
        logger.error(exMessage);
        return exMessage;
    }

    @Scheduled(fixedDelayString = "${websocket.scheduled.rate.fixed}")
    public void scheduleFixedRatedWebSocketCast() {
        String scheduledMessage = String.format("Websocket scheduled message @ %s", new Date().toString());
        logger.info(scheduledMessage);
        simpMessagingTemplate.convertAndSend(webSocketScheduledChannel, scheduledMessage);
    }
}
