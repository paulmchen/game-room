package com.acme.app.provider.provider;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.netflix.spectator.api.Counter;
import com.netflix.spectator.api.DefaultRegistry;
import com.netflix.spectator.api.ManualClock;
import com.netflix.spectator.api.Registry;
import org.apache.servicecomb.core.event.InvocationStartEvent;
import org.apache.servicecomb.foundation.metrics.MetricsBootstrapConfig;
import org.apache.servicecomb.foundation.metrics.MetricsInitializer;
import org.apache.servicecomb.foundation.metrics.registry.GlobalRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameDBSessionCountMeterInitializer implements MetricsInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameDBSessionCountMeterInitializer.class);

    public static final String App_DB_CONNECTION_COUNT_ID = "app_db_connection_count";
    private static Counter counter;

    @Override
    public void init(GlobalRegistry globalRegistry, EventBus eventBus, MetricsBootstrapConfig config) {
        Registry registry = new DefaultRegistry(new ManualClock());
        globalRegistry.add(registry);
        this.counter = registry.counter(App_DB_CONNECTION_COUNT_ID, "scaling", "true", "help", "The total number of database connections that the app was established.");
        eventBus.register(this);
    }

    public synchronized static void incrementCounter() {
        counter.increment();
    }
}
