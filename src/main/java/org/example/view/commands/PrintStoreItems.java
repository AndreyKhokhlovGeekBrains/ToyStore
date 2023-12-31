package org.example.view.commands;

import org.example.view.View;

public class PrintStoreItems extends Command{
    public PrintStoreItems(View view) {
        super("Print store items", view);
    }

    @Override
    public void execute() {
        getView().printStoreItems();
    }
}
