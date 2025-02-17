package ro.uvt.info.dessignpatternslab2024.observers;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AllBooksSubject {
    private final List<SseObserver> observers = new ArrayList<>();

    public void attach(SseObserver observer) {
        observers.add(observer);
    }

    public void detach(SseObserver observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String event) {
        for (SseObserver observer : observers) {
            observer.update(event);
        }
    }
}
