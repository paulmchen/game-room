package com.acme.app.game;

import org.apache.servicecomb.foundation.common.utils.BeanUtils;
import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.core.MediaType;

@RestSchema(schemaId = "JoinGame")
@RequestMapping(path = "/join-game" , produces = MediaType.APPLICATION_JSON)
public class JoinGameSession {
    @GetMapping("/")
    public String joinSession(@RequestParam(value = "username") String username) {
        JoinGameClient client = BeanUtils.getBean("JoinGameClient");
        return client.run(username);
    }
}
