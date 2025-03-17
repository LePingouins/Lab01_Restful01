package com.champsoft.Lab01_Restful.inventorymanagementsubdomain.presentationlayer;

import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.businesslogiclayer.InventoryVehicleService;
import com.champsoft.Lab01_Restful.utils.InvalidInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/v1/inventories/{inventoryId}/vehicles")

public class InventoryVehicleController {
    private final InventoryVehicleService inventoryVehicleService;

    public InventoryVehicleController(InventoryVehicleService inventoryVehicleService) {
        this.inventoryVehicleService = inventoryVehicleService;
    }

    @GetMapping()
    public ResponseEntity<List<VehicleResponseModel>> getVehiclesInInventoryWithFiltering(
            @PathVariable String inventoryId, @RequestParam Map<String, String> queryParams) {
        return ResponseEntity.ok().body(
                inventoryVehicleService.getVehiclesInInventoryWithFiltering(inventoryId, queryParams));
    }

    @GetMapping("/{vin}")
    public ResponseEntity<VehicleResponseModel> getVehicleInInventoryByVIN(
            @PathVariable String vin) {
        return ResponseEntity.ok().body(inventoryVehicleService.getInventoryVehicleByVIN(vin));
    }

    @PostMapping
    public ResponseEntity<VehicleResponseModel> addVehicle(
            @PathVariable String inventoryId,
            @RequestBody VehicleRequestModel vehicleRequestModel
    ) {
        if (vehicleRequestModel.getVehicleId().length() != 17) {
            throw new InvalidInputException("Invalid VIN provided: " + vehicleRequestModel.getVehicleId());
        }
        return
                ResponseEntity.status(HttpStatus.CREATED).body(inventoryVehicleService.addVehicleToInventory(vehicleRequestModel,inventoryId));
    }

    @PutMapping("/{vin}")
    public ResponseEntity<VehicleResponseModel> updateVehicleInInventory(
            @PathVariable String inventoryId, @PathVariable String vin,
            @RequestBody VehicleRequestModel vehicleRequestModel
    ) {

        return ResponseEntity.ok().body(inventoryVehicleService.updateVehicleInInventory(vehicleRequestModel,
                inventoryId, vin));
    }

    @DeleteMapping("/{vin}")
    ResponseEntity<Void> removeVehicleFromInventory(
            @PathVariable String inventoryId, @PathVariable String vin) {
        inventoryVehicleService.removeVehicleFromInventory(inventoryId, vin);
        return ResponseEntity.noContent().build();
    }
}

