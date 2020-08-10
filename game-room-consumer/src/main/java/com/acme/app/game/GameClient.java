package com.acme.app.game;

import org.apache.servicecomb.provider.springmvc.reference.RestTemplateBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("GameClient")
public class GameClient {
    private static final Logger LOG = LoggerFactory.getLogger(GameClient.class);
    private static RestTemplate restTemplate = RestTemplateBuilder.create();

    public String runJoin(String name) {
        String getJoinNotification = restTemplate.getForObject("cse://appProvider/join-game?username=" + name, String.class);
        LOG.info("Get result from microservice provider: " + getJoinNotification);
        return getJoinNotification;
    }

    public String runLeave(String name) {
        String getJoinNotification = restTemplate.getForObject("cse://appProvider/leave-game?username=" + name, String.class);
        LOG.info("Get result from microservice provider: " + getJoinNotification);
        return getJoinNotification;
    }
}
