package org.example.model;

import java.io.Serializable;

public interface StoreItem extends Serializable {
    int getId();

    String getName();

    int getQuantity();

    long getDropoutRate();
}
