package com.champsoft.Lab01_Restful.MemberManagementSubDomain.dataaccesslayer;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberAddress {

    private String street;

    private String city;

    private String province;

    private String country;

}