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
                carDto.getCepikId(),
                carDto.getMark(),
                carDto.getModel(),
                carDto.getCapacity(),
                carDto.getRegistration()
        );
    }

    public CarDto mapToCarDto(final Car car) {
        return new CarDto(
                car.getCepikId(),
                car.getMark(),
                car.getModel(),
                car.getCapacity(),
                car.getRegistration()
        );
    }

    public List<CarDto> mapToCarDtoList(final List<Car> carList) {
        return carList.stream()
                .map(this::mapToCarDto)
                .collect(Collectors.toList());
    }

    public List<Car> mapToCarList(final List<CarDto> carDtoList) {
        return carDtoList.stream()
                .map(this::mapToCar)
                .collect(Collectors.toList());
    }
}
