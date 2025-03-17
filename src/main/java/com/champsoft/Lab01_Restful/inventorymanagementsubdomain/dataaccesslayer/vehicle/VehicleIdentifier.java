package com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.vehicle;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable  // Marks it as a value object to be embedded in an entity
@Getter
@Setter
@NoArgsConstructor
public class VehicleIdentifier {

    private String vin;

    public VehicleIdentifier(String vin) {
        this.vin = vin;
    }
}
