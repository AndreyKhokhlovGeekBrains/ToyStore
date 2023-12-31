package org.example.view.commands;

import org.example.view.View;

public class ChangeQuantity extends Command{
    public ChangeQuantity(View view) {
        super("Change quantity", view);
    }

    @Override
    public void execute() {
        getView().changeQuantity();
    }
}
