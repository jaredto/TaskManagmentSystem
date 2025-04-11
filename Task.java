/*
 * Represents a task in the system.
 * Tasks can be marked as complete.
 */


public class Task {
    private String task;
    private boolean isCompleted;

    public Task(String task) {
        this.task = task;
        this.isCompleted = false;
    }

    public String getTask() {
        return task;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markCompleted() {
        isCompleted = true;
    }

    @Override
    public String toString(){
        return task + (isCompleted ? " [DONE]" : " [TODO]");
    }
}
