package com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.presentationlayer;


import com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.dataaccesslayer.FinancingAgreementDetails;
import com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.dataaccesslayer.Status;
import com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.dataaccesslayer.Warranty;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PurchaseRequestModel {
    private String inventoryId;
    private String vehicleIdentificationNumber;
    private String customerId;
    private String employeeId;
    private Double salePrice;
    private String currency;
    private String payment_currency;
    private LocalDate saleOfferDate;
    private Status saleStatus;
    private FinancingAgreementDetails financingAgreementDetails;
    private Warranty warranty;

}
