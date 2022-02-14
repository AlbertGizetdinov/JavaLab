package ru.itis.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.dto.CarDto;
import ru.itis.services.CarsService;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/cars")
public class CarsController {
    private final CarsService carsService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<CarDto> addCar(@RequestBody CarDto car) {
        return carsService.addCar(car);
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getCarsPage(Model model) {
        model.addAttribute("cars", carsService.getAll());
        return "cars";
    }
}
