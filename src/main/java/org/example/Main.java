package org.example;

import org.example.view.ConsoleUI;
import org.example.view.View;

public class Main {
    public static void main(String[] args) {
        View view = new ConsoleUI();
        view.start();
    }
}