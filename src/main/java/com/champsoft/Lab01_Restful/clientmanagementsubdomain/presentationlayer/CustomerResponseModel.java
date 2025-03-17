package com.champsoft.Lab01_Restful.clientmanagementsubdomain.presentationlayer;


import com.champsoft.Lab01_Restful.clientmanagementsubdomain.dataaccesslayer.PhoneNumber;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerResponseModel   {
    private String customerId; //public identifier
    private String lastName;
    private String firstName;
    private String emailAddress;

    private String streetAddress;
    private String postalCode;
    private String city;
    private String province;
//    private String phoneType;
//    private String phoneNumber;
private List<PhoneNumber> phoneNumbers;

}
