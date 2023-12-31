package org.example.model;

import java.io.Serializable;
import java.util.*;

public class Service {
    private Store<Toy> myStore;
    private ToyBuilder builder;
    private String pathStore;
    private String pathPrizesFile;
    private String pathPrizesQueue;
    private Map<Integer, Long> mapOfStoreItems;
    private Queue<Toy> prizeItemsQueue;
    private List<StoreItem> prizeItemsList;
    private FileHandler fileHandler;
    private int id;

    public Service(){
        myStore = new Store<>();
        builder = new ToyBuilder();
        prizeItemsQueue = myStore.getPrizeItems();
        pathStore = "src\\main\\java\\org\\example\\model\\store.txt";
        pathPrizesFile = "src\\main\\java\\org\\example\\model\\prizes.txt";
        pathPrizesQueue = "src\\main\\java\\org\\example\\model\\prizesQueue.txt";
        id = 0;
    }

    public List<StoreItem> getPizeList(){
        fileHandler = new FileHandler(pathPrizesFile);
        prizeItemsList = (List<StoreItem>) fileHandler.read();
        if(prizeItemsList == null){
            prizeItemsList = new ArrayList<>();
        }
        return prizeItemsList;
    }

    public void createStoreItem(String name, int quantity, long dropoutRate){
        assignID(myStore);
        Toy toyItem = builder.build(id, name, quantity, dropoutRate);
        myStore.addStoreItem(toyItem);
    }

    public void assignID(Store<Toy> store){
        int maxID = 0;
        List<Toy> storeItemsList = store.getStoreItems();
        for(Toy item: storeItemsList){
            if (maxID < item.getId()){
                maxID = item.getId();
            }
        }
        id = maxID + 1;
    }

    public Store<Toy> getMyStore(){
        return myStore;
    }

    public String getStoreInfo(){
        return myStore.getInfo();
    }

    public List<? extends StoreItem> getStoreItems() {
        return myStore.getStoreItems();
    }

    public Map<Integer, Long> getItemsMap() {
        return myStore.getMapOfStoreItems();
    }

    public void save() {
        fileHandler = new FileHandler(pathStore);
        fileHandler.save(myStore);

        List<Toy> prizeItems = new ArrayList<>(prizeItemsQueue);
        fileHandler = new FileHandler(pathPrizesQueue);
        fileHandler.save((Serializable) prizeItems);
    }

    public void save(Serializable emptyItem){
        fileHandler = new FileHandler(pathPrizesFile);
        fileHandler.save(emptyItem);
    }

    public void loadStore(){
        fileHandler = new FileHandler(pathStore);
        myStore = (Store<Toy>) fileHandler.read();

        fileHandler = new FileHandler(pathPrizesQueue);
        List<Toy> loadedPrizeItems = (List<Toy>) fileHandler.read();
        prizeItemsQueue.addAll(loadedPrizeItems);

        System.out.println(getStoreInfo());
        System.out.println("Items in the prize queue: " + prizeItemsQueue.size());
        for(Toy item: prizeItemsQueue){
            System.out.println("ID: " + item.getId() + ", Name: " + item.getName());
        }
        System.out.println();
    }

    // A weighted random choice function is used in the runLottery method
    public void runLottery() {
        Random random = new Random();
        long totalWeight = 0;
        long randomNumber = 0;

        mapOfStoreItems = myStore.getMapOfStoreItems();

        // Check if there are any items with a non-zero quantity
        if (mapOfStoreItems.values().stream().anyMatch(weight -> weight > 0)) {
            for (Map.Entry<Integer, Long> entry : mapOfStoreItems.entrySet()) {
                totalWeight += entry.getValue();
            }

        randomNumber = random.nextLong(totalWeight);

        //Iterate over the items and their weights, and find the item whose weight is closest to the random number
        boolean addedToQueue = false;
        for (Map.Entry<Integer, Long> entry : mapOfStoreItems.entrySet()) {
            long itemWeight = entry.getValue();
            if(randomNumber < itemWeight){
                Toy prizeItem = myStore.getByID(entry.getKey());
                if(checkQuantity(prizeItem)){
                    int quantity = prizeItem.getQuantity();
                    prizeItem.setQuantity(quantity - 1);
                    prizeItemsQueue.add(prizeItem);
                    addedToQueue = true;
                }
                break; // exit the loop after adding an item
            }
        }

        // If randomNumber is greater than any weight, add the last item to the queue
        if(!addedToQueue && !mapOfStoreItems.isEmpty()){
            Map.Entry<Integer, Long> lastEntry = null;
            for(Map.Entry<Integer, Long> entry : mapOfStoreItems.entrySet()){
                lastEntry = entry;
            }
            if(lastEntry != null){
                Toy prizeItem = myStore.getByID(lastEntry.getKey());
                if(checkQuantity(prizeItem)){
                    int quantity = prizeItem.getQuantity();
                    prizeItem.setQuantity(quantity - 1);
                    prizeItemsQueue.add(myStore.getByID(lastEntry.getKey()));
                }
            }
        }

        System.out.println("Selected random number: " + randomNumber);
        System.out.println("Items in the prize queue:\n" + prizeItemsQueue.size());
        for(Toy item: prizeItemsQueue){
            System.out.println(item);
        }
            System.out.println("\n");
    } else {
            System.out.println("All items have a quantity of 0. Skipping lottery.\n");
        }
    }

    private boolean checkQuantity(Toy toy){
        return toy.getQuantity() > 0;
    }

    public void getPrize(){
        if(!prizeItemsQueue.isEmpty()){
            Toy prizeItem = prizeItemsQueue.poll(); // Remove the first item from the list
            getPizeList().add(prizeItem);
            fileHandler = new FileHandler(pathPrizesFile);
            fileHandler.save((Serializable) prizeItemsList);

            System.out.println("Items in the prize queue: \n" + prizeItemsQueue.size() + "\n");
            showPrizesInFile();
            save();
        } else {
            System.out.println("There is no items in the prize queue.\n");
        }
    }

    private void printMapItems(Map<Integer, Long> map){
        for(Map.Entry<Integer, Long> entry: map.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public void getItemByID(int id) {
        myStore.getByID(id);
    }

    public void showPrizesInFile() {
        fileHandler = new FileHandler(pathPrizesFile);

        List<StoreItem> listToPrint = getPizeList();

        if(!listToPrint.isEmpty()){
            System.out.println("Items in the prize file: " + listToPrint.size());
            for(StoreItem item: listToPrint){
                System.out.println(item);
            }
        } else {
            System.out.println("No items in the prize file.");
        }
        System.out.println();
    }

    public void changeQuantity(int id, int quantity) {
        Toy itemToChange = myStore.getByID(id);
        itemToChange.setQuantity(quantity);
        System.out.println(itemToChange + "\n");
    }

    public void deleteItem(int id) {
        myStore.removeStoreItem(id);
    }
}
