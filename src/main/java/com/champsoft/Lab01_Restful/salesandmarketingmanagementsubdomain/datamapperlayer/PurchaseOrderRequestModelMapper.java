package com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.datamapperlayer;

import com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.dataaccesslayer.PurchaseOrder;
import com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.presentationlayer.PurchaseRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")

public interface PurchaseOrderRequestModelMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "vehicleIdentifier", ignore = true)
    @Mapping(target = "purchaseOrderIdentifier", ignore = true)
    @Mapping(target = "inventoryIdentifier", ignore = true)
    @Mapping(target = "employeeIdentifier", ignore = true)
    @Mapping(target = "customerIdentifier", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "saleOfferDate", expression = "java(purchaseRequestModel.getSaleOfferDate())")
    public PurchaseOrder requestModelToEntity(PurchaseRequestModel purchaseRequestModel);
}
