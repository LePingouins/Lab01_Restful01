package com.champsoft.Lab01_Restful.inventorymanagementsubdomain.businesslogiclayer;

import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.presentationlayer.InventoryRequestModel;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.presentationlayer.InventoryResponseModel;

import java.util.List;

public interface InventoryService {
    List<InventoryResponseModel> getInventories();
    InventoryResponseModel addInventory(InventoryRequestModel inventoryRequestModel);
    InventoryResponseModel updateInventoryDetails(InventoryRequestModel inventoryRequestModel, String inventoryId);
    void deleteInventory(String inventoryId);
    InventoryResponseModel getInventoryById(String inventoryId);

}
