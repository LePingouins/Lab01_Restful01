package com.champsoft.Lab01_Restful.clientmanagementsubdomain.datamapperlayer;

import com.champsoft.Lab01_Restful.clientmanagementsubdomain.dataaccesslayer.Customer;
import com.champsoft.Lab01_Restful.clientmanagementsubdomain.presentationlayer.CustomerRequestModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerRequestMapper {

    @Mapping(target = "customerIdentifier.customerId", source ="customerId" )
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "customerAddress.streetAddress", source = "streetAddress")
    @Mapping(target = "customerAddress.province", source ="province" )
    @Mapping(target = "customerAddress.postalCode", source = "postalCode")
    @Mapping(target = "customerAddress.city", source = "city" )
    @Mapping(target = "phoneNumbers", expression = "java(customerRequestModel.getPhoneNumbers())")
    Customer requestModelToEntity(CustomerRequestModel customerRequestModel);
    List<Customer> requestModelListToEntityList(List<CustomerRequestModel> customerRequestModel);


}
