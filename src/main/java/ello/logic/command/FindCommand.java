package ello.logic.command;

import java.util.Collections;
import java.util.List;

import ello.logic.command.exception.EmptySearchKeywordException;
import ello.model.task.Task;
import ello.model.task.TaskList;

/**
 * Represents a command to find tasks containing a specific keyword in exact match.
 */
public class FindCommand extends Command {
    public static final CommandInfo COMMAND_INFO = new CommandInfo(
            "find",
            "Finds tasks containing the specified keyword",
            "Syntax: find <keyword>"
    );
    private final String searchWord;

    /**
     * Creates a find command with the specified search keyword.
     *
     * @param searchWord The keyword to search for in task descriptions.
     */
    public FindCommand(String searchWord) {
        super("find");
        this.searchWord = searchWord;
    }

    /**
     * Executes the find command by searching for tasks that match the keyword.
     *
     * @param taskList The task list to search in.
     * @return The command result containing the execution result.
     */
    @Override
    public CommandResult execute(TaskList taskList) {
        String keyword = trimmedSearchWord();

        if (keyword.isEmpty()) {
            throw new EmptySearchKeywordException();
        }

        List<Task> matchingTasks = taskList.findTasks(keyword);

        String feedback;
        if (matchingTasks.isEmpty()) {
            feedback = "No matching tasks found for keyword: \"" + keyword + "\"";
        } else {
            StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                result.append((i + 1)).append(". ").append(matchingTasks.get(i));
                if (i != matchingTasks.size() - 1) {
                    result.append("\n");
                }
            }
            feedback = result.toString();
        }

        return new CommandResult(feedback, getCommandType());
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public List<CommandInfo> getCommandInfoList() {
        return Collections.singletonList(COMMAND_INFO);
    }

    private String trimmedSearchWord() {
        return searchWord.trim();
    }
}
