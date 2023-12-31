package org.example.view.commands;

import org.example.view.View;

public class LoadStore extends Command{
    public LoadStore(View view) {
        super("Load store", view);
    }

    @Override
    public void execute() {
        getView().loadStore();
    }
}
