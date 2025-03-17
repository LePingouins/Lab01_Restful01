package com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.presentationlayer;

import com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.businesslogiclayer.PurchaseOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sales")
public class PurchaseController {
    private final PurchaseOrderService purchaseOrderService;


    public PurchaseController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;

    }
    @GetMapping
    public ResponseEntity<List<PurchaseResponseModel>> getAllPurchaseOrders() {
        return new ResponseEntity<>(purchaseOrderService.getAllPurchaseOrders(), HttpStatus.OK);
    }

    @GetMapping("/{purchaseId}")
    public ResponseEntity<PurchaseResponseModel> getPurchaseOrderById(@PathVariable String purchaseId) {
        PurchaseResponseModel purchaseResponseModel = purchaseOrderService.getPurchaseOrderById(purchaseId);
        if (purchaseResponseModel != null) {
            return new ResponseEntity<>(purchaseResponseModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<PurchaseResponseModel> addPurchaseOrder(@RequestBody PurchaseRequestModel purchaseRequestModel) {
        PurchaseResponseModel purchaseResponseModel = purchaseOrderService.addPurchaseOrder(purchaseRequestModel);
        return new ResponseEntity<>(purchaseResponseModel, HttpStatus.CREATED);
    }
    @PutMapping("/{purchaseId}")
    public ResponseEntity<PurchaseResponseModel> updatePurchaseOrder(@PathVariable String purchaseId, @RequestBody PurchaseRequestModel purchaseRequestModel) {
        PurchaseResponseModel updatedPurchaseOrder = purchaseOrderService.updatePurchaseOrder(purchaseId, purchaseRequestModel);
        return  ResponseEntity.ok(purchaseOrderService.updatePurchaseOrder(purchaseId, purchaseRequestModel));
    }

    @DeleteMapping("/{purchaseId}")
    public ResponseEntity<Void> deletePurchaseOrder(@PathVariable String purchaseId) {
        purchaseOrderService.deletePurchaseOrder(purchaseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
