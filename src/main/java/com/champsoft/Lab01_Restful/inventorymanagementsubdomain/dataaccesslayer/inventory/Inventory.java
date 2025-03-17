package com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.inventory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "inventories")
@AllArgsConstructor
@Data

public class Inventory {
    @Id
    @GeneratedValue(strategy =
            GenerationType.IDENTITY)
    private Integer id; //private id
    @Embedded
    private InventoryIdentifier inventoryIdentifier; //public id
    private String type;
    public Inventory() {
        this.inventoryIdentifier = new
                InventoryIdentifier();
    }
    public Inventory(String type) {
        this.inventoryIdentifier = new
                InventoryIdentifier();
        this.type = type;
    }
    public Inventory(String type, String inventoryId) {
        this.inventoryIdentifier = new InventoryIdentifier(inventoryId);
        this.type = type;
    }

}
