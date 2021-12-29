package com.crud.carRental.scheduler;

import com.crud.carRental.client.CepikClient;
import com.crud.carRental.domain.dto.CarDto;
import com.crud.carRental.domain.dto.cepik.CepikCarDto;
import com.crud.carRental.mapper.CarMapper;
import com.crud.carRental.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CarScheduler {

    @Autowired
    private final CepikClient client;
    @Autowired
    private final CarService service;
    @Autowired
    private final CarMapper mapper;

    BigDecimal value;

    @Scheduled(cron = "0 0 10 * * *")
    public void saveCarInDatabase() {
        List<CepikCarDto> newCar = client.getCepikCars();
        newCar.forEach(c -> {
            if (service.findByCepikId(c.getId()).isEmpty()) {
                if (c.getAttributes().getCapacity() < 1000) {
                    BigDecimal value = new BigDecimal(50000);
                } else if (c.getAttributes().getCapacity() > 2000) {
                    BigDecimal value = new BigDecimal(100000);
                } else {
                    BigDecimal value = new BigDecimal(70000);
                }

                service.save(mapper.mapToCar(
                        new CarDto(
                                null,
                                c.getId(),
                                c.getAttributes().getMark(),
                                c.getAttributes().getModel(),
                                c.getAttributes().getCapacity(),
                                c.getAttributes().getFuel(),
                                value
                        )));
            }
        });
    }
}
