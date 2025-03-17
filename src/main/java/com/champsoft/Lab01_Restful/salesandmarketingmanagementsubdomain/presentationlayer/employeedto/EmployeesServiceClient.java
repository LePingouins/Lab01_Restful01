package com.champsoft.Lab01_Restful.salesandmarketingmanagementsubdomain.presentationlayer.employeedto;

import com.champsoft.Lab01_Restful.employeemanagementsubdomain.presentationlayer.EmployeeResponseDTO;
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
public class EmployeesServiceClient {
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper;
    private final String EMPLOYEE_SERVICE_BASE_URL;

    //constructor - get environment variable values
    public EmployeesServiceClient(RestTemplate restTemplate, ObjectMapper mapper) {
        this.restTemplate = restTemplate;
        this.mapper = mapper;
        String employeeServiceHost = "localhost";
        String employeeServicePort = "8080";
        EMPLOYEE_SERVICE_BASE_URL = "http://" + employeeServiceHost + ":" +
                employeeServicePort + "/api/v1/employees";
    }

    public EmployeeResponseDTO getEmployeeByEmployeeId(String employeeId) {
        try {
            String url = EMPLOYEE_SERVICE_BASE_URL + "/" + employeeId;
            EmployeeResponseDTO employeeResponseDTO = restTemplate.getForObject(url,
                    EmployeeResponseDTO.class);
            return employeeResponseDTO;
        } catch (HttpClientErrorException ex) {
            //log.debug("5. Received in API-Gateway Client Service Client getAllClients exception: " + ex.getMessage());
            throw handleHttpClientException(ex);
        }
    }

    private String getErrorMessage(HttpClientErrorException ex) {
        try {
            return mapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        } catch (IOException ioex) {
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
