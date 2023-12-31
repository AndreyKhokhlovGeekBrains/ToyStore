package org.example.view;

import org.example.view.commands.*;

import java.util.ArrayList;
import java.util.List;

public class MainMenu {
    private List<Command> commandList;
    public MainMenu(View view){
        commandList = new ArrayList<>();
        commandList.add(new LoadStore(view));
        commandList.add(new AddItem(view));
        commandList.add(new ChangeQuantity(view));
        commandList.add(new DeleteItem(view));
        commandList.add(new PrintStoreItems(view));
        commandList.add(new RunLottery(view));
        commandList.add(new GetPrize(view));
        commandList.add(new ShowPrizesInFile(view));
        commandList.add(new ClearPrizesFile(view));
        commandList.add(new Save(view));
        commandList.add(new Exit(view));

    }
    public String print(){
        StringBuilder sb = new StringBuilder();
        sb.append("List of commands:\n");
        for (int i = 0; i < commandList.size(); i++) {
            sb.append(i+1)
                    .append(". ")
                    .append(commandList.get(i).getDescription())
                    .append("\n");
        }
        return sb.toString();
    }

    public void execute(int choice){
        Command command = commandList.get(choice - 1);
        command.execute();
    }
}
