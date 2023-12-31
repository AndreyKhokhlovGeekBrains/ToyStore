package org.example.view.commands;

import org.example.view.View;

public class ShowPrizesInFile extends Command{
    public ShowPrizesInFile(View view) {
        super("Show prizes in the file", view);
    }

    @Override
    public void execute() {
        getView().showPrizesInFile();
    }
}
