package cz.los.simulation;

import cz.los.config.SimulationConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public final class TimeExecutor implements Runnable {

    private final SimulationConfig config = SimulationConfig.getInstance();

    private volatile AtomicInteger day = new AtomicInteger(1);

    public void run() {
        log.info("Staring simulation period. Total days: {}, day length in seconds: {}",
                config.getSimulationLength(),
                config.getDayLengthInSecs());
        while (day.get() <= config.getSimulationLength()) {
            log.info("{} day started.", day.get());
            try {
                Thread.sleep(config.getDayLengthInSecs() * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("TimeExecutor thread interrupted");
            }
            log.info("{} day ended.", day.get());
            day.incrementAndGet();
        }
    }

}
