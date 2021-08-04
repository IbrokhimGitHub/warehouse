package uz.pdp.appwarehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appwarehouse.entity.Measurement;


import uz.pdp.appwarehouse.result.MeasurementResult;
import uz.pdp.appwarehouse.result.Result;
import uz.pdp.appwarehouse.respository.MeasurementRepository;

import java.util.List;
import java.util.Optional;


@Service
public class MeasurementService {
    @Autowired
    MeasurementRepository measurementRepository;

    public Result addMeasurementService(Measurement measurement){
        boolean existsByName = measurementRepository.existsByName(measurement.getName());
        if (existsByName)
            return new Result("such measurement is exist",false);
        measurementRepository.save(measurement);
        return new Result("saved successfully",true);

    }
    public List<Measurement> getMeasurements(){
        List<Measurement> measurements = measurementRepository.findAll();
        return measurements;
    }
    public MeasurementResult getMeasurement(Integer id){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isPresent()) {
            return new MeasurementResult(optionalMeasurement.get(),true);
        }else {
            return new MeasurementResult(new Measurement(),false);
        }
    }

    public Result deleteMeasurement(Integer id){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (optionalMeasurement.isPresent()) {
            Measurement Measurement = optionalMeasurement.get();
            Measurement.setActive(false);
            return new Result("Measurement deactivated",true);
        }
        else
            return new Result("cant find such Measurement",false);
    }
    public Result editMeasurement(Integer id, Measurement measurementIntro){
        Optional<Measurement> optionalMeasurement = measurementRepository.findById(id);
        if (!optionalMeasurement.isPresent()) {
            return new Result("cant find such Measurement",false);
        }
        Measurement measurement = optionalMeasurement.get();
        measurement.setName(measurementIntro.getName());
        measurementRepository.save(measurement);
        return new Result("Measurement edited successfully",true);

    }

}

