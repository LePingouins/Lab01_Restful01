package com.champsoft.Lab01_Restful.clientmanagementsubdomain.dataaccesslayer;

import jakarta.persistence.Embeddable;
import lombok.*;


@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class Address {
    private String streetAddress;
    private String postalCode;
    private String city;
    private String province;
}
