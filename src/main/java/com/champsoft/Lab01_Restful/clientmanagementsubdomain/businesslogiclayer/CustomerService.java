package com.champsoft.Lab01_Restful.clientmanagementsubdomain.businesslogiclayer;

import com.champsoft.Lab01_Restful.clientmanagementsubdomain.presentationlayer.CustomerRequestModel;
import com.champsoft.Lab01_Restful.clientmanagementsubdomain.presentationlayer.CustomerResponseModel;

import java.util.List;

public interface CustomerService {
    List<CustomerResponseModel> getCustomers();
    CustomerResponseModel getCustomerbyCustomerId(String customer_id);

    CustomerResponseModel addCustomer(CustomerRequestModel newCustomerData);

    CustomerResponseModel updateCustomer(String customerId, CustomerRequestModel newCustomerData);

    String deleteCustomerbyCustomerId(String customerId);
    CustomerResponseModel getCustomerByEmailAddress(String emailAddress);
}
