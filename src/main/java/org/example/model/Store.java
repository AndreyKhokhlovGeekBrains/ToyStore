package org.example.model;

import java.io.Serializable;
import java.util.*;

public class Store <I extends StoreItem> implements Serializable {
    private int maxID = 0;
    private List<I> storeItems;
    private Map<Integer, Long> mapOfStoreItems;
    private Queue<I> prizeItemsQueue;

    public Store() {
        this(new ArrayList<>());
        prizeItemsQueue = new LinkedList<>();
    }

    public Store(List<I> toyItems) {
        this.storeItems = toyItems;
    }

    public List<I> getStoreItems() {
        return storeItems;
    }

    public Queue<I> getPrizeItems(){
        return prizeItemsQueue;
    }

    public void setPrizeItems(Queue<I> prizeItems){
        this.prizeItemsQueue = prizeItems;
    }

    private void createMapOfStoreItems(List<I> storeItemsList) {
        this.mapOfStoreItems = new HashMap<>();
        for (StoreItem item : storeItemsList) {
            if(item.getQuantity() > 0){
                mapOfStoreItems.put(item.getId(), item.getDropoutRate());
            }
        }
    }

    public Map<Integer, Long> getMapOfStoreItems() {
        createMapOfStoreItems(storeItems);
        return mapOfStoreItems;
    }

    public boolean addStoreItem(I toy) {
        if (toy == null) {
            return false;
        }
        if (!storeItems.contains(toy)) {
            storeItems.add(toy);
            return true;
        }
        return false;
    }

    private void checkMaxID() {
        for (StoreItem item : storeItems) {
            if (maxID < item.getId()) {
                maxID = item.getId();
            }
        }
    }

    public I getByID(int id) {
        if (checkID(id)) {
            for (I item : storeItems) {
                if (item.getId() == id) {
                    return item;
                }
            }
        }
        return null;
    }

    private boolean checkID(int id) {
        checkMaxID();
        return id >= 1 && id <= maxID;
    }

    public I getByName(String name) {
        for (I item : storeItems) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }

    public boolean removeItem(int id) {
        if (checkID(id)) {
            I item = getByID(id);
            return storeItems.remove(item);
        }
        return false;
    }

    public String getInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Items in the store: ")
                .append(storeItems.size())
                .append("\n");
        for (I item : storeItems) {
            sb.append(item)
                    .append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return getInfo();
    }

    public void removeStoreItem(int id) {
        Iterator<I> iterator = storeItems.iterator();
        while (iterator.hasNext()){
            I item = iterator.next();
            if(item.getId() == id){
                iterator.remove();
                break;
            }
        }
        System.out.println();
    }
}