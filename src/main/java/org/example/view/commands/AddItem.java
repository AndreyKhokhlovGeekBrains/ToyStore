package org.example.view.commands;

import org.example.view.View;

public class AddItem extends Command{
    public AddItem(View view) {
        super("Add/create item", view);
    }

    @Override
    public void execute() {
        getView().addItem();
    }
}
