package com.acme.app.game;

import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.core.MediaType;

@RestSchema(schemaId = "Game")
@RequestMapping(path = "/" , produces = MediaType.APPLICATION_JSON)
public class GameSession {

    @RequestMapping(path = "/join-game" , produces = MediaType.APPLICATION_JSON)
    public String joinSession(@RequestParam(value = "username") String username) {
        GameClient client = BeanUtils.getBean("GameClient");
        return client.runJoin(username);
    }

    @RequestMapping(path = "/leave-game" , produces = MediaType.APPLICATION_JSON)
    public String leaveSession(@RequestParam(value = "username") String username) {
        GameClient client = BeanUtils.getBean("GameClient");
        return client.runLeave(username);
    }
}
