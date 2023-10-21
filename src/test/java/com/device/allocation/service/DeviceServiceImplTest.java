package com.device.allocation.service;

import com.device.allocation.dto.DeviceBookingRequest;
import com.device.allocation.entity.Device;
import com.device.allocation.entity.DeviceUser;
import com.device.allocation.repository.DeviceRepository;
import com.device.allocation.repository.DeviceUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

class DeviceServiceImplTest {
    @Mock
    DeviceRepository deviceRepository;
    @Mock
    NotificationService notificationService;
    @Mock
    DeviceUserRepository deviceUserRepository;
    @InjectMocks
    DeviceServiceImpl deviceServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testBookDevice_success() {
        Device device = new Device();
        device.setAvailable(Boolean.TRUE);
        when(deviceRepository.findById(anyInt())).thenReturn(Optional.of(device));
        DeviceUser deviceUser = new DeviceUser();
        deviceUser.setUserName("test name");
        when(deviceUserRepository.findById(anyInt())).thenReturn(Optional.of(deviceUser));
        Boolean result = deviceServiceImpl.bookDevice(new DeviceBookingRequest(0, 0));
        Assertions.assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testBookDevice_fail_device_not_found() {
        when(deviceRepository.findById(anyInt())).thenReturn(Optional.empty());
        DeviceUser deviceUser = new DeviceUser();
        deviceUser.setUserName("test name");
        when(deviceUserRepository.findById(anyInt())).thenReturn(Optional.of(deviceUser));
        Boolean result = deviceServiceImpl.bookDevice(new DeviceBookingRequest(0, 0));
        Assertions.assertEquals(Boolean.FALSE, result);
    }

    @Test
    void testBookDevice_fail_user_not_found() {
        Device device = new Device();
        device.setAvailable(Boolean.TRUE);
        when(deviceRepository.findById(anyInt())).thenReturn(Optional.of(device));
        when(deviceUserRepository.findById(anyInt())).thenReturn(Optional.empty());
        Boolean result = deviceServiceImpl.bookDevice(new DeviceBookingRequest(0, 0));
        Assertions.assertEquals(Boolean.FALSE, result);
    }

    @Test
    void testBookDevice_fail_device_not_available() {
        Device device = new Device();
        device.setAvailable(Boolean.FALSE);
        when(deviceRepository.findById(anyInt())).thenReturn(Optional.of(device));
        DeviceUser deviceUser = new DeviceUser();
        deviceUser.setUserName("test name");
        when(deviceUserRepository.findById(anyInt())).thenReturn(Optional.of(deviceUser));
        Boolean result = deviceServiceImpl.bookDevice(new DeviceBookingRequest(0, 0));
        Assertions.assertEquals(Boolean.FALSE, result);
    }

    @Test
    void testReturnDevice_success() {
        Device device = new Device();
        device.setAvailable(Boolean.FALSE);
        DeviceUser deviceUser = new DeviceUser();
        deviceUser.setId(0);
        deviceUser.setUserName("test name");
        device.setBookedUser(deviceUser);
        when(deviceRepository.findById(anyInt())).thenReturn(Optional.of(device));
        when(deviceUserRepository.findById(anyInt())).thenReturn(Optional.of(deviceUser));
        Boolean result = deviceServiceImpl.returnDevice(new DeviceBookingRequest(0, 0));
        Assertions.assertEquals(Boolean.TRUE, result);
    }

    @Test
    void testReturnDevice_fail_device_not_found() {
        DeviceUser deviceUser = new DeviceUser();
        deviceUser.setId(0);
        deviceUser.setUserName("test name");
        when(deviceRepository.findById(anyInt())).thenReturn(Optional.empty());
        when(deviceUserRepository.findById(anyInt())).thenReturn(Optional.of(deviceUser));
        Boolean result = deviceServiceImpl.returnDevice(new DeviceBookingRequest(0, 0));
        Assertions.assertEquals(Boolean.FALSE, result);
    }

    @Test
    void testReturnDevice_fail_user_not_found() {
        Device device = new Device();
        device.setAvailable(Boolean.FALSE);
        DeviceUser deviceUser = new DeviceUser();
        deviceUser.setId(0);
        deviceUser.setUserName("test name");
        device.setBookedUser(deviceUser);
        when(deviceRepository.findById(anyInt())).thenReturn(Optional.of(device));
        when(deviceUserRepository.findById(anyInt())).thenReturn(Optional.empty());
        Boolean result = deviceServiceImpl.returnDevice(new DeviceBookingRequest(0, 0));
        Assertions.assertEquals(Boolean.FALSE, result);
    }

    @Test
    void testReturnDevice_fail_user_mismatch_found() {
        Device device = new Device();
        device.setAvailable(Boolean.FALSE);
        DeviceUser deviceUser = new DeviceUser();
        deviceUser.setId(0);
        deviceUser.setUserName("test name");
        device.setBookedUser(deviceUser);
        when(deviceRepository.findById(anyInt())).thenReturn(Optional.of(device));
        DeviceUser deviceUser1 = new DeviceUser();
        deviceUser.setId(1);
        when(deviceUserRepository.findById(anyInt())).thenReturn(Optional.of(deviceUser1));
        Boolean result = deviceServiceImpl.returnDevice(new DeviceBookingRequest(0, 1));
        Assertions.assertEquals(Boolean.FALSE, result);
    }

    @Test
    void testReturnDevice_fail_device_not_allocated() {
        Device device = new Device();
        device.setAvailable(Boolean.TRUE);
        when(deviceRepository.findById(anyInt())).thenReturn(Optional.of(device));
        Boolean result = deviceServiceImpl.returnDevice(new DeviceBookingRequest(0, 1));
        Assertions.assertEquals(Boolean.FALSE, result);
    }
}
