package com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.vehicle;

import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.inventory.InventoryIdentifier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="vehicles")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Embedded

    private VehicleIdentifier vehicleIdentifier;
    //////////////////// models many-to-one /////////////////////////
    @Embedded

    private InventoryIdentifier inventoryIdentifier;
    /////////////////////////////////////////////////////////////////
    @Enumerated(EnumType.STRING)
    @Column(name = "vehicle_status")
    private Status status;
    @Enumerated(EnumType.STRING)
    @Column(name = "usage_type")
    private UsageType usageType;
    @Column(name = "vehicle_year")
    private Integer year;
    private String manufacturer;
    private String make;
    private String model;
    @Column(name="body_class")
    private String bodyClass;
    //////////////////// models many-to-one /////////////////////////
/////////// Entity to value object /////////////////////////////////
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "vehicle_options", joinColumns =
    @JoinColumn(name = "vehicle_id"))
    private List<Option> options;
    @Embedded
    private Price price;

    public Vehicle(VehicleIdentifier vehicleIdentifier, Status status, UsageType usageType, List<Option> options, Price price) {
        this.vehicleIdentifier = vehicleIdentifier;
        this.status = status;
        this.usageType = usageType;
        this.options = options;
        this.price = price;
    }

    public Vehicle(VehicleIdentifier vehicleIdentifier, InventoryIdentifier inventoryIdentifier, Status status, UsageType usageType, Integer year, String manufacturer, String make, String model, String bodyClass, List<Option> options, Price price) {
        this.vehicleIdentifier = vehicleIdentifier;
        this.inventoryIdentifier = inventoryIdentifier;
        this.status = status;
        this.usageType = usageType;
        this.year = year;
        this.manufacturer = manufacturer;
        this.make = make;
        this.model = model;
        this.bodyClass = bodyClass;
        this.options = options;
        this.price = price;
    }

}
