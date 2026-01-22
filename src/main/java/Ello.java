package main.java;

import main.java.command.Command;
import main.java.exception.ElloException;
import main.java.exception.InvalidCommandException;
import main.java.parser.Parser;
import main.java.storage.TaskList;
import main.java.ui.Ui;

public class Ello {
    private static final TaskList taskList = new TaskList();
    private static final Ui ui = new Ui();

    public static void main(String[] args) {
        ui.showGreeting();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command command = Parser.parse(fullCommand);
                command.execute(taskList, ui);
                isExit = command.isExit();
            } catch (ElloException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}
