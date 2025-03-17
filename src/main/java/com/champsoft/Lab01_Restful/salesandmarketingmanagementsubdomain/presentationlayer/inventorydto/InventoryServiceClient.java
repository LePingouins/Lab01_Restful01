package com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.presentationlayer.inventorydto;

import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.presentationlayer.VehicleRequestModel;
import com.champsoft.Lab01_Restful.inventorymanagementsubdomain.presentationlayer.VehicleResponseModel;
import com.champsoft.Lab01_Restful.utils.HttpErrorInfo;
import com.champsoft.Lab01_Restful.utils.InvalidInputException;
import com.champsoft.Lab01_Restful.utils.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Slf4j
@Component
public class InventoryServiceClient {
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private final String INVENTORY_SERVICE_BASE_URL;
    //constructor - get environment variable values
    public InventoryServiceClient( RestTemplate restTemplate,
                                   ObjectMapper mapper) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        String inventoryServiceHost = "localhost";
        String inventoryServicePort = "8080";
        INVENTORY_SERVICE_BASE_URL = "http://" + inventoryServiceHost + ":" +
                inventoryServicePort + "/api/v1/inventories";
    }
    public VehicleResponseModel getVehicleByVehicleId(String inventoryId,
                                                      String vin){
        try {
            //"/{inventoryId}/vehicles/{vin}"
            String url = INVENTORY_SERVICE_BASE_URL + "/" + inventoryId + "/"
                    + "vehicles" + "/" + vin;
            VehicleResponseModel vehicleResponseModel =
                    restTemplate.getForObject(url, VehicleResponseModel.class);
            return vehicleResponseModel;
        }catch (HttpClientErrorException ex) {
            //log.debug("5. Received in API-Gateway Client Service Client getAllClients exception: " + ex.getMessage());
            throw handleHttpClientException(ex);
        }
    }
    public void updateVehicleStatus(VehicleRequestModel vehicleRequestModel,
                                    String inventoryId, String vin) {
        //"/{inventoryId}/vehicles/{vin}"
        try {
            String url = INVENTORY_SERVICE_BASE_URL + "/" + inventoryId + "/" +
                    "vehicles" + "/" + vin;
            restTemplate.put(url, vehicleRequestModel);
        }
        catch (HttpClientErrorException ex) {
            //log.debug("5. Received in API-Gateway Client Service Client getAllClients exception: "
            // + ex.getMessage());
            throw handleHttpClientException(ex);
        }
    }
    private String getErrorMessage(HttpClientErrorException ex) {
        try {
            return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        }
        catch (IOException ioex) {
            return ioex.getMessage();
        }
    }
    private RuntimeException handleHttpClientException(HttpClientErrorException ex) {
        //include all possible responses from the client
        if (ex.getStatusCode() == NOT_FOUND) {
            return new NotFoundException(getErrorMessage(ex));
        }
        if (ex.getStatusCode() == UNPROCESSABLE_ENTITY) {
            return new InvalidInputException(getErrorMessage(ex));
        }
        log.warn("Got a unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
        log.warn("Error body: {}", ex.getResponseBodyAsString());
        return ex;
    }
}
