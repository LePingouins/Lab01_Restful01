package com.champsoft.Lab01_Restful.clientmanagementsubdomain.datamapperlayer;

import com.champsoft.Lab01_Restful.clientmanagementsubdomain.dataaccesslayer.Customer;
import com.champsoft.Lab01_Restful.clientmanagementsubdomain.presentationlayer.CustomerResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerResponseMapper {


    //  @Mapping(target = "phoneType", expression = "java(  customer.getPhoneNumber().getType().toString() )")
   //@Mapping(target = "phoneNumber", expression = "java(  customer.getPhoneNumber().getNumber() )")
    @Mapping(target = "customerId", source = "customerIdentifier.customerId")
    @Mapping(target = "streetAddress", source = "customerAddress.streetAddress")
    @Mapping(target = "province", source = "customerAddress.province")
    @Mapping(target = "postalCode", source = "customerAddress.postalCode")
    @Mapping(target = "city", source = "customerAddress.city")
    @Mapping(target = "phoneNumbers", source = "phoneNumbers")

    CustomerResponseModel entityToResponseModel(Customer customer);
    List<CustomerResponseModel> entityListToResponseModelList(List<Customer> customers);
}
