package ru.itis.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.dto.CarDto;
import ru.itis.models.Car;
import ru.itis.repositories.CarsRepository;

import java.util.List;

import static ru.itis.dto.CarDto.from;

@RequiredArgsConstructor
@Service
public class CarsServiceImpl implements CarsService {
    private final CarsRepository carsRepository;

    @Override
    public List<CarDto> addCar(CarDto car) {
        Car newCar = Car.builder()
                .model(car.getModel())
                .color(car.getColor())
                .build();

        carsRepository.save(newCar);

        return from(carsRepository.findAll());
    }

    @Override
    public List<CarDto> getAll() {
        return from(carsRepository.findAll());
    }
}
