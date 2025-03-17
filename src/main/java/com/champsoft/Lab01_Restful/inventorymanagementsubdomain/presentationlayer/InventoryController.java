package com.champsoft.Lab01_Restful.inventorymanagementsubdomain.presentationlayer;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.businesslogiclayer.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/inventories")
public class InventoryController {
    private final InventoryService inventoryService;
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping()
    public ResponseEntity<List<InventoryResponseModel>> getInventories() {
        return ResponseEntity.ok().body(
                inventoryService.getInventories());
    }
    @GetMapping("/{inventoryId}")
    public ResponseEntity<InventoryResponseModel> getInventoryById(@PathVariable String inventoryId) {
        return ResponseEntity.ok().body(inventoryService.getInventoryById(inventoryId));
    }
    //add new inventory
    @PostMapping()
    public ResponseEntity<InventoryResponseModel> addInventory(@RequestBody InventoryRequestModel
                                                                       inventoryRequestModel) {
        InventoryResponseModel rm = inventoryService.addInventory(inventoryRequestModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(rm);
    }
    //update an inventory's details - this only updates inventory, not vehicle
    @PutMapping("/{inventoryId}")
    public ResponseEntity<InventoryResponseModel> updateInventoryDetails(@RequestBody InventoryRequestModel
                                                                                 inventoryRequestModel, @PathVariable String inventoryId) {
        return ResponseEntity.ok().body(inventoryService.updateInventoryDetails(inventoryRequestModel, inventoryId));
    }
    //delete an inventory - note - this will delete all vehicles in that inventory - this is an aggregate!
    @DeleteMapping("/{inventoryId}")
    ResponseEntity<Void> deleteInventory(@PathVariable String inventoryId) {
        inventoryService.deleteInventory(inventoryId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    //TO-DO:
    //might be good to implement a transfer method - transfer a car from one inventory to another.
}


