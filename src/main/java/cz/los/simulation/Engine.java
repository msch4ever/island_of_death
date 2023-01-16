package cz.los.simulation;

import cz.los.animals.Animal;
import cz.los.config.SimulationConfig;
import cz.los.island.Island;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
public final class Engine {

    private final SimulationConfig config = SimulationConfig.getInstance();
    private final Island island = Island.getInstance();

    private volatile AtomicInteger day = new AtomicInteger(1);

    public void start() {
        log.info("Staring simulation period. Total days: {}, day length in seconds: {}",
                config.getSimulationLength(),
                config.getDayLengthInSecs());
        do {
            log.info("{} day started.", day.get());
            try {
                Day thisDay = new Day(day.get());
                if (!thisDay.startDay()) {
                    log.info("Simulation is stopped..");
                    break;
                }
                Thread.sleep(config.getDayLengthInSecs() * 1000L);
                thisDay.endDay();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("TimeExecutor thread interrupted");
            }
            log.info("{} day ended.", day.get());
            day.incrementAndGet();
        } while (day.get() <= config.getSimulationLength());
        log.info("Finishing simulation. {}", island.getAnimals());
    }

    public void startSynchronous() {
        log.info("Staring simulation period. Total days: {}, day length in seconds: {}",
                config.getSimulationLength(),
                config.getDayLengthInSecs());
        do {
            log.info("{} day started.", day.get());
            island.getAnimals().stream().filter(Animal::isAlive).forEach(Animal::run);
            log.info("{} day ended.", day.get());
            day.incrementAndGet();
        } while (day.get() <= config.getSimulationLength());
        log.info("Finishing simulation. {}", island.getAnimals());
    }

    private class Day {

        private final int dayNumber;
        private final ExecutorService executorService;

        private List<Future> routines = new ArrayList<>();

        public Day(int dayNumber) {
            this.dayNumber = dayNumber;
            this.executorService = Executors.newCachedThreadPool();
        }

        private boolean startDay() {
            log.info("Starting {} day...", dayNumber);
            List<Animal> aliveAnimals = island.getAnimals().stream()
                    .filter(Animal::isAlive)
                    .collect(Collectors.toList());
            if (aliveAnimals.isEmpty()) {
                log.info("ALL ANIMALS ARE DEAD!");
                return false;
            }
            aliveAnimals.forEach(it -> {
                routines.add(executorService.submit(it));
            });
            return true;
        }

        private void endDay() {
            log.info("Day {} is coming to an end..", dayNumber);
            try {
                while (routines.stream().anyMatch(it -> !it.isDone())) {
                    Thread.sleep(100);
                }
                List<Runnable> leftovers = executorService.shutdownNow();
                if (!leftovers.isEmpty()) {
                    throw new RuntimeException("All routines should be done by that moment!!");
                }
            } catch (InterruptedException e) {
                log.error("Day could not end without error...");
            }
        }

    }


}
