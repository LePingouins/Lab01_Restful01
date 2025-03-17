package com.champsoft.Lab01_Restful.inventorymanagementsubdomain.datamapperlayer;

import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.inventory.Inventory;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.presentationlayer.InventoryRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryRequestMapper {
    @Mapping(target = "id", ignore = true)
   // @Mapping(target = "inventoryIdentifier", ignore = true)
    Inventory requestModelToEntity(InventoryRequestModel requestModel);
}
