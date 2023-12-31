package org.example.presenter;

import org.example.model.Service;
import org.example.model.StoreItem;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Presenter {
    private Service service;

    public Presenter(){
        service = new Service();
    }

    public void addItem(String name, int quantity, long dropoutRate){
        service.createStoreItem(name, quantity, dropoutRate);
    }

    public void printStoreItems(){
        System.out.println(service.getStoreInfo());
    }

    public List<? extends StoreItem> getStoreItems() {
        return service.getStoreItems();
    }

    public Map<Integer, Long> getItemsMap() {
        return service.getItemsMap();
    }

    public void save() {
        service.save();
    }

    public void save(Serializable emptyItem){
        service.save(emptyItem);
    }

    public void loadStore() {
        service.loadStore();
    }

    public void runLottery() {
        service.runLottery();
    }

    public void getItemByID(int id) {
        service.getItemByID(id);
    }

    public void getPrize() {
        service.getPrize();
    }

    public void showPrizesInFile() {
        service.showPrizesInFile();
    }

    public void changeQuantity(int id, int quntity) {
        service.changeQuantity(id, quntity);
    }

    public void deleteItem(int id){
        service.deleteItem(id);
    }
}
