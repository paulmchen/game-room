package com.acme.app.provider.provider;

import org.apache.servicecomb.provider.rest.common.RestSchema;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.core.MediaType;
import io.prometheus.client.Gauge;

@RestSchema(schemaId = "DBSession")
@RequestMapping(path = "/" , produces = MediaType.APPLICATION_JSON)
public class GameSession {

    private static final String CONNECTION_COUNT = "app_db_connection_count";
    private static final String HELP = "The total number of database connections that the app was establoshed.";
    private static final Gauge connection_count = Gauge.build()
            .name(CONNECTION_COUNT).help(HELP)
            .labelNames("scaling", "help")
            .register();

    @RequestMapping(value = "/join-game", method = RequestMethod.GET)
    public String joinSession(@RequestParam(value = "username") String username) {
        if (username == null) {
            throw new IllegalArgumentException("Username must be provided.");
        }

        // Todo connect to game session database to join the specific game session

        // increment db connection count by 1
        connection_count.labels("true", HELP).inc();

        return "User " + username + " has successfully connected to the game session.";
    }

    @RequestMapping(value = "/leave-game", method = RequestMethod.GET)
    public String leaveSession(@RequestParam(value = "username") String username) {
        if (username == null) {
            throw new IllegalArgumentException("Username must be provided.");
        }

        // Todo process to prepare for leaving the game session

        // decrement db connection count by 1
        connection_count.labels("true", HELP).dec();

        return "User " + username + " has successfully disconnected from the game session.";
    }
}