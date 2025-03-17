package com.champsoft.Lab01_Restful.inventorymanagementsubdomain.businesslogiclayer;

import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.inventory.Inventory;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.inventory.InventoryIdentifier;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.inventory.InventoryRepository;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.vehicle.Vehicle;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.vehicle.VehicleRepository;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.datamapperlayer.InventoryRequestMapper;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.datamapperlayer.InventoryResponseMapper;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.presentationlayer.InventoryRequestModel;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.presentationlayer.InventoryResponseModel;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.presentationlayer.VehicleResponseModel;
import com.champsoft.Lab01_Restful.utils.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    private final VehicleRepository vehicleRepository;
    private final InventoryRequestMapper inventoryRequestMapper;
    private final InventoryResponseMapper inventoryResponseMapper;
    @Autowired
    public InventoryServiceImpl(InventoryRepository inventoryRepository, VehicleRepository vehicleRepository, InventoryRequestMapper inventoryRequestMapper, InventoryResponseMapper inventoryResponseMapper) {
        this.inventoryRepository = inventoryRepository;
        this.vehicleRepository = vehicleRepository;
        this.inventoryRequestMapper = inventoryRequestMapper;
        this.inventoryResponseMapper = inventoryResponseMapper;


    }


    @Override
    public List<InventoryResponseModel> getInventories() {
        List<Inventory> inventories = inventoryRepository.findAll();
        return inventoryResponseMapper.entityListToResponseModelList(inventories);
    }

    @Override
    public InventoryResponseModel addInventory(InventoryRequestModel inventoryRequestModel) {
        log.debug("service - add Inventory");
        return inventoryResponseMapper.entityToResponseModel(inventoryRepository.save(inventoryRequestMapper.requestModelToEntity(inventoryRequestModel)));
    }

    @Override
    public InventoryResponseModel updateInventoryDetails(InventoryRequestModel inventoryRequestModel, String inventoryId) {
        Inventory existingInventory = inventoryRepository.findByInventoryIdentifier_InventoryId(inventoryId);

        if (existingInventory == null) {
            throw new NotFoundException("Inventory not found with ID: " + inventoryId);
        }
        Inventory updatedInventory = inventoryRequestMapper.requestModelToEntity(inventoryRequestModel);
        updatedInventory.setInventoryIdentifier(existingInventory.getInventoryIdentifier());
        updatedInventory.setId(existingInventory.getId());



        return inventoryResponseMapper.entityToResponseModel(inventoryRepository.save(updatedInventory));
    }

    @Override
    public void deleteInventory(String inventoryId) {
//get the actual inventory, because we need the entity in order to delete it.
        Inventory existingInventory = inventoryRepository.findByInventoryIdentifier_InventoryId(inventoryId);
        if (existingInventory == null) {
            throw new NotFoundException("Unknown inventoryId: " + inventoryId);
        }
//delete all the vehicles in the inventory
        List<Vehicle> vehicles = vehicleRepository.findAllByInventoryIdentifier_InventoryId(inventoryId);
        vehicles.forEach(vehicle -> vehicleRepository.delete(vehicle));
//delete the inventory itself
        inventoryRepository.delete(existingInventory);
    }

    @Override
    public InventoryResponseModel getInventoryById(String inventoryId) {
        InventoryIdentifier id = new InventoryIdentifier(inventoryId);
        Inventory inventory = inventoryRepository.findInventoryByInventoryIdentifier(id);
        System.out.println(inventory.getType());
        if (inventory == null) {
            throw new NotFoundException("Unknown inventoryId: " + inventoryId);
        }


//        List<Vehicle> vehicles = vehicleRepository.findAllByInventoryIdentifier_InventoryId(inventoryId);
//        List<Vehicle> vehicles = vehicleRepository
//                .findAllByInventoryIdentifier_InventoryIdAndStatusEquals(inventoryId,
//                        Status.AVAILABLE);
//        List<VehicleResponseModel> vehicleResponseModels =
//                vehicleResponseMapper.entityListToResponseModelList(vehicles);
        List<VehicleResponseModel> vehicleResponseModels = new ArrayList<>();
//        return vehicleInventoryResponseMapper.entitiesToResponseModel(inventory, vehicleResponseModels);
        return this.inventoryResponseMapper.entityToResponseModel(inventory);
    }
}
