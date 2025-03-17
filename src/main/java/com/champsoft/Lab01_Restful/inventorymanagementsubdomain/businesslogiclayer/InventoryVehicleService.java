package com.champsoft.Lab01_Restful.inventorymanagementsubdomain.businesslogiclayer;

import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.presentationlayer.VehicleRequestModel;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.presentationlayer.VehicleResponseModel;

import java.util.List;
import java.util.Map;

public interface InventoryVehicleService {
    List<VehicleResponseModel> getVehiclesInInventoryWithFiltering(String inventoryId, Map<String, String> queryParams);
    VehicleResponseModel addVehicleToInventory(VehicleRequestModel vehicleRequestModel, String inventoryId);
    VehicleResponseModel updateVehicleInInventory(VehicleRequestModel vehicleRequestModel, String inventoryId, String vin);
    void removeVehicleFromInventory(String inventoryId, String vin);
    List<VehicleResponseModel> getVehicles(Map<String, String> queryParams);
    VehicleResponseModel getInventoryVehicleByVIN(String vin);



}
