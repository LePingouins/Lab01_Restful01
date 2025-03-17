package com.champsoft.Lab01_Restful.clientmanagementsubdomain.dataaccesslayer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends
        JpaRepository<Customer, Integer> {
    Customer findCustomerByCustomerIdentifier_CustomerId(String customerId);
    Customer findCustomerByEmailAddress(String emailAddress);
}
