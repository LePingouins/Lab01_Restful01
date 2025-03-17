package com.champsoft.Lab01_Restful.MemberManagementSubDomain.dataaccesslayer;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="members")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Private identifier

    @Embedded
    private MemberIdentifier memberIdentifier;

    private String firstName;
    private String lastName;
    private String email;
    private String membershipType;

    @Embedded
    private MemberAddress memberAddress;
}
