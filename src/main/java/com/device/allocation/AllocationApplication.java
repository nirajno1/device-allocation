package com.device.allocation;

import com.device.allocation.entity.Device;
import com.device.allocation.entity.DeviceUser;
import com.device.allocation.repository.DeviceRepository;
import com.device.allocation.repository.DeviceUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class AllocationApplication {

    public static void main(String[] args) {
        SpringApplication.run(AllocationApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(DeviceUserRepository deviceUserRepository, DeviceRepository deviceRepository) {
        return arg -> {
            List<String> devices = List.of("Samsung Galaxy S9",
                    "2x Samsung Galaxy S8",
                    "Motorola Nexus 6",
                    "Oneplus 9",
                    "Apple iPhone 13",
                    "Apple iPhone 12",
                    "Apple iPhone 11",
                    "iPhone X",
                    "Nokia 3310");

            List<Device> deviceList = devices.stream().map(this::createDevice).collect(Collectors.toList());

            //deviceRepository.save(deviceList.get(0));
            deviceRepository.saveAll(deviceList);

            List<String> deviceUsers = List.of("Mona Lata",
                    "Rachel Vaidya",
                    "Wafa Agate",
                    "Padmini Krishnan",
                    "Yadunandan Naik",
                    "Nalini Naruka",
                    "Mustafa Biswas",
                    "Avantika Matthai",
                    "Daanish Dua",
                    "Subhash Amin");

            List<DeviceUser> deviceUserList = deviceUsers.stream().map(this::createDeviceUser).collect(Collectors.toList());
            deviceUserRepository.saveAll(deviceUserList);
        };
    }

    private Device createDevice(String deviceName) {
        Device device = new Device();
        device.setDeviceName(deviceName);
        device.setAvailable(Boolean.TRUE);
        device.setCreatedOn(Calendar.getInstance());
        device.setCreatedBy("System");
        return device;
    }

    private DeviceUser createDeviceUser(String deviceUserName) {
        DeviceUser deviceUser = new DeviceUser();
        deviceUser.setUserName(deviceUserName);
        deviceUser.setCreatedOn(Calendar.getInstance());
        deviceUser.setCreatedBy("System");
        return deviceUser;
    }


}
