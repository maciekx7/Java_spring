package com.company.RESTApi.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * GPSInfo object represents GPS record.
 * Contains of deviceId, longtitude, latitude, date and time
 */
@Entity
public class GPSInfo {
    private @Id @GeneratedValue long id;

    @Min(1) @NotNull
    private Long deviceId;
    @NotNull
    private Long latitude= null;
    @NotNull
    private Long longtitude = null;
    private LocalDate date;
    private LocalTime time;

    public GPSInfo(long DeviceId, long Latitude, long Longitude) {
        this.deviceId = DeviceId;
        this.latitude = Latitude;
        this.longtitude = Longitude;
        date = LocalDate.now(ZoneId.of("Europe/Paris"));
        time =  LocalTime.now(ZoneId.of("Europe/Paris"));

    }

    public GPSInfo() {
        date = LocalDate.now(ZoneId.of("Europe/Paris"));
        time =  LocalTime.now(ZoneId.of("Europe/Paris"));
    }


    public Long getId() {
        return id;
    }

    public long getDeviceId() {
        return deviceId;
    }

    public long getLatitude() {
        return latitude;
    }

    public long getLongtitude() {
        return longtitude;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public void setLongtitude(Long longtitude) {
        this.longtitude = longtitude;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return "GPSInfo{" + "id=" + this.id + ", devideId=" + this.deviceId + ", latitude=" + this.latitude + ", longtitude=" + this.longtitude + ", date='"+ dateFormatter.format(date) + "', time='" +  timeFormatter.format(time) + "' }";
    }

}
