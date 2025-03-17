package com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.dataaccesslayer;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.util.UUID;

@Embeddable
@Getter
@Data
@Builder

public class PurchaseOrderIdentifier {
    private String purchaseId;
    public PurchaseOrderIdentifier() {
        this.purchaseId = UUID.randomUUID().toString();
    }
    public PurchaseOrderIdentifier(String purchaseId) {
        this.purchaseId = purchaseId;
    }
}
