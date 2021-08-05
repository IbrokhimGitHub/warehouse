package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.appwarehouse.payload.ExpiredProductsForDashboard;
import uz.pdp.appwarehouse.payload.InputsForDashboard;
import uz.pdp.appwarehouse.payload.OutputsForDashboard;
import uz.pdp.appwarehouse.service.DashboardService;

import java.util.List;

@RestController
@RequestMapping("dashboard")
public class DashboardController {
    @Autowired
    DashboardService dashboardService;
    @GetMapping("inputedADay")
    public List<InputsForDashboard> inputsForDashboards(){
        List<InputsForDashboard> perDay = dashboardService.getPerDayInputs();
        return perDay;
    }

    @GetMapping("outputedADay")
    public List<OutputsForDashboard> topOutputs() {
         List<OutputsForDashboard> outputsForDashboard = dashboardService.getPerDayOutputs();
         return outputsForDashboard;

    }
    @GetMapping("expired")
    public List<ExpiredProductsForDashboard> expiredProducts(){
        List<ExpiredProductsForDashboard> expired = dashboardService.getExpired();
        return expired;
    }

}
