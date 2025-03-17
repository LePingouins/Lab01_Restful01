package com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.presentationlayer;


import com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.dataaccesslayer.FinancingAgreementDetails;
import com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.dataaccesslayer.Status;
import com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.dataaccesslayer.Warranty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class PurchaseResponseModel {
    private String purchaseOrderId;
    private String inventoryId;
    private String vehicleIdentificationNumber;
    private String customerId;
    private String employeeId;
    private BigDecimal salePrice;
    private String currency;
    private String payment_currency;
    private LocalDate saleOfferDate;
    private Status saleStatus;
    private FinancingAgreementDetails financingAgreementDetails;
    private Warranty warranty;
}
