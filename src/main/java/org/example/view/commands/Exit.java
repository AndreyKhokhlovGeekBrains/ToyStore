package org.example.view.commands;

import org.example.view.View;

public class Exit extends Command{
    public Exit( View view) {
        super("Exit", view);
    }

    @Override
    public void execute() {
        getView().exit();
    }
}
