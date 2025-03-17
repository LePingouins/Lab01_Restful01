package com.champsoft.Lab01_Restful.inventorymanagementsubdomain.datamapperlayer;

import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.inventory.Inventory;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.presentationlayer.InventoryResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel ="spring")

public interface InventoryResponseMapper {
    @Mapping(expression = "java(inventory.getInventoryIdentifier().getInventoryId())", target = "inventoryId")
    InventoryResponseModel entityToResponseModel(Inventory inventory);
    List<InventoryResponseModel> entityListToResponseModelList(List<Inventory> inventories);
}
