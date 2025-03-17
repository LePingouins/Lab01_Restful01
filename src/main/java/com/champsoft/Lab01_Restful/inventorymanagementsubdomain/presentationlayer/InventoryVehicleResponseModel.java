package com.champsoft.Lab01_Restful.inventorymanagementsubdomain.presentationlayer;

import lombok.*;

import java.util.List;

@Data
//@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class InventoryVehicleResponseModel {
     String inventoryId;  // From InventoryIdentifier
     String type;         // Inventory type
    List<VehicleResponseModel> availableVehicles;
}
