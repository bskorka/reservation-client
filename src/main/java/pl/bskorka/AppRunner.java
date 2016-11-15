package pl.bskorka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by sages on 15.11.16.
 */
@Component
@Slf4j
public class AppRunner implements ApplicationRunner {

    @Autowired
    DiscoveryClient discoveryClient;

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("reservationservice");
        serviceInstances.forEach(instance -> {
            log.info(instance.getServiceId());
            log.info(instance.getUri().toString());
            log.info("Port: " + instance.getPort());
        });
    }

}
