package com.device.allocation.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DeviceBookingRequestTest {
    DeviceBookingRequest deviceBookingRequest = new DeviceBookingRequest(1, 1);

    @Test
    void testDeviceId() {
        Integer result = deviceBookingRequest.deviceId();
        Assertions.assertEquals(1, result);
    }

    @Test
    void testUserId() {
        Integer result = deviceBookingRequest.userId();
        Assertions.assertEquals(1, result);
    }
}