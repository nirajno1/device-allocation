package com.device.allocation.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;

class DeviceTest {
    @Mock
    DeviceUser bookedUser;
    @Mock
    Calendar bookedDateTime;
    @Mock
    Calendar createdOn;
    @Mock
    Calendar updatedOn;
    @InjectMocks
    Device device;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDevice(){
        Assertions.assertNotNull(device.getBookedUser());
        Assertions.assertNotNull(device.getUpdatedOn());
        Assertions.assertNotNull(device.getBookedDateTime());
        Assertions.assertNotNull(device.getCreatedOn());
    }
}
