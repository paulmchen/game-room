package com.acme.app.provider.provider;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.core.MediaType;

@RestSchema(schemaId = "joinDBSession")
@RequestMapping(path = "/" , produces = MediaType.APPLICATION_JSON)
public class GameSession {
    @RequestMapping(value = "/join-game", method = RequestMethod.GET)
    public String joinSession(@RequestParam(value = "username") String username) {
        if (username == null) {
            throw new IllegalArgumentException("Username must be provided.");
        }

        // Todo connect to game session database to join the specific game session

        // increment db connection count by 1
        GameDBSessionCountMeterInitializer.incrementCounter();

        return "User " + username + " has successfully connected to the game session.";
    }
}