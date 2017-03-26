package bg.web_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.web_service.entity.CarEntity;
import bg.web_service.model.CarModel;
import bg.web_service.model.RepairModel;
import bg.web_service.repository.CarRepository;

@Service("vehicleServiceImpl")
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    CarRepository carRepository;

    @Autowired
    private RepairServiceImpl repairServiceImpl;

    @Override
    public void addCar(CarModel carModel) {
        CarEntity carEntity = modelToEntity(carModel);
        carRepository.save(carEntity);
    }

    @Override
    public List<CarModel> getUserCars(Long userId) {
        List<CarModel> cars = new ArrayList<>();
        Iterable<CarEntity> carEntitys = carRepository.findByOwnerId(userId);

        for (CarEntity carEntity : carEntitys) {
            CarModel model = entityToModel(carEntity);
            cars.add(model);
        }
        return cars;
    }

    @Override
    public CarModel getCar(Long carId) {
        CarEntity entity = carRepository.findOne(carId);
        if (entity == null) {
            return null;
        }
        CarModel model = new CarModel(entity.getId(), entity.getOwnerId(), entity.getRegistrationPlate(),
                entity.getMarka(), entity.getModel(), entity.getVin(), entity.getEngineType(),
                entity.getCurrentMilage(), entity.getYearManifacture(), entity.getRoadTaxisEndDate(),
                entity.getAnualCheckEndDate(), entity.getLiabilityInsuranseEndDate(), entity.getFullInsuranseEndDate(),
                entity.getVinnetesEndDate(), entity.getLastVisitDate(), new ArrayList<>());

        return model;
    }

    @Override
    public List<CarModel> getAllCars() {
        List<CarModel> allCars = new ArrayList<>();
        Iterable<CarEntity> cars = carRepository.findAll();
        for (CarEntity entity : cars) {
            CarModel model = new CarModel(entity.getId(), entity.getOwnerId(), entity.getRegistrationPlate(),
                    entity.getMarka(), entity.getModel(), entity.getVin(), entity.getEngineType(),
                    entity.getCurrentMilage(), entity.getYearManifacture(), entity.getRoadTaxisEndDate(),
                    entity.getAnualCheckEndDate(), entity.getLiabilityInsuranseEndDate(),
                    entity.getFullInsuranseEndDate(), entity.getVinnetesEndDate(), entity.getLastVisitDate(),
                    new ArrayList<>());
            allCars.add(model);
        }
        return allCars;
    }

    @Override
    public void deleteCar(Long carId) {
        repairServiceImpl.deleteRepairsByCar(carId);
        carRepository.delete(carId);
    }

    @Override
    public void deleteAllCarByUser(long userId) {

        for (CarEntity carEntitis : carRepository.findByOwnerId(userId)) {
            repairServiceImpl.deleteRepairsByCar(carEntitis.getId());
            carRepository.delete(carEntitis.getId());
        }
    }

    private CarModel entityToModel(CarEntity entity) {

        List<RepairModel> repairs = repairServiceImpl.findAllByCarId(entity.getId());

        CarModel model = new CarModel(entity.getId(), entity.getOwnerId(), entity.getRegistrationPlate(),
                entity.getMarka(), entity.getModel(), entity.getVin(), entity.getEngineType(),
                entity.getCurrentMilage(), entity.getYearManifacture(), entity.getRoadTaxisEndDate(),
                entity.getAnualCheckEndDate(), entity.getLiabilityInsuranseEndDate(), entity.getFullInsuranseEndDate(),
                entity.getVinnetesEndDate(), entity.getLastVisitDate(), repairs);

        return model;
    }

    private CarEntity modelToEntity(CarModel model) {
        CarEntity entity = new CarEntity(model.getOwnerId(), model.getRegistrationPlate(), model.getMarka(),
                model.getModel(), model.getVin(), model.getEngineType(), model.getCurrentMilage(),
                model.getYearManifacture(), model.getRoadTaxisEndDate(), model.getAnualCheckEndDate(),
                model.getLiabilityInsuranseEndDate(), model.getFullInsuranseEndDate(), model.getVinnetesEndDate(),
                model.getLastVisitDate());
        if (model.getId() != null) {
            entity.setId(model.getId());
        }

        return entity;
    }

}
