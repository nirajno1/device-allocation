package com.device.allocation.controller;

import com.device.allocation.dto.DeviceBookingRequest;
import com.device.allocation.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AllocationController {

    private final DeviceService deviceService;

    public AllocationController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("/health")
    public String health() {
        return "ok";
    }

    @PutMapping("/book_device")
    public ResponseEntity<String> allocateDevice(@RequestBody DeviceBookingRequest deviceBookingRequest) {
        ResponseEntity<String> entityResponse;
        Boolean success = deviceService.bookDevice(deviceBookingRequest);
        if (success) {
            entityResponse = new ResponseEntity<>("Success", HttpStatus.OK);
        } else {
            entityResponse = new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return entityResponse;
    }

    @PutMapping("/return_device")
    public ResponseEntity<String> returnDevice(@RequestBody DeviceBookingRequest deviceBookingRequest) {
        ResponseEntity<String> entityResponse;
        Boolean success = deviceService.returnDevice(deviceBookingRequest);
        if (success) {
            entityResponse = new ResponseEntity<>("Success", HttpStatus.OK);
        } else {
            entityResponse = new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return entityResponse;
    }
}
