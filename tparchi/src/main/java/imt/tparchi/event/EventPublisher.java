package imt.tparchi.event;

import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Component
public class EventPublisher {
    private BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    public void publish(String event) {
        queue.offer(event);
    }

    public String consume() throws InterruptedException {
        return queue.take();
    }
}
