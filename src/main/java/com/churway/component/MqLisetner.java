package com.churway.component;

import com.churway.entity.Item;
import com.churway.service.ItemService;
import com.churway.ws.WsHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.Map;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2020/11/17
 * @since 1.0.0
 */
@Component
public class MqLisetner {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    ItemService itemService;

    @Autowired
    WsHandler wsHandler;

/*    @RabbitListener(queues = "readyToActionQ")
    public void judgeIfStartAction(Item item) {
        if (item.getStartDate().getTime() - new Date().getTime() > 60000) {
            rabbitTemplate.convertAndSend("oneMinDelayQ", item);
            return;
        }
        itemService.startAction(item);
        rabbitTemplate.convertAndSend("tenMinDelayQ", item);
    }*/

    @RabbitListener(queues = "readyToStopActionQ")
    public void judgeIfStopAction(Item item) throws IOException {
        item.setSurviveTime(item.getSurviveTime() - 10);
        if (item.getSurviveTime() > 0){
            rabbitTemplate.convertAndSend("tenMinDelayQ", item);
            return;
        }
        //关闭竞拍室
        Map<String, WebSocketSession> webSocketSessionMap = wsHandler.getMap().get(item.getGoodsId());
        if(webSocketSessionMap != null){
            for(Map.Entry<String,WebSocketSession> s:webSocketSessionMap.entrySet()){
                s.getValue().sendMessage(new TextMessage("竞拍已结束,请及时离场"));
                s.getValue().close();
            }
            webSocketSessionMap.remove(item.getGoodsId());
        }
        itemService.stopAction(item.getId());
    }
}
