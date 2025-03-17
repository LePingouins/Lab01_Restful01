package com.champsoft.Lab01_Restful.clientmanagementsubdomain.dataaccesslayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class PhoneNumber {
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private PhoneType type;
    @Column(name = "number")
    private String number;
}
