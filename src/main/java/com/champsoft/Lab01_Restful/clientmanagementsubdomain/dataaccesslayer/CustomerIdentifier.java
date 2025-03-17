package com.champsoft.Lab01_Restful.clientmanagementsubdomain.dataaccesslayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Embeddable  // Marks it as a value object to be embedded in an entity
@Getter
@Setter
@NoArgsConstructor


public class CustomerIdentifier {

    @Column(name="customer_id", nullable=false, unique=true)
    private String customerId;

    public CustomerIdentifier(String customerId) {
        this.customerId = (customerId != null && !customerId.isEmpty()) ? customerId : UUID.randomUUID().toString();
    }

}
