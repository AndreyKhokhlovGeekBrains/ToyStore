package org.example.view;

import org.example.model.StoreItem;
import org.example.model.Toy;
import org.example.presenter.Presenter;

import java.io.Serializable;
import java.util.*;

public class ConsoleUI implements View{
    private Scanner scanner;
    private Presenter presenter;
    private boolean check;
    private MainMenu menu;
    private List<? extends StoreItem> storeItems;
//    private Map<Integer, Long> mapOfStoreItems;

    public ConsoleUI(){
        scanner = new Scanner(System.in);
        presenter = new Presenter();
        check = true;
        menu = new MainMenu(this);
        storeItems = new ArrayList<>();
//        mapOfStoreItems = new HashMap<>();
    }
    @Override
    public void start() {
        System.out.println("Hello!");
        while (check){
            printMenu();
            scanMenu();
        }
    }

    private void printMenu(){
        System.out.println(menu.print());
    }

    private void scanMenu(){
        String choice = scanner.nextLine();
        try{
            int intChoice = Integer.parseInt(choice);
            menu.execute(intChoice);
        } catch (NumberFormatException e){
            System.out.println("Incorrect input, please enter correct number:\n");
        } catch (IndexOutOfBoundsException e){
            System.out.println("Incorrect input, please enter a valid number:\n");
        }
    }

    @Override
    public void answer(String prompt) {
        System.out.println(prompt);
    }

    @Override
    public void addItem() {
        int quantity = 0;
        long dropoutRate = 0;
        boolean check = true;

        System.out.println("Enter name: ");
        String name = scanner.nextLine();

        System.out.println("Enter quantity: ");

        while (check){
            try{
                quantity = Integer.parseInt(scanner.nextLine());
                if(quantity > 0){
                    check = false;
                } else {
                    System.out.println("Enter a positive number.");
                }
            } catch (Exception e) {
                System.out.println("Incorrect input for quantity, please enter a valid number: ");
            }
        }

        check = true;
        System.out.println("Enter dropout rate: ");
        while (check){
            try{
                dropoutRate = Long.parseLong(scanner.nextLine());
                if(dropoutRate > 0 && dropoutRate < 100){
                    check = false;
                } else {
                    System.out.println("Enter a positive number from 1 to 99.");
                }
            } catch (Exception e){
                System.out.println("Incorrect input for dropout rate, please enter a valid number: ");
            }
        }

        presenter.addItem(name, quantity, dropoutRate);
        printStoreItems();
    }

    @Override
    public void printStoreItems() {
        presenter.printStoreItems();
    }

    @Override
    public void getItemByID(int id) {
        presenter.getItemByID(id);
    }

    @Override
    public void exit() {
        System.out.println("Bye-bye");
        check = false;
    }

    @Override
    public void runLottery() {
        presenter.runLottery();
    }

    @Override
    public void save() {
        presenter.save();
    }

    @Override
    public void loadStore() {
        presenter.loadStore();
    }

    @Override
    public void clearPrizesFile() {
        Serializable emptyItem = null;
        presenter.save(emptyItem);
    }

    @Override
    public void getPrize() {
        presenter.getPrize();
    }

    @Override
    public void showPrizesInFile() {
        presenter.showPrizesInFile();
    }

    @Override
    public void changeQuantity() {
        boolean check = true;
        int id = 0;
        int newQuantity = 0;
        System.out.println("Enter an id of an item to change: ");
        while (check){
            try {
                id = Integer.parseInt(scanner.nextLine());
            } catch (Exception e){
                System.out.println("Incorrect id. Please, enter correct id.\n");
            }
            if(checkID(id)){
                check = false;
            } else {
                System.out.println("There is no id with the number: " + id);
                System.out.println("Please, enter correct id: ");
            }
        }

        check = true;
        System.out.println("Enter a new quantity: ");
        while (check){
            try{
                newQuantity = Integer.parseInt(scanner.nextLine());
                check = false;
            } catch (Exception e){
                System.out.println("Incorrect quantity. Please, enter correct quantity:\n");
                scanner.nextLine(); // Consume the invalid input to avoid an infinite loop
            }
        }

        presenter.changeQuantity(id, newQuantity);
    }

    @Override
    public void deleteItem() {
        boolean check = true;
        int id = 0;
        System.out.println("Enter an id of an item to delete: ");
        while (check){
            try {
                id = Integer.parseInt(scanner.nextLine());
            } catch (Exception e){
                System.out.println("Incorrect id. Please, enter correct id.\n");
            }
            if(checkID(id)){
                presenter.deleteItem(id);
                check = false;
            } else {
                System.out.println("There is no id with the number: " + id);
                System.out.println("Please, enter correct id: ");
            }
        }
    }

    private boolean checkID(int id){
        storeItems = presenter.getStoreItems();
        List<Integer> idList = new ArrayList<>();

        for (StoreItem item: storeItems){
            idList.add(item.getId());
        }
        return idList.contains(id);
    }
}
