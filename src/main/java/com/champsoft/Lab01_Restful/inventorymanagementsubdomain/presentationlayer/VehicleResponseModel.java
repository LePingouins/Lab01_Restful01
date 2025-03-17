package com.champsoft.Lab01_Restful.inventorymanagementsubdomain.presentationlayer;

import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.vehicle.Option;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.vehicle.Price;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.vehicle.Status;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.vehicle.UsageType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class VehicleResponseModel {
    String vehicleId; // VIN from VehicleIdentifier
    String inventoryId; // From InventoryIdentifier
    String make;
    String model;
    Integer year;
    Status status;
    UsageType usageType;
    String manufacturer;
    String bodyClass;
    Price price;
    List<Option> options;
}
