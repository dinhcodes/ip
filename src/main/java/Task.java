public class Task {
    private String taskName;
    private Status status;

    public Task(String taskName) {
        this.taskName = taskName;
        this.status = Status.NOT_DONE;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getTaskStatus() {
        if (this.status == Status.DONE){
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    public String getTaskStatusAndName() {
        return this.getTaskStatus() + " " + this.taskName;
    }

    public void mark() {
        this.status = Status.DONE;
    }

    public void unmark() {
        this.status = Status.NOT_DONE;
    }

    @Override
    public String toString() {
        return this.taskName;
    }
}
