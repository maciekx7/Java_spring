package com.company.RESTApi.TmpObjects;

public class GPSInfoPostTestObject {
    Long deviceId = null;
    Long longtitude = null;
    Long latitude = null;

    public GPSInfoPostTestObject(Long deviceId, Long longtitude, Long latitude) {
        this.deviceId = deviceId;
        this.longtitude = longtitude;
        this.latitude = latitude;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(Long longtitude) {
        this.longtitude = longtitude;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }
}
