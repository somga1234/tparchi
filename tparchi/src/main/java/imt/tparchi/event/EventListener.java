package imt.tparchi.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EventListener implements CommandLineRunner {

    @Autowired
    private EventPublisher eventPublisher;

    @Override
    public void run(String... args) throws Exception {
        new Thread(() -> {
            try {
                while (true) {
                    String event = eventPublisher.consume();
                    // Process event
                    System.out.println("Processing event: " + event);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}
