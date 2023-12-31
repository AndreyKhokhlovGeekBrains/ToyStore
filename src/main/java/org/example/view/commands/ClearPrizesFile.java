package org.example.view.commands;

import org.example.view.View;

public class ClearPrizesFile extends Command{
    public ClearPrizesFile(View view) {
        super("Clear the file with prize items", view);
    }

    @Override
    public void execute() {
        getView().clearPrizesFile();
    }
}
