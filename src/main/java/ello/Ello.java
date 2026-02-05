package ello;

import ello.command.Command;
import ello.command.parser.CommandParser;
import ello.common.exception.ElloException;
import ello.common.util.AppConstants;
import ello.service.TaskService;
import ello.ui.Ui;

public class Ello {
    /** The user interface component for interacting with the user. */
    private final Ui ui;
    /** The service responsible for task management and persistence. */
    private final TaskService taskService;

    /**
     * Constructs an instance of the Ello application.
     */
    public Ello() {
        this.ui = new Ui();
        this.taskService = new TaskService(AppConstants.DEFAULT_SAVE_PATH);
    }

    public static void main(String[] args) {
        new Ello().run();
    }

    private void run() {
        loadTasks();
        ui.showGreeting();
        runCommandLoop();
    }

    private void loadTasks() {
        try {
            int count = taskService.load();
            ui.showLoadResult(count);
        } catch (ElloException e) {
            ui.showError("Could not load tasks: " + e.getMessage());
        }
    }

    private void runCommandLoop() {
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = CommandParser.parse(fullCommand);
                command.execute(taskService.getTaskList(), ui);
                taskService.save();
                isExit = command.isExit();
            } catch (ElloException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}
