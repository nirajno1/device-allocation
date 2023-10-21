package com.device.allocation.service;

import com.device.allocation.dto.DeviceBookingRequest;
import com.device.allocation.entity.Device;
import com.device.allocation.entity.DeviceUser;
import com.device.allocation.repository.DeviceRepository;
import com.device.allocation.repository.DeviceUserRepository;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;
    private final NotificationService notificationService;
    private final DeviceUserRepository deviceUserRepository;

    public DeviceServiceImpl(NotificationService notificationService, DeviceRepository deviceRepository, DeviceUserRepository deviceUserRepository) {
        this.notificationService = notificationService;
        this.deviceRepository = deviceRepository;
        this.deviceUserRepository = deviceUserRepository;
    }

    @Override
    public Boolean bookDevice(DeviceBookingRequest deviceBookingRequest) {
        Optional<Device> requestedDevice = deviceRepository.findById(deviceBookingRequest.deviceId());
        if (requestedDevice.isPresent()) {
            Device device = requestedDevice.get();
            boolean isAvailable = device.getAvailable();
            if (isAvailable) {
                Optional<DeviceUser> user = deviceUserRepository.findById(deviceBookingRequest.userId());
                if (user.isPresent()) {
                    DeviceUser deviceUser = user.get();
                    device.setAvailable(Boolean.FALSE);
                    device.setBookedUser(deviceUser);
                    device.setBookedDateTime(Calendar.getInstance());
                    device.setUpdatedBy("system");
                    device.setUpdatedOn(Calendar.getInstance());
                    deviceRepository.save(device);
                    notificationService.sendNotification(
                            MessageFormat.format("Mobile {0} allocated to {1} successfully.",
                                    device.getDeviceName(), device.getBookedUser().getUserName()));
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

    @Override
    public Boolean returnDevice(DeviceBookingRequest deviceBookingRequest) {
        Optional<Device> optionalDevice = deviceRepository.findById(deviceBookingRequest.deviceId());
        if (optionalDevice.isPresent()) {
            Device device = optionalDevice.get();
            if (!device.getAvailable()) {
                DeviceUser deviceUser = device.getBookedUser();
                Optional<DeviceUser> optionalDeviceUser = deviceUserRepository.findById(deviceBookingRequest.userId());
                if (optionalDeviceUser.isPresent()) {
                    DeviceUser deviceUserEntity = optionalDeviceUser.get();
                    if (deviceUser.getId().equals(deviceUserEntity.getId())) {
                        device.setAvailable(Boolean.TRUE);
                        device.setBookedDateTime(null);
                        device.setBookedUser(null);
                        device.setUpdatedBy("system");
                        device.setUpdatedOn(Calendar.getInstance());
                        deviceRepository.save(device);
                        notificationService.sendNotification(
                                MessageFormat.format("Mobile {0} returned by {1} successfully.",
                                        device.getDeviceName(), deviceUser.getUserName()));
                        return Boolean.TRUE;
                    }
                }
            }
        }
        return Boolean.FALSE;
    }
}
