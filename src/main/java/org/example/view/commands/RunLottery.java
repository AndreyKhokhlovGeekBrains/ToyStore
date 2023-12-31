package org.example.view.commands;

import org.example.view.View;

public class RunLottery extends Command{
    public RunLottery(View view) {
        super("Run lottery", view);
    }

    @Override
    public void execute() {
        getView().runLottery();
    }
}
