package controller.turnMapper;

public interface Command {
    public void execute();

    public String getAction();

    public String getResults();
}
