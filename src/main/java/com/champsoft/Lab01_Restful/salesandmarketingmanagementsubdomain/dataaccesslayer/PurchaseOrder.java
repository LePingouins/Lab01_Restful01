package com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.dataaccesslayer;

import com.champsoft.Lab01_Restful.clientmanagementsubdomain.dataaccesslayer.CustomerIdentifier;
import com.champsoft.Lab01_Restful.employeemanagementsubdomain.dataaccesslayer.EmployeeIdentifier;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.inventory.InventoryIdentifier;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.vehicle.VehicleIdentifier;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Table(name="sales")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //private id
    @Embedded
    private PurchaseOrderIdentifier purchaseOrderIdentifier;
    @Enumerated(EnumType.STRING)
    private Status saleStatus;
    @Embedded
    private FinancingAgreementDetails financingAgreementDetails;
    private LocalDate saleOfferDate;
    @Embedded
    private Price price;
    @Embedded
    private Warranty warranty;
    @Embedded
    private InventoryIdentifier inventoryIdentifier;
    @Embedded
    private VehicleIdentifier vehicleIdentifier;
    @Embedded
    private CustomerIdentifier customerIdentifier;
    @Embedded
    private EmployeeIdentifier employeeIdentifier;
}
