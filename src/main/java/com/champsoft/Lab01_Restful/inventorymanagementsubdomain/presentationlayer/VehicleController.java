package com.champsoft.Lab01_Restful.inventorymanagementsubdomain.presentationlayer;

import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.businesslogiclayer.InventoryVehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("api/v1/vehicles")
public class VehicleController {
    private final InventoryVehicleService inventoryVehicleService;
    public VehicleController(InventoryVehicleService inventoryVehicleService) {
        this.inventoryVehicleService = inventoryVehicleService;
    }

    @GetMapping()
    public ResponseEntity<List<VehicleResponseModel>> getVehiclesWithFilter(
            Map<String, String> queryParams) {
        return ResponseEntity.ok().body(
                inventoryVehicleService.getVehicles(queryParams));
    }
    @GetMapping("/{vin}")
    public ResponseEntity<VehicleResponseModel> getVehicleInInventoryByVIN(
            @PathVariable String vin) {
        return ResponseEntity.ok().body(inventoryVehicleService.getInventoryVehicleByVIN( vin) );
    }

}
