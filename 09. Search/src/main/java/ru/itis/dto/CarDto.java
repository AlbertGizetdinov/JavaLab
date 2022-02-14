package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Car;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDto {
    private Integer id;
    private String model;
    private String color;

    public static CarDto from(Car car) {
        return CarDto.builder()
                .id(car.getId())
                .model(car.getModel())
                .color(car.getColor())
                .build();
    }

    public static List<CarDto> from(List<Car> cars) {
        return cars.stream().map(CarDto::from).collect(Collectors.toList());
    }
}
