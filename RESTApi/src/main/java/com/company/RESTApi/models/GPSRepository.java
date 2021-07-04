package com.company.RESTApi.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Repository of GPSInfo objects
 */
@Repository
public interface GPSRepository extends JpaRepository<GPSInfo, Long> {
    List<GPSInfo> findByDeviceId(long deviceId);
    List<GPSInfo> findByDate(LocalDate date);
    List<GPSInfo> deleteByDeviceId(long deviceId);

}
