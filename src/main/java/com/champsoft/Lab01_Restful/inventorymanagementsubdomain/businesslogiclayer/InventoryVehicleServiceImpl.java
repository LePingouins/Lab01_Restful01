package com.champsoft.Lab01_Restful.inventorymanagementsubdomain.businesslogiclayer;

import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.inventory.Inventory;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.inventory.InventoryRepository;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.vehicle.*;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.datamapperlayer.InventoryVehicleResponseMapper;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.datamapperlayer.VehicleRequestMapper;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.datamapperlayer.VehicleResponseMapper;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.presentationlayer.VehicleRequestModel;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.presentationlayer.VehicleResponseModel;
import com.champsoft.Lab01_Restful.utils.DuplicateVinException;
import com.champsoft.Lab01_Restful.utils.InvalidInputException;
import com.champsoft.Lab01_Restful.utils.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class InventoryVehicleServiceImpl implements InventoryVehicleService {
    private final VehicleRepository vehicleRepository;
    private final InventoryRepository inventoryRepository;
    private final VehicleRequestMapper vehicleRequestMapper;
    private final VehicleResponseMapper vehicleResponseMapper;
    private final InventoryVehicleResponseMapper inventoryVehicleResponseMapper;

    @Autowired
    public InventoryVehicleServiceImpl(VehicleRepository vehicleRepository, InventoryRepository inventoryRepository, VehicleRequestMapper vehicleRequestMapper, VehicleResponseMapper vehicleResponseMapper, InventoryVehicleResponseMapper inventoryVehicleResponseMapper) {
        this.vehicleRepository = vehicleRepository;
        this.inventoryRepository = inventoryRepository;
        this.vehicleRequestMapper = vehicleRequestMapper;
        this.vehicleResponseMapper = vehicleResponseMapper;
        this.inventoryVehicleResponseMapper = inventoryVehicleResponseMapper;
    }


    @Override
    public List<VehicleResponseModel> getVehiclesInInventoryWithFiltering(
            String inventoryId,
            Map<String, String> queryParams) {
        //extract the query parameters
        //String inventoryId = queryParams.get("inventoryId");
        String status = queryParams.get("status");
        String usageType = queryParams.get("usage");
        if (!inventoryRepository.existsByInventoryIdentifier_InventoryId(inventoryId)) {
            throw new InvalidInputException("Invalid inventoryId provided: " + inventoryId);
        }
        //convert to Enums
        Map<String, Status> statusMap = new HashMap<String, Status>();
        statusMap.put("available", Status.AVAILABLE);
        statusMap.put("sale_pending", Status.SALE_PENDING);
        statusMap.put("sold", Status.SOLD);
        Map<String, UsageType> usageMap = new HashMap<String, UsageType>();
        usageMap.put("new", UsageType.NEW);
        usageMap.put("used", UsageType.USED);
        //case where both parameters are present
        if (status != null & usageType != null) {
            return vehicleResponseMapper.entityListToResponseModelList(
                    vehicleRepository
                            .findAllByInventoryIdentifier_InventoryIdAndStatusEqualsAndUsageTypeEquals(inventoryId,
                                    statusMap.get(status.toLowerCase()),
                                    usageMap.get(usageType.toLowerCase())));
        }
        //case where only status is present
        if (status != null) {
            return
                    vehicleResponseMapper.entityListToResponseModelList(vehicleRepository.findAllByInventoryIdentifier_InventoryIdAndStatusEquals(inventoryId, statusMap.get(status.toLowerCase())));
        }
        //case where only usageType is present
        if (usageType != null) {
            return
                    vehicleResponseMapper.entityListToResponseModelList(vehicleRepository.findAllByInventoryIdentifier_InventoryIdAndUsageTypeEquals(inventoryId, usageMap.get(usageType.toLowerCase())));
        }
        //case where no query parameters are present; return all vehicles in inventory
        return vehicleResponseMapper.entityListToResponseModelList(
                vehicleRepository.findAllByInventoryIdentifier_InventoryId(inventoryId));
    }

    @Override
    public VehicleResponseModel addVehicleToInventory(VehicleRequestModel vehicleRequestModel, String inventoryId) {
        //validate provided inventoryId
        Inventory existingInventory = inventoryRepository.findByInventoryIdentifier_InventoryId(inventoryId);

        if (existingInventory == null) {
            throw new InvalidInputException("Invalid inventoryId provided: " + inventoryId);
        }
        //convert request model to an entity and save it
        //create value objects

        VehicleIdentifier vehicleIdentifier =
                new VehicleIdentifier(vehicleRequestModel.getVehicleId());
        //calculate options cost
        //double totalOptionsCost = vehicleRequestModel.getOptions().stream().mapToDouble(Option::getCost).sum();

        BigDecimal totalOptionsCost = vehicleRequestModel.getOptions().stream()
                .map(Option::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Price price = new Price(vehicleRequestModel.getMsrp(), vehicleRequestModel.getCost(), totalOptionsCost);
        Vehicle vehicle = vehicleRequestMapper.requestModelToEntity(
                vehicleRequestModel, vehicleIdentifier,
                existingInventory.getInventoryIdentifier(),
                price);

        vehicle.setInventoryIdentifier(existingInventory.getInventoryIdentifier());

        try {
            return vehicleResponseMapper.entityToResponseModel(vehicleRepository.save(vehicle));
        } catch (DataAccessException ex) {
            if (ex.getMessage().contains("constraint [vin]")) {
                throw new DuplicateVinException("Inventory already contains a vehicle with vin: " + vehicleRequestModel.getVehicleId());
            } else throw new InvalidInputException("An unknown error has occurred.");
        }

    }

    @Override
    public VehicleResponseModel updateVehicleInInventory(VehicleRequestModel vehicleRequestModel, String
            inventoryId, String vin) {
        //validate provided inventoryId
        Inventory existingInventory = inventoryRepository.findByInventoryIdentifier_InventoryId(inventoryId);
        if (existingInventory == null) {
            throw new InvalidInputException("Invalid inventoryId provided: " + inventoryId);
        }
        //validate provided vehicle vin
        Vehicle existingVehicle = vehicleRepository.findByVehicleIdentifier_Vin(vin);
        if (existingVehicle == null) {
            throw new NotFoundException("Unknown vehicleId provided: " + vin);
        }
        //re-create value objects and convert to Vehicle
        //calculate options cost
        //BigDecimal totalOptionsCost = BigDecimal.valueOf(vehicleRequestModel.getOptions().stream().mapToDouble(Option::getCost).sum());
        BigDecimal totalOptionsCost = vehicleRequestModel.getOptions().stream()
                .map(Option::getCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        Price price = new Price(vehicleRequestModel.getMsrp(), vehicleRequestModel.getCost(), totalOptionsCost);
        Vehicle vehicle = vehicleRequestMapper.requestModelToEntity(
                vehicleRequestModel,
                existingVehicle.getVehicleIdentifier(),
                existingInventory.getInventoryIdentifier(),
                price);
        //update updatedVehicle with private database id
        vehicle.setId(existingVehicle.getId());
        vehicle.setInventoryIdentifier(existingVehicle.getInventoryIdentifier());
        //System.out.println("Before save: " + vehicle);
      //  System.out.println("Inventory ID: " + vehicle.getInventoryIdentifier().getInventoryId());

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
       // System.out.println("After save: " + savedVehicle);
       // System.out.println("Saved Inventory ID: " + savedVehicle.getInventoryIdentifier().getInventoryId());
        return vehicleResponseMapper.entityToResponseModel(savedVehicle);
    }

    @Override
    public void removeVehicleFromInventory(String inventoryId, String vin) {
        if (!inventoryRepository.existsByInventoryIdentifier_InventoryId(inventoryId)) {
            throw new NotFoundException("Inventory not found: " + inventoryId);
        }

        Vehicle vehicle = vehicleRepository.findByVehicleIdentifier_Vin(vin);
        if (vehicle == null) {
            throw new NotFoundException("Vehicle not found: " + vin);
        }
        vehicleRepository.delete(vehicle);

    }

    @Override
    public List<VehicleResponseModel> getVehicles(Map<String, String> queryParams) {
        return vehicleResponseMapper.entityListToResponseModelList(
                vehicleRepository.findAll()
        );
    }

    @Override
    public VehicleResponseModel getInventoryVehicleByVIN(String vin) {
        Vehicle vehicle = (vehicleRepository.findByVehicleIdentifier_Vin(vin));
        if (vehicle == null) {
            throw new NotFoundException("Vehicle not found: " + vin);
        }
        return vehicleResponseMapper.entityToResponseModel(vehicle);
    }
}
