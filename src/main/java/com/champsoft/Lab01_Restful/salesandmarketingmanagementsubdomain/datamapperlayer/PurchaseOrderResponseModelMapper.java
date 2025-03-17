package com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.datamapperlayer;

import com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.dataaccesslayer.PurchaseOrder;
import com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.presentationlayer.PurchaseResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PurchaseOrderResponseModelMapper {
    @Mapping(target = "payment_currency", expression =
            "java(purchaseOrder.getPrice().getPayment_currency().toString())")
    @Mapping(target = "currency", expression = "java(purchaseOrder.getPrice().getCurrency().toString())")
    @Mapping(target = "saleOfferDate", expression = "java(purchaseOrder.getSaleOfferDate())")
    @Mapping(target = "salePrice", expression = "java(purchaseOrder.getPrice().getAmount())")
    @Mapping(target = "vehicleIdentificationNumber", expression =
            "java(purchaseOrder.getVehicleIdentifier().getVin())")
    @Mapping(target = "purchaseOrderId", expression =
            "java(purchaseOrder.getPurchaseOrderIdentifier().getPurchaseId())")
    @Mapping(target = "inventoryId", expression = "java(purchaseOrder.getInventoryIdentifier().getInventoryId())")
    @Mapping(target = "employeeId", expression = "java(purchaseOrder.getEmployeeIdentifier().getEmployeeId())")
    @Mapping(target = "customerId", expression = "java(purchaseOrder.getCustomerIdentifier().getCustomerId())")
    public PurchaseResponseModel entityToResponseModel(PurchaseOrder purchaseOrder);
    public List<PurchaseResponseModel> entityToResponseModelList(List<PurchaseOrder> purchaseOrders);
}
