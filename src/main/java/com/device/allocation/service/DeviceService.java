package com.device.allocation.service;

import com.device.allocation.dto.DeviceBookingRequest;

public interface DeviceService {
    Boolean bookDevice(DeviceBookingRequest deviceBookingRequest);
    Boolean returnDevice(DeviceBookingRequest deviceBookingRequest);
}
