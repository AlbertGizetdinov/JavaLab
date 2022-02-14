package ru.itis.services;

import ru.itis.dto.CarDto;

import java.util.List;

public interface CarsService {
    List<CarDto> addCar(CarDto car);

    List<CarDto> getAll();
}
