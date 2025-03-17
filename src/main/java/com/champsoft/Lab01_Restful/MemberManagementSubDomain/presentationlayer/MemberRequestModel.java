package com.champsoft.Lab01_Restful.MemberManagementSubDomain.presentationlayer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MemberRequestModel {
    private String memberId;
    private String firstName;
    private String lastName;
    private String email;
    private String membershipType;
    private String street;
    private String city;
    private String province;
    private String country;
}
