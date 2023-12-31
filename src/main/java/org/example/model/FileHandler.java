package org.example.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private String filePath;

    public FileHandler(String path){
        this.filePath = path;
    }

    public boolean save(Serializable data){
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath))){
            objectOutputStream.writeObject(data);
            return true;
        } catch (Exception e){
            System.out.println("Error saving the file. Please check the path to the file.");;
            return false;
        }
    }

    public Object read(){
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(filePath))){
            return objectInputStream.readObject();
        } catch (Exception e){
            System.out.println("Error reading the file. Please check the path to the file.");;
            return null;
        }
    }
}
