package com.qubedlab.crair.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import com.qubedlab.crair.auditableService.impl.CustomerPersonalDetailsAuditableImpl;
import com.qubedlab.crair.auditableService.impl.PVehiclesAuditableImpl;
import com.qubedlab.crair.models.PVehicles;
import com.qubedlab.crair.models.TradeVehicles;
import com.qubedlab.crair.service.PVehiclesService;
import com.qubedlab.crair.service.TradeVehiclesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qubedlab.crair.models.CustomerPersonalDetails;
import com.qubedlab.crair.repository.CustomerPersonalDetailsRepository;
import com.qubedlab.crair.service.CustomerPersonalDetailsService;
import org.springframework.transaction.annotation.Propagation;
import com.qubedlab.crair.service.Response;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@Service
public class CustomerPersonalDetailsServiceImpl implements CustomerPersonalDetailsService {

    @Autowired
    private CustomerPersonalDetailsRepository temporaryCustomerRepository;

    @Autowired
    private CustomerPersonalDetailsService customerPersonalDetailsService;
    @Autowired
    PVehiclesService pVehiclesService;

    @Autowired
    TradeVehiclesService tradeVehiclesService;

    @Autowired
    PVehiclesAuditableImpl pVehiclesAuditable;

    @Autowired
    CustomerPersonalDetailsAuditableImpl customerPersonalDetailsAuditable;

    @Override
    public List<CustomerPersonalDetails> listCustomersByDLNumber(String dlNumber) {

	return temporaryCustomerRepository.listCustomersByDLNumber(dlNumber);
    }

    @Override
    public CustomerPersonalDetails saveCustomer(CustomerPersonalDetails customer) {

	return temporaryCustomerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(String dlNumber) {
	temporaryCustomerRepository.deleteById(dlNumber);

    }

    @Override
    public Response<CustomerPersonalDetails> findByGlobal(String customerGlobalID) {

        try {
            CustomerPersonalDetails provider = customerPersonalDetailsAuditable.findById(customerGlobalID);
            if (provider == null) {
                return new Response<>(404, "customer provider not found");
            }
            return new Response<CustomerPersonalDetails>().buildSuccessResponse(provider);
        } catch (Exception e) {
            log.error("{}", e);
            return new Response<>(404, "Error searching for CustomerPersonalDetails");
        }
    }


    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Response<CustomerPersonalDetails> addCustomerPVehicle(String customerGlobalID, String VIN) {

        final Response<CustomerPersonalDetails> customerResponse = findByGlobal(customerGlobalID);
        if (!customerResponse.isSuccess()) {
            return customerResponse;
        }
        final Response<PVehicles> pVehiclesResponse = pVehiclesService.findById(VIN);
        if (!pVehiclesResponse.isSuccess()) {
            return new Response<>(404, pVehiclesResponse.getMessage());
        }
        try {
            CustomerPersonalDetails customer = customerResponse.getResponseBody();
            List<PVehicles> customerPvehicless = customer.getPurchases();
            PVehicles pVToAdd = pVehiclesResponse.getResponseBody();
            if (customerPvehicless == null || customerPvehicless.isEmpty()) {
                customerPvehicless = new ArrayList<>(1);
            } else {
                 for (PVehicles pVehicle : customerPvehicless) {
                    if (pVehicle.equals(pVToAdd)) {
                        return new Response<CustomerPersonalDetails>().buildErrorResponse("Pvehicles already added to this CustomerPersonalDetails");
                    }
                }
            }
            customerPvehicless.add(pVToAdd);
            customer.setPurchases(customerPvehicless);

            CustomerPersonalDetails customer1 = temporaryCustomerRepository.save(customer);
            return new Response<CustomerPersonalDetails>().buildSuccessResponse("New pVehicle added to the pVehicle provider", customer1);
        } catch (Exception e) {
            log.error("{}", e);
            return new Response<CustomerPersonalDetails>().buildErrorResponse("Failed to add new pVehicle to the pVehicle-provider", customerResponse.getResponseBody());
        }
    }

    @Override
    public Response<CustomerPersonalDetails> addCustomerTradeVehicle(String customerGlobalID, String VIN) {

        final Response<CustomerPersonalDetails> customerResponse = findByGlobal(customerGlobalID);
        if (!customerResponse.isSuccess()) {
            return customerResponse;
        }
        final Response<TradeVehicles> tradeVehicleResponse = tradeVehiclesService.findById(VIN);
        if (!tradeVehicleResponse.isSuccess()) {
            return new Response<>(404, tradeVehicleResponse.getMessage());
        }
        try {
            CustomerPersonalDetails customer = customerResponse.getResponseBody();
            List<TradeVehicles> customerTvehicles = customer.getTrades();
            TradeVehicles tradeToAdd = tradeVehicleResponse.getResponseBody();
            if (customerTvehicles == null || customerTvehicles.isEmpty()) {
                customerTvehicles = new ArrayList<>(1);
            } else {
                //Check if the pVehicle already exists
                for (TradeVehicles pVehicle : customerTvehicles) {
                    if (pVehicle.equals(tradeToAdd)) {
                        return new Response<CustomerPersonalDetails>().buildErrorResponse("Trade already added to this CustomerPersonalDetails");
                    }
                }
            }
            customerTvehicles.add(tradeToAdd);
            customer.setTrades(customerTvehicles);

            CustomerPersonalDetails customer1 = temporaryCustomerRepository.save(customer);
            return new Response<CustomerPersonalDetails>().buildSuccessResponse("New Trade added to the pVehicle provider", customer1);
        } catch (Exception e) {
            log.error("{}", e);
            return new Response<CustomerPersonalDetails>().buildErrorResponse("Failed to add new trade vehicle to the customer", customerResponse.getResponseBody());
        }
    }


    @Override
    public void deleteCustomer(CustomerPersonalDetails customer) {
	temporaryCustomerRepository.delete(customer);

    }

    @Override
    public List<CustomerPersonalDetails> listCustomers() {

	return temporaryCustomerRepository.findAll();
    }

    @Override
    public List<CustomerPersonalDetails> listCustomerByGlobalID(String customerGlobalID) {

	return temporaryCustomerRepository.listCustomerByGlobalID(customerGlobalID);
    }

    @Override
    public CustomerPersonalDetails CustomerByGlobalID(String customerGlobalID) {

	return temporaryCustomerRepository.CustomerByGlobalID(customerGlobalID);
    }

    @Override
    public List<CustomerPersonalDetails> serchCustomer(String dlNumber) {
	// TODO Auto-generated method stub
	return temporaryCustomerRepository.serchCustomer(dlNumber);
    }

}
