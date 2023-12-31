package org.example.view.commands;

import org.example.view.View;

public class GetPrize extends Command{
    public GetPrize(View view) {
        super("Get a prize", view);
    }

    @Override
    public void execute() {
        getView().getPrize();
    }
}
