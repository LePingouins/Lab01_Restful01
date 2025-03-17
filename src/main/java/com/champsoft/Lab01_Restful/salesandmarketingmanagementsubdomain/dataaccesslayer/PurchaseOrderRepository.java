package com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.dataaccesslayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
    PurchaseOrder findPurchaseOrderByPurchaseOrderIdentifierPurchaseId(String pOrderId);
}
