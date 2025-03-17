package com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.businesslogiclayer;

import com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.presentationlayer.PurchaseRequestModel;
import com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.presentationlayer.PurchaseResponseModel;

import java.util.List;

public interface PurchaseOrderService {
    List<PurchaseResponseModel> getAllPurchaseOrders();

    PurchaseResponseModel getPurchaseOrderById(String pOrderId);
    PurchaseResponseModel addPurchaseOrder(PurchaseRequestModel prm);
    PurchaseResponseModel updatePurchaseOrder(String purchaseOrderId, PurchaseRequestModel purchaseRequestModel);

    void deletePurchaseOrder(String purchaseId);
}
