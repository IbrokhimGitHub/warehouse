package uz.pdp.appwarehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appwarehouse.entity.Measurement;
import uz.pdp.appwarehouse.payload.MeasurementResult;
import uz.pdp.appwarehouse.payload.Result;
import uz.pdp.appwarehouse.service.MeasurementService;

import java.util.List;

@RestController
@RequestMapping("/measurement")
public class MeasurementController {
    @Autowired
    MeasurementService measurementService;
    @PostMapping
    public Result addMeasurement(@RequestBody Measurement measurement){
        Result result = measurementService.addMeasurementService(measurement);
        return  result;
    }
    @GetMapping("getById/{id}")
    public MeasurementResult getById(@PathVariable Integer id){
        MeasurementResult measurementResult = measurementService.getMeasurement(id);
        return measurementResult;
    }
    @GetMapping
    public List<Measurement> getAll(){
        List<Measurement> measurements = measurementService.getMeasurements();
        return measurements;
    }
    @DeleteMapping("deleted/{id}")
    public Result delete(@PathVariable Integer id){
        Result result = measurementService.deleteMeasurement(id);
        return result;
    }
    @PutMapping("editMeasuremnent/{id}")
    public Result edit(@PathVariable Integer id,@RequestBody Measurement measurement){
        Result result = measurementService.editMeasurement(id, measurement);
        return result;
    }
    //crud
}
