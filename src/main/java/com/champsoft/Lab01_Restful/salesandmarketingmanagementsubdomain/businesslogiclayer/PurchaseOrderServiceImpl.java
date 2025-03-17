package com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.businesslogiclayer;

import com.champsoft.Lab01_Restful.clientmanagementsubdomain.dataaccesslayer.CustomerIdentifier;
import com.champsoft.Lab01_Restful.clientmanagementsubdomain.presentationlayer.CustomerResponseModel;
import com.champsoft.Lab01_Restful.employeemanagementsubdomain.dataaccesslayer.EmployeeIdentifier;
import com.champsoft.Lab01_Restful.employeemanagementsubdomain.presentationlayer.EmployeeResponseDTO;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.vehicle.Status;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.inventory.InventoryIdentifier;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.dataaccesslayer.vehicle.VehicleIdentifier;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.presentationlayer.VehicleRequestModel;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.presentationlayer.VehicleResponseModel;
import com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.dataaccesslayer.*;
import com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.datamapperlayer.PurchaseOrderRequestModelMapper;
import com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.datamapperlayer.PurchaseOrderResponseModelMapper;
import com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.presentationlayer.PurchaseRequestModel;
import com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.presentationlayer.PurchaseResponseModel;
import com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.presentationlayer.customerdtos.CustomersServiceClient;
import com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.presentationlayer.employeedto.EmployeesServiceClient;
import com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.presentationlayer.inventorydto.InventoryServiceClient;
import com.champsoft.Lab01_Restful.utils.InvalidInputException;
import com.champsoft.Lab01_Restful.utils.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final PurchaseOrderResponseModelMapper purchaseOrderResponseModelMapper;
    private final PurchaseOrderRequestModelMapper purchaseOrderRequestModelMapper;
    private final CustomersServiceClient customersServiceClient;
    private final InventoryServiceClient inventoryServiceClient;
    private final EmployeesServiceClient employeesServiceClient;


    @Autowired
    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository, PurchaseOrderResponseModelMapper purchaseOrderResponseModelMapper, PurchaseOrderRequestModelMapper purchaseOrderRequestModelMapper, CustomersServiceClient customersServiceClient, InventoryServiceClient inventoryServiceClient, EmployeesServiceClient employeesServiceClient) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.purchaseOrderResponseModelMapper = purchaseOrderResponseModelMapper;
        this.purchaseOrderRequestModelMapper = purchaseOrderRequestModelMapper;
        this.customersServiceClient = customersServiceClient;
        this.inventoryServiceClient = inventoryServiceClient;
        this.employeesServiceClient = employeesServiceClient;
    }

    public List<PurchaseResponseModel> getAllPurchaseOrders() {
        List<PurchaseOrder> purchaseOrders = this.purchaseOrderRepository.findAll();
        return this.purchaseOrderResponseModelMapper.entityToResponseModelList(purchaseOrders);
    }

    @Override
    public PurchaseResponseModel getPurchaseOrderById(String pOrderId) {
        PurchaseOrder purchaseOrder =
                this.purchaseOrderRepository.findPurchaseOrderByPurchaseOrderIdentifierPurchaseId(pOrderId);
        return this.purchaseOrderResponseModelMapper.entityToResponseModel(purchaseOrder);
    }

    @Override
    public PurchaseResponseModel addPurchaseOrder(PurchaseRequestModel prm) {

        CustomerResponseModel customerResponseModel =
                this.customersServiceClient.getCustomerByCustomerId(prm.getCustomerId());
        if (customerResponseModel == null) {
            throw new NotFoundException("Unknown customerId provided: " + prm.getCustomerId());
        }
        EmployeeResponseDTO employeeResponseModel = this.employeesServiceClient.getEmployeeByEmployeeId(prm.getEmployeeId());
        if (employeeResponseModel == null) {
            throw new NotFoundException("Unknown employeeId provided: " + prm.getEmployeeId());
        }
        VehicleResponseModel vehicleResponseModel = this.inventoryServiceClient.getVehicleByVehicleId(
                prm.getInventoryId(),
                prm.getVehicleIdentificationNumber()
        );
        if (vehicleResponseModel == null) {
            throw new NotFoundException("Vehicle with id: " + prm.getVehicleIdentificationNumber()
                    + "is not in inventory with id: " +
                    prm.getInventoryId());
        }
        //use the request mapper to build the purchase order object
        PurchaseOrder purchaseOrder = this.purchaseOrderRequestModelMapper
                .requestModelToEntity(prm);


        // use the setters to save all these objects in the entity object
        purchaseOrder.setPurchaseOrderIdentifier(new PurchaseOrderIdentifier());
        purchaseOrder.setCustomerIdentifier(new CustomerIdentifier(prm.getCustomerId()));
        purchaseOrder.setEmployeeIdentifier(new EmployeeIdentifier(prm.getEmployeeId()));
        purchaseOrder.setVehicleIdentifier(new VehicleIdentifier(prm.getVehicleIdentificationNumber()));
        purchaseOrder.setInventoryIdentifier(new InventoryIdentifier(prm.getInventoryId()));

        // create the price object and save it in the entity object
        Price price = new Price(new BigDecimal(prm.getSalePrice()),
                stringToCurrency(prm.getCurrency()),
                stringToCurrency(prm.getPayment_currency()));
        purchaseOrder.setPrice(price);
        // the financing agreement value object is copied automatically by the request mapper
        // because the names match exactly
        // now save the object in the repository
        PurchaseOrder saved = purchaseOrderRepository.save(purchaseOrder);
        if (saved == null) {
            throw new InvalidInputException("An error has occurred while saving");
        }
        Status vehicleStatus = Status.SALE_PENDING;
        switch (saved.getSaleStatus()) {
            case PURCHASE_COMPLETED -> vehicleStatus = Status.SOLD;
            case PURCHASE_CANCELED -> vehicleStatus = Status.AVAILABLE;
        }
        //convert vehicleResponseModel into a VehicleRequestModel
        VehicleRequestModel vehicleRequestModel1 = VehicleRequestModel.builder()
                .vehicleId(vehicleResponseModel.getVehicleId())
                .inventoryId(vehicleResponseModel.getInventoryId())
                .status(vehicleStatus)
                .usageType(vehicleResponseModel.getUsageType())
                .year(vehicleResponseModel.getYear())
                .make(vehicleResponseModel.getMake())
                .manufacturer(vehicleResponseModel.getManufacturer())
                .model(vehicleResponseModel.getModel())
                .bodyClass(vehicleResponseModel.getBodyClass())
                .options(vehicleResponseModel.getOptions())
                .cost(vehicleResponseModel.getPrice().getCost())
                .msrp(vehicleResponseModel.getPrice().getMsrp())
                .build();
        inventoryServiceClient.updateVehicleStatus(
                vehicleRequestModel1,
                vehicleRequestModel1.getInventoryId(),
                vehicleRequestModel1.getVehicleId());
        return purchaseOrderResponseModelMapper.entityToResponseModel(saved);
    }

    @Override
    public PurchaseResponseModel updatePurchaseOrder(String purchaseId, PurchaseRequestModel purchaseRequestModel) {
        System.out.println("Received request to update purchase order with ID: " + purchaseId);

        // Fetch purchase order
        PurchaseOrder existingPurchaseOrder = purchaseOrderRepository.findPurchaseOrderByPurchaseOrderIdentifierPurchaseId(purchaseId);
        if (existingPurchaseOrder == null) {
            throw new NotFoundException("Purchase order not found for ID: " + purchaseId);
        }
        System.out.println("Existing Purchase Order Found: " + existingPurchaseOrder);

        // Validate Customer
        CustomerResponseModel customerResponseModel = customersServiceClient.getCustomerByCustomerId(purchaseRequestModel.getCustomerId());
        if (customerResponseModel == null) {
            throw new NotFoundException("Unknown customerId provided: " + purchaseRequestModel.getCustomerId());
        }

        // Validate Employee
        EmployeeResponseDTO employeeResponseModel = employeesServiceClient.getEmployeeByEmployeeId(purchaseRequestModel.getEmployeeId());
        if (employeeResponseModel == null) {
            throw new NotFoundException("Unknown employeeId provided: " + purchaseRequestModel.getEmployeeId());
        }

        // Validate Vehicle
        VehicleResponseModel vehicleResponseModel = inventoryServiceClient.getVehicleByVehicleId(
                purchaseRequestModel.getInventoryId(),
                purchaseRequestModel.getVehicleIdentificationNumber() // FIXED method name
        );
        if (vehicleResponseModel == null) {
            throw new NotFoundException("Vehicle with id: " + purchaseRequestModel.getVehicleIdentificationNumber() +
                    " is not in inventory with id: " + purchaseRequestModel.getInventoryId());
        }

        // Apply Updates
        if (purchaseRequestModel.getSaleStatus() != null) {
            System.out.println("Updating Sale Status: " + purchaseRequestModel.getSaleStatus());
            existingPurchaseOrder.setSaleStatus(purchaseRequestModel.getSaleStatus());
        }

        if (purchaseRequestModel.getSalePrice() != null || purchaseRequestModel.getCurrency() != null || purchaseRequestModel.getPayment_currency() != null) {
            System.out.println("Updating Price Details");

            Price price = new Price(
                    purchaseRequestModel.getSalePrice() != null ? new BigDecimal(purchaseRequestModel.getSalePrice()) : existingPurchaseOrder.getPrice().getAmount(),
                    purchaseRequestModel.getCurrency() != null ? stringToCurrency(purchaseRequestModel.getCurrency()) : existingPurchaseOrder.getPrice().getCurrency(),
                    purchaseRequestModel.getPayment_currency() != null ? stringToCurrency(purchaseRequestModel.getPayment_currency()) : existingPurchaseOrder.getPrice().getPayment_currency()
            );
            existingPurchaseOrder.setPrice(price);
        }

        // Save updated purchase order
        PurchaseOrder updatedPurchaseOrder = purchaseOrderRepository.save(existingPurchaseOrder);
        if (updatedPurchaseOrder == null) {
            throw new InvalidInputException("Error saving updated Purchase Order.");
        }
        System.out.println("Updated Purchase Order successfully");

        return purchaseOrderResponseModelMapper.entityToResponseModel(updatedPurchaseOrder);
    }
        @Override
        public void deletePurchaseOrder (String purchaseId){
            PurchaseOrder existingPurchaseOrder = purchaseOrderRepository.findPurchaseOrderByPurchaseOrderIdentifierPurchaseId(purchaseId);
            if (existingPurchaseOrder == null) {
                throw new NotFoundException("Purchase order not found");
            }
            purchaseOrderRepository.delete(existingPurchaseOrder);
        }


        private Currency stringToCurrency (String stringVar){
            // How confident are you that stringVar will have an acceptable value?
            Currency enumValue;
            try {
                // Consider using trim to eliminate extraneous whitespace
                enumValue = Currency.valueOf(stringVar.trim());
            } catch (Exception e) {
                // handle the situation here. Here are a couple of ideas.
                // Apply null and expect the using code to detect.
                // enumValue = null;
                // Have a defined private constant for a default value
                // assuming a default value would make more sense than null
                enumValue = Currency.CAD;
            }
            return enumValue;
        }

    }

