package com.og.oms.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * webSocket service
 * 
 * @author oscar
 *
 */
@Service
public class WebSocketService {

    @Autowired
    private SimpMessagingTemplate template;

    /**
     * 向客户端推送消息
     * 
     * @param csCount
     * @param opCount
     */
    public void sendMessage(Integer csCount, Integer opCount){
        template.convertAndSend("/topic/getResponse",new Response(csCount, opCount));
    }

}
