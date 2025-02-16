package ro.uvt.info.dessignpatternslab2024.commands;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class AsynchronousCommand implements Command {
    private static final ExecutorService executor = Executors.newFixedThreadPool(5);

    @Override
    public final void execute() {
        executor.submit(this::executeCommand);
    }

    protected abstract void executeCommand();
}
