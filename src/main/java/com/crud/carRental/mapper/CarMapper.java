package com.crud.carRental.mapper;

import com.crud.carRental.domain.Car;
import com.crud.carRental.domain.dto.CarDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarMapper {

    public Car mapToCar(final CarDto carDto) {
        return new Car(
                carDto.getId(),
                carDto.getCepikId(),
                carDto.getMark(),
                carDto.getModel(),
                carDto.getCapacity(),
                carDto.getFuel(),
                carDto.getValue()
        );
    }

    public CarDto mapToCarDto(final Car car) {
        return new CarDto(
                car.getId(),
                car.getCepikId(),
                car.getMark(),
                car.getModel(),
                car.getCapacity(),
                car.getFuel(),
                car.getValue()
        );
    }

    public List<CarDto> mapToCarDtoList(final List<Car> carList) {
        return carList.stream()
                .map(this::mapToCarDto)
                .collect(Collectors.toList());
    }
}
