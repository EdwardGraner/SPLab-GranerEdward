package ro.uvt.info.dessignpatternslab2024.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import ro.uvt.info.dessignpatternslab2024.observers.AllBooksSubject;
import ro.uvt.info.dessignpatternslab2024.observers.SseObserver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/sse")
public class SseController implements SseObserver {
    private final AllBooksSubject allBooksSubject;
    private final List<SseEmitter> emitters = new ArrayList<>();

    public SseController(AllBooksSubject allBooksSubject) {
        this.allBooksSubject = allBooksSubject;
        this.allBooksSubject.attach(this);
    }

    @GetMapping("/stream")
    public SseEmitter streamEvents() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitters.add(emitter);

        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));

        return emitter;
    }

    @Override
    public void update(String event) {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event().data(event));
            } catch (IOException e) {
                deadEmitters.add(emitter);
            }
        }
        emitters.removeAll(deadEmitters);
    }
}
