package com.champsoft.Lab01_Restful.inventorymanagementsubdomain.presentationlayer;

import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.vehicle.Option;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.vehicle.Status;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.vehicle.UsageType;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Builder
public class VehicleRequestModel {
    private String vehicleId; // VIN (17 characters)
    private String inventoryId;
    private String make;
    private String model;
    private String bodyClass;
    private Integer year;
    private Status status; // Enum: AVAILABLE, SALE_PENDING, SOLD
    private UsageType usageType; // Enum: NEW, USED
    private String manufacturer;
    private BigDecimal msrp; // Manufacturer's Suggested Retail Price
    private BigDecimal cost; // Dealer cost
    private List<Option> options;

}
