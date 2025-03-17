package com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.inventory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    Inventory findInventoryByInventoryIdentifier(InventoryIdentifier inventoryIdentifier);
    Inventory findByInventoryIdentifier_InventoryId(String inventoryId);
    Boolean existsByInventoryIdentifier_InventoryId(String inventoryId);
}
