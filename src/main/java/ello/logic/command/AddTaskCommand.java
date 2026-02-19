package ello.logic.command;

import java.util.Arrays;
import java.util.List;

import ello.model.task.Task;
import ello.model.task.TaskList;
import ello.model.task.TaskType;

/**
 * Represents a command to add a task to the task list.
 */
public class AddTaskCommand extends Command {
    public static final List<CommandInfo> COMMAND_INFO_LIST = createCommandInfoList();

    private final Task taskToAdd;
    private final TaskType taskType;

    /**
     * Constructs an AddTaskCommand with the specified task to add and its corresponding task type.
     * The command type is generated based on the enumerated tasks in {@link TaskType}.
     *
     * @param taskToAdd The task to be added to the task list.
     * @param taskType  The type of the task being added, used to generate the command type and for help information.
     */
    public AddTaskCommand(Task taskToAdd, TaskType taskType) {
        super("add_" + taskType.getCommandWord());
        this.taskToAdd = taskToAdd;
        this.taskType = taskType;
    }

    /**
     * Creates a list of CommandInfo objects for the add task commands corresponding to each {@link TaskType}.
     *
     * @return A list of CommandInfo objects for the add task commands.
     */
    private static List<CommandInfo> createCommandInfoList() {
        return Arrays.asList(
                new CommandInfo(
                        TaskType.TODO.getCommandWord(),
                        "Adds a todo task to the list",
                        TaskType.TODO.buildSyntax()
                ),
                new CommandInfo(
                        TaskType.DEADLINE.getCommandWord(),
                        "Adds a deadline task to the list",
                        TaskType.DEADLINE.buildSyntax()
                ),
                new CommandInfo(
                        TaskType.EVENT.getCommandWord(),
                        "Adds an event task to the list",
                        TaskType.EVENT.buildSyntax()
                )
        );
    }

    /**
     * Executes the add task command by adding the specified task to the task list.
     *
     * @param taskList The task list to add the task to.
     * @return The command result containing the execution result
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        taskList.add(taskToAdd);

        String taskCountMessage = generateTaskCountMessage(taskList.size());
        String feedback = "Got it. I've added this task:\n\n  "
                + taskToAdd + "\n\n"
                + taskCountMessage;

        return new CommandResult(feedback, getCommandType());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public List<CommandInfo> getCommandInfoList() {
        return COMMAND_INFO_LIST;
    }

    /**
     * Generates a message indicating the current count of tasks in the list.
     *
     * @param count The number of tasks.
     * @return A String message indicating the task count.
     */
    private String generateTaskCountMessage(int count) {
        assert (count >= 0) : "Task count should not be negative.";
        if (count == 1) {
            return "Now you have 1 Task in the list.";
        }
        return "Now you have " + count + " tasks in the list.";
    }
}
