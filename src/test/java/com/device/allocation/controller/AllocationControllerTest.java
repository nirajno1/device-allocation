package com.device.allocation.controller;

import com.device.allocation.dto.DeviceBookingRequest;
import com.device.allocation.service.DeviceService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class AllocationControllerTest {
    @Mock
    DeviceService deviceService;
    @InjectMocks
    AllocationController allocationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHealth() {
        String result = allocationController.health();
        Assertions.assertEquals("ok", result);
    }

    @Test
    void testAllocateDevice_success() {
        when(deviceService.bookDevice(any())).thenReturn(Boolean.TRUE);
        ResponseEntity<String> entityResponseSuccess = new ResponseEntity<>("Success", HttpStatus.OK);
        ResponseEntity<?> result = allocationController.allocateDevice(new DeviceBookingRequest(0, 0));
        Assertions.assertEquals(entityResponseSuccess, result);
    }

    @Test
    void testAllocateDevice_fail() {
        when(deviceService.bookDevice(any())).thenReturn(Boolean.FALSE);
        ResponseEntity<String> entityResponseFail = new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        ResponseEntity<?> result = allocationController.allocateDevice(new DeviceBookingRequest(0, 0));
        Assertions.assertEquals(entityResponseFail, result);
    }

    @Test
    void testReturnDevice_success() {
        when(deviceService.returnDevice(any())).thenReturn(Boolean.TRUE);

        ResponseEntity<?> result = allocationController.returnDevice(new DeviceBookingRequest(0, 0));
        ResponseEntity<String> entityResponseSuccess = new ResponseEntity<>("Success", HttpStatus.OK);
        Assertions.assertEquals(entityResponseSuccess, result);
    }
    @Test
    void testReturnDevice_fail() {
        when(deviceService.returnDevice(any())).thenReturn(Boolean.FALSE);
        ResponseEntity<?> result = allocationController.returnDevice(new DeviceBookingRequest(0, 0));
        ResponseEntity<String> entityResponseFail = new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        Assertions.assertEquals(entityResponseFail, result);
    }
}
