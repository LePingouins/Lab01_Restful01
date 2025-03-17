package com.champsoft.Lab01_Restful.inventorymanagementsubdomain.datamapperlayer;

import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.vehicle.Vehicle;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.presentationlayer.VehicleResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel ="spring")

public interface VehicleResponseMapper {
    @Mapping(expression = "java(vehicle.getVehicleIdentifier().getVin())", target = "vehicleId")
    @Mapping(expression = "java(vehicle.getInventoryIdentifier().getInventoryId())", target = "inventoryId")
    @Mapping(source = "bodyClass", target = "bodyClass")
    VehicleResponseModel entityToResponseModel(Vehicle vehicle);
    List<VehicleResponseModel> entityListToResponseModelList(List<Vehicle> vehicle);

}
