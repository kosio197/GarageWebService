package bg.web_service.service;

import java.util.List;

import bg.web_service.model.CarModel;

public interface VehicleService {

    CarModel getCar(Long carId);

    void addCar(CarModel carModel);

    void deleteCar(Long carId);

    List<CarModel> getUserCars(Long userId);

    List<CarModel> getAllCars();

    void deleteAllCarByUser(long userId);
}
