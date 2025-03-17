package com.champsoft.Lab01_Restful.inventorymanagementsubdomain.datamapperlayer;

import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.inventory.Inventory;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.presentationlayer.InventoryVehicleResponseModel;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.presentationlayer.VehicleResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InventoryVehicleResponseMapper {
    @Mapping(expression = "java(inventory.getInventoryIdentifier().getInventoryId())", target = "inventoryId")
    @Mapping(expression = "java(vehicles)", target = "availableVehicles")
    InventoryVehicleResponseModel entitiesToResponseModel(Inventory inventory, List<VehicleResponseModel>
            vehicles);

}
