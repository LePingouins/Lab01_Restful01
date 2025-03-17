package com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.dataaccesslayer;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Warranty {
    @Column(name = "warranty_end_date")
    LocalDate endDate;
    @Column(name = "warranty_terms")
    String terms;

}
