package com.churway.ws;

import com.churway.entity.Item;
import com.churway.model.SysUser;
import com.churway.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2020/10/29
 * @since 1.0.0
 */
@Component
public class WsHandler extends TextWebSocketHandler {

    @Autowired
    private ItemService itemService;

    //key--商品/房间id,v- sessionId和session
    private static Map<Long, Map<String, WebSocketSession>> map = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Long id = (Long)session.getAttributes().get("id");
        Map<String, WebSocketSession> socketSessionMap = map.get(id);
        if (socketSessionMap == null) {
            synchronized (map) {
                if (socketSessionMap == null)
                    socketSessionMap = new ConcurrentHashMap<>();
            }
        }
        socketSessionMap.put(session.getId(), session);
        map.put(id, socketSessionMap);
        session.sendMessage(new TextMessage("欢迎加入本场拍卖!"));
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
        String originalMsg = (String) message.getPayload();
        SysUser user = (SysUser) session.getAttributes().get("user");
        //--当前用户所竞拍的商品id
        Long id = (Long) session.getAttributes().get("id");
        synchronized (map.get(id)) {
            Item item = itemService.findItemById(id);
            if (item.getCurrentPrice().add(item.getMinDeltaPrice()).compareTo(new BigDecimal(originalMsg)) == 1) {
                session.sendMessage(new TextMessage("加价不能低于" + item.getMinDeltaPrice()));
                return;
            }
            if (item.getCurrentPrice().compareTo(new BigDecimal(originalMsg)) == 1) {
                session.sendMessage(new TextMessage("您的出价低于目前的竞拍价"));
                return;
            }
            item.setCurrentPrice(new BigDecimal(originalMsg));
            item.setBuyerId(user.getId());
            itemService.update(item);
        }
        //构建广播的msg
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String msg = user.getAccount()+ " 出价: " + originalMsg + "元 | 时间:" + simpleDateFormat.format(new Date());
        //广播
        Map<String, WebSocketSession> webSocketSessionMap = map.get(id);
        for (Map.Entry<String, WebSocketSession> s : webSocketSessionMap.entrySet()) {
            s.getValue().sendMessage(new TextMessage(msg));
        }
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        map.remove(session.getId());
    }

    public Map<Long, Map<String, WebSocketSession>> getMap() {
        return map;
    }
}
