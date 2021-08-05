package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.payload.ExpiredProductsForDashboard;
import uz.pdp.appwarehouse.payload.InputsForDashboard;
import uz.pdp.appwarehouse.payload.OutputsForDashboard;
import uz.pdp.appwarehouse.respository.InputProductRepository;
import uz.pdp.appwarehouse.respository.InputRepository;
import uz.pdp.appwarehouse.respository.OutputProductRepository;

import java.util.List;

@Service
public class DashboardService {
    @Autowired
    InputProductRepository inputProductRepository;
    @Autowired
    OutputProductRepository outputProductRepository;
    public List<InputsForDashboard> getPerDayInputs(){
        List<InputsForDashboard> inputDistinctByDate = inputProductRepository.getDistinctByDate();
        return inputDistinctByDate;
    }
    public List<OutputsForDashboard> getPerDayOutputs(){
        List<OutputsForDashboard> outputDistinctByDate=outputProductRepository.outputsForDashboard();
        return outputDistinctByDate;
    }
    public List<ExpiredProductsForDashboard> getExpired(){
        List<ExpiredProductsForDashboard> expired = inputProductRepository.getExpired();
        return expired;
    }

}
