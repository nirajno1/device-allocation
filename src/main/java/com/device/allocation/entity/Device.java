package com.device.allocation.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.util.Calendar;

@Entity
public class Device {
    @Id
    @GeneratedValue()
    private Integer id;
    private String deviceName;
    private Boolean available;
    @OneToOne
    private DeviceUser bookedUser;
    private Calendar bookedDateTime;
    private Calendar createdOn;
    private String createdBy;
    private Calendar updatedOn;
    private String updatedBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Calendar getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Calendar createdOn) {
        this.createdOn = createdOn;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Calendar getBookedDateTime() {
        return bookedDateTime;
    }

    public void setBookedDateTime(Calendar bookedDateTime) {
        this.bookedDateTime = bookedDateTime;
    }

    public DeviceUser getBookedUser() {
        return bookedUser;
    }

    public void setBookedUser(DeviceUser bookedUser) {
        this.bookedUser = bookedUser;
    }

    public Calendar getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Calendar updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
