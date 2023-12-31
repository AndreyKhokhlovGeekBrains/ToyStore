package org.example.view.commands;

import org.example.view.View;

public class DeleteItem extends Command{
    public DeleteItem(View view) {
        super("Delete an item", view);
    }

    @Override
    public void execute() {
        getView().deleteItem();
    }
}
