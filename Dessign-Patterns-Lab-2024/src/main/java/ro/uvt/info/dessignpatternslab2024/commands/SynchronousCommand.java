package ro.uvt.info.dessignpatternslab2024.commands;

public abstract class SynchronousCommand implements Command {
    @Override
    public final void execute() {
        executeCommand();
    }

    protected abstract void executeCommand();
}
