package com.champsoft.Lab01_Restful.inventorymanagementsubdomain.datamapperlayer;

import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.inventory.InventoryIdentifier;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.vehicle.Price;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.vehicle.Vehicle;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.vehicle.VehicleIdentifier;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.presentationlayer.VehicleRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface VehicleRequestMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(expression = "java(vehicleIdentifier)", target = "vehicleIdentifier"),
            @Mapping(expression = "java(inventoryIdentifier)", target = "inventoryIdentifier"),
            @Mapping(expression = "java(price)", target = "price"),
    })
    Vehicle requestModelToEntity(VehicleRequestModel vehicleRequestModel,
                                 VehicleIdentifier vehicleIdentifier,
                                 InventoryIdentifier inventoryIdentifier,
                                 Price price);

}
