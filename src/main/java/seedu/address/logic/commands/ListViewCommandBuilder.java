package seedu.address.logic.commands;

import java.util.Map;

import seedu.address.logic.UiManager;
import seedu.address.logic.commands.arguments.list.OptionalArgumentList;
import seedu.address.logic.commands.arguments.list.RequiredArgumentList;

//@@author Kyzure
/**
 * Represents a CommandBuilder responsible for creating {@link ListViewCommand}.
 */
class ListViewCommandBuilder extends CommandBuilder {

    private UiManager uiManager;

    ListViewCommandBuilder(UiManager uiManager) {
        this.uiManager = uiManager;
    }

    @Override
    protected RequiredArgumentList defineCommandArguments() {
        return null;
    }

    @Override
    protected Map<String, OptionalArgumentList> defineCommandOptions() {
        return null;
    }

    UiManager getUiManager() {
        return uiManager;
    }

    @Override
    protected Command commandBuild() {
        return new ListViewCommand(this);
    }
}
