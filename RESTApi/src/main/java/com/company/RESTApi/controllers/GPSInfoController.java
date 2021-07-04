package com.company.RESTApi.controllers;

import java.time.LocalDate;
import java.util.List;

import com.company.RESTApi.Exceptions.GPSInfoNotFoundException;
import com.company.RESTApi.models.GPSInfo;
import com.company.RESTApi.models.GPSRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * Rest Controller connected to GPS Repository
 */
@RestController
public class GPSInfoController {

    /**
     * GPS repository with GPSInfo records
     */
    private final GPSRepository repository;


    GPSInfoController(GPSRepository repository) {
        this.repository = repository;
    }

    /**
     * GET all GPSInfo objects
     * @return List of GPSInfo records
     */
    @GetMapping(value="/gps")
    public List<GPSInfo> all() {
        return repository.findAll();
    }

    /**
     * GET specified GPSInfo object (selected by id)
     * @param id Id of GPSInfo Object
     * @return GPSInfo record
     */
    @GetMapping(value="/gps/{id}")
    public GPSInfo GPSInfoByPk(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new GPSInfoNotFoundException(id));
    }

    /**
     * GET all GPSInfo objects associated with requested deviceId
     * @param deviceId Id of requested device
     * @return List of GPSInfo records
     */
    @GetMapping(value="/gps", params="deviceId")
    public List<GPSInfo> allOfDevice(@RequestParam Long deviceId) {
        return repository.findByDeviceId(deviceId);
    }

    /**
     * GET all GPSInfo from requested day
     * @param date Day of which GPSInfo is requested
     * @return List of GPSInfo records
     */
    @GetMapping(value="/gps", params="date")
    @ResponseBody
    public List<GPSInfo> allFromDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
        return repository.findByDate(date);
    }

    /**
     * POST - create and save new GPSInfo object
     * @param newGPSInfo json with devideId, latitude and longtitude
     * @return newly created and saved GPSInfo record
     */
    @PostMapping(value="/gps")
    public GPSInfo newGPSInfo(@Valid @RequestBody  GPSInfo newGPSInfo) {
        return repository.save(newGPSInfo);
    }

    /**
     * DELETE all GPSInfo records of passed deviceId
     * @param deviceId id of device which GPSInfo records requested to delete
     * @return deleted GPSInfo records
     */
    @DeleteMapping(value="/gps", params="deviceId")
    @Transactional
    public List<GPSInfo> deleteGPSInfoByDeviceId(@RequestParam long deviceId) {
        return repository.deleteByDeviceId(deviceId);
    }
}
