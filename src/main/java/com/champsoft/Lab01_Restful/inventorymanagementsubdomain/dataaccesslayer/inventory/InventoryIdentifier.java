package com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.inventory;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;
@Embeddable  // Marks it as a value object to be embedded in an entity
@Getter
@Setter



public class InventoryIdentifier {
    @Column(name = "inventory_id")

    private String inventoryId;

    public InventoryIdentifier() {
        this.inventoryId = UUID.randomUUID().toString();
    }

    // Constructor for existing IDs
    public InventoryIdentifier(String inventoryId) {
        this.inventoryId = inventoryId;
    }
}
