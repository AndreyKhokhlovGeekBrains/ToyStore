package org.example.view;

public interface View {
    void start();
    void answer(String prompt);
    void addItem();
    void printStoreItems();
    void getItemByID(int id);
    void exit();
    void runLottery();
    void save();
    void loadStore();
    void clearPrizesFile();
    void getPrize();
    void showPrizesInFile();
    void changeQuantity();
    void deleteItem();
}
