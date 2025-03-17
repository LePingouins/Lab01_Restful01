package com.champsoft.Lab01_Restful.inventorymanagementsubdomain.presentationlayer;

import lombok.*;

@Data
//@Builder
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor

public class InventoryResponseModel {
    String inventoryId;
    String type;
}
