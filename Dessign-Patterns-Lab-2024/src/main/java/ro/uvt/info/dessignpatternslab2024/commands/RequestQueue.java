package ro.uvt.info.dessignpatternslab2024.commands;

import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.*;

@Component
public class RequestQueue {
    private final ExecutorService executor = Executors.newFixedThreadPool(5);
    private final Map<String, Future<?>> requestStatus = new ConcurrentHashMap<>();

    public String submitRequest(Command command) {
        String requestId = UUID.randomUUID().toString();
        Future<?> future = executor.submit(command::execute);
        requestStatus.put(requestId, future);
        return requestId;
    }

    public String getRequestStatus(String requestId) {
        Future<?> future = requestStatus.get(requestId);
        if (future == null) {
            return "Request not found";
        } else if (future.isDone()) {
            return "Completed";
        } else {
            return "Processing";
        }
    }
}
