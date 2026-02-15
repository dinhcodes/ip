package ello.command;

import java.util.List;

import ello.command.exception.EmptySearchKeywordException;
import ello.storage.TaskList;
import ello.task.Task;
import ello.ui.Ui;

/**
 * Represents a command to find tasks containing a specific keyword in exact match.
 */
public class FindCommand implements Command {
    private final String searchWord;

    /**
     * Creates a find command with the specified search keyword.
     *
     * @param searchWord The keyword to search for in task descriptions.
     */
    public FindCommand(String searchWord) {
        this.searchWord = searchWord;
    }

    private String trimmedSearchWord() {
        return searchWord.trim();
    }

    /**
     * Executes the find command by searching for tasks that match the keyword.
     *
     * @param taskList The task list to search in.
     * @param ui       The user interface for displaying results.
     * @throws EmptySearchKeywordException if the search keyword is empty or contains only whitespace.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        String keyword = trimmedSearchWord();

        if (keyword.isEmpty()) {
            throw new EmptySearchKeywordException();
        }

        List<Task> matchingTasks = taskList.findTasks(keyword);

        if (matchingTasks.isEmpty()) {
            ui.showMessage("No matching tasks found for keyword: \"" + keyword + "\"");
        } else {
            StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                result.append((i + 1)).append(". ").append(matchingTasks.get(i));
                if (i != matchingTasks.size() - 1) {
                    result.append("\n");
                }
            }
            ui.showMessage(result.toString());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

