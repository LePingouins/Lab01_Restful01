package com.champsoft.Lab01_Restful.clientmanagementsubdomain.businesslogiclayer;



import com.champsoft.Lab01_Restful.clientmanagementsubdomain.dataaccesslayer.Customer;
import com.champsoft.Lab01_Restful.clientmanagementsubdomain.dataaccesslayer.CustomerIdentifier;
import com.champsoft.Lab01_Restful.clientmanagementsubdomain.dataaccesslayer.CustomerRepository;
import com.champsoft.Lab01_Restful.clientmanagementsubdomain.datamapperlayer.CustomerRequestMapper;
import com.champsoft.Lab01_Restful.clientmanagementsubdomain.datamapperlayer.CustomerResponseMapper;
import com.champsoft.Lab01_Restful.clientmanagementsubdomain.presentationlayer.CustomerRequestModel;
import com.champsoft.Lab01_Restful.clientmanagementsubdomain.presentationlayer.CustomerResponseModel;
import com.champsoft.Lab01_Restful.utils.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomerServiceImpl implements  CustomerService{
    private final CustomerRepository customerRepository;
    private final CustomerResponseMapper customerResponseMapper;
    private final CustomerRequestMapper customerRequestMapper;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               CustomerResponseMapper customerResponseMapper,
                               CustomerRequestMapper customerRequestMapper) {
        this.customerRepository = customerRepository;
        this.customerResponseMapper = customerResponseMapper;
        this.customerRequestMapper = customerRequestMapper;
    }
    @Override
    public List<CustomerResponseModel> getCustomers() {
        List<Customer> customers = this.customerRepository.findAll();
        customers.get(0).getCustomerAddress().getStreetAddress();
        List<CustomerResponseModel> customerResponseModels =
                this.customerResponseMapper.entityListToResponseModelList(customers);
        return customerResponseModels;
    }

    @Override
    public CustomerResponseModel getCustomerbyCustomerId(String customer_id) {
        Customer customer = this.customerRepository.findCustomerByCustomerIdentifier_CustomerId(customer_id);
        if (customer==null){
            throw new NotFoundException("Customer with " + customer_id + " not found.");
        } else{
            return this.customerResponseMapper.entityToResponseModel(customer);
        }

    }

    @Override
    public CustomerResponseModel addCustomer(CustomerRequestModel newCustomerData) {
        String pw1 = newCustomerData.getPassword1();
        String pw2 = newCustomerData.getPassword2();
        if(pw1==null) {  pw1=""; }
        if(pw2==null) {  pw2=""; }
        if (!pw1.equals(  pw2  )) {
           throw  new IllegalArgumentException("Passwords do not match.");
        }
        CustomerIdentifier customerIdentifier = new CustomerIdentifier(newCustomerData.getCustomerId());

        Customer foundCustomer = this.customerRepository.findCustomerByCustomerIdentifier_CustomerId(customerIdentifier.getCustomerId());
        if (foundCustomer != null) {
            throw  new IllegalArgumentException("Customer with " + newCustomerData.getCustomerId() + " already exists.");
        }

        Customer customer = this.customerRequestMapper.requestModelToEntity(newCustomerData);

        customer.setCustomerIdentifier(customerIdentifier);
        customer.setPassword(newCustomerData.getPassword1());

        Customer savedCustomer = this.customerRepository.save(customer);

        return this.customerResponseMapper.entityToResponseModel(savedCustomer);


    }

    @Override
    public CustomerResponseModel updateCustomer(String customerId, CustomerRequestModel newCustomerData) {
        String message = "";
        Customer savedCustomer = new Customer();
        CustomerIdentifier customerIdentifier = new CustomerIdentifier();
        customerIdentifier.setCustomerId(customerId);
        Customer foundCustomer = this.customerRepository.findCustomerByCustomerIdentifier_CustomerId(customerIdentifier.getCustomerId());
        if (foundCustomer==null){
            message = "Customer with id: " + customerId + " not found in repository.";
        } else {
            String pw1 = newCustomerData.getPassword1();
            String pw2 = newCustomerData.getPassword2();
            if(pw1==null) {  pw1=""; }       if(pw2==null) {  pw2=""; }
            if (  pw1.equals(pw2)  )    {
                    Customer customer = this.customerRequestMapper.requestModelToEntity(newCustomerData);
                    customer.setPassword(newCustomerData.getPassword1());
                    customer.setCustomerIdentifier(customerIdentifier); // important
                    customer.setId(foundCustomer.getId());  // important
                    savedCustomer = this.customerRepository.save(customer);
                    if (savedCustomer != null)
                        message = "Customer updated successfully.";
                    else
                        message = "Could not save new customer into repository.";

                } else
                message = "Entered passwords do not match!";
        }
        return this.customerResponseMapper.entityToResponseModel(savedCustomer);
    }

    @Override

    public String deleteCustomerbyCustomerId(String customerId) {
        String message = "";
        CustomerIdentifier customerIdentifier = new CustomerIdentifier();
        customerIdentifier.setCustomerId(customerId);
        Customer foundCustomer = this.customerRepository.findCustomerByCustomerIdentifier_CustomerId(customerIdentifier.getCustomerId());
        if (foundCustomer==null){
            message = "Customer with id: " + customerId + " not found in repository.";
        } else {

            this.customerRepository.delete(foundCustomer);
            message =  "Customer with id: " + customerId + " deleted successfully.";
        }
        return message;
    }

    // Helper methods
    public CustomerResponseModel fromEntityToModel(Customer customer){
        CustomerResponseModel customerResponseModel =
                new CustomerResponseModel();
        customerResponseModel.setFirstName(customer.getFirstName());
        customerResponseModel.setLastName(customer.getLastName());

        return customerResponseModel;
    }
    public List<CustomerResponseModel> fromEntityListToModelList(
            List<Customer> customers){

        List<CustomerResponseModel> customerResponseModels =
                new ArrayList<>();
        for (Customer c : customers) {
            customerResponseModels.add(fromEntityToModel(c));
        }
        return customerResponseModels;
    }
    @Override
    public CustomerResponseModel getCustomerByEmailAddress(String emailAddress){
        Customer customer = this.customerRepository.findCustomerByEmailAddress(emailAddress);
        if(customer==null){
            throw new NotFoundException("Customer with " + emailAddress + " not found.");

        }
        return this.customerResponseMapper.entityToResponseModel(customer);
    }

}

