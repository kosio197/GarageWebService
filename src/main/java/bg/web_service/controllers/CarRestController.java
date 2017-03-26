package bg.web_service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import bg.web_service.model.CarModel;
import bg.web_service.service.VehicleService;

@RestController
@RequestMapping("/admin/*")
public class CarRestController {

    @Autowired
    VehicleService vehicleService;

    // ----------------- Retrieve All Users------------------------

    @RequestMapping(value = "/allCars", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CarModel>> listAllCars() {
        List<CarModel> cars = vehicleService.getAllCars();
        if (cars.isEmpty()) {
            return new ResponseEntity<List<CarModel>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<CarModel>>(cars, HttpStatus.OK);
    }

    // ------------------ Retrieve Single User----------------------

    @RequestMapping(value = "/carById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarModel> getCar(@PathVariable("id") long id) {
        System.out.println("Fetching Car with id " + id);
        CarModel model = vehicleService.getCar(id);
        if (model == null) {
            System.out.println("Car with id " + id + " not found");
            return new ResponseEntity<CarModel>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<CarModel>(model, HttpStatus.OK);
    }

    // ---------------Create a User -------------------------------

    @RequestMapping(value = "/car/", method = RequestMethod.POST)
    public ResponseEntity<Void> createCar(@RequestBody CarModel model, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Car " + model.toString());

        vehicleService.addCar(model);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/car/{id}").buildAndExpand(model.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a User ---------------------------

    @RequestMapping(value = "/car/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CarModel> updateCAr(@PathVariable("id") long id, @RequestBody CarModel model) {
        System.out.println("Updating Car " + id);

        CarModel currentCar = vehicleService.getCar(id);

        if (currentCar == null) {
            System.out.println("Car with id " + id + " not found");
            return new ResponseEntity<CarModel>(HttpStatus.NOT_FOUND);
        }

        currentCar.setAnualCheckEndDate(model.getAnualCheckEndDate());
        currentCar.setCarRepairs(model.getCarRepairs());
        currentCar.setCurrentMilage(model.getCurrentMilage());
        currentCar.setEngineType(model.getEngineType());
        currentCar.setFullInsuranseEndDate(model.getFullInsuranseEndDate());
        currentCar.setLastVisitDate(model.getLastVisitDate());
        currentCar.setLiabilityInsuranseEndDate(model.getLiabilityInsuranseEndDate());
        currentCar.setMarka(model.getMarka());
        currentCar.setModel(model.getModel());
        currentCar.setOwnerId(model.getOwnerId());
        currentCar.setRegistrationPlate(model.getRegistrationPlate());
        currentCar.setRoadTaxisEndDate(model.getRoadTaxisEndDate());
        currentCar.setVin(model.getVin());
        currentCar.setVinnetesEndDate(model.getVinnetesEndDate());
        currentCar.setYearManifacture(model.getYearManifacture());

        vehicleService.addCar(currentCar);
        return new ResponseEntity<CarModel>(currentCar, HttpStatus.OK);
    }

    // ------------------- Delete a User -------------------

    @RequestMapping(value = "/car/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CarModel> deleteCar(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Car with id " + id);

        CarModel model = vehicleService.getCar(id);
        if (model == null) {
            System.out.println("Unable to delete. Car with id " + id + " not found");
            return new ResponseEntity<CarModel>(HttpStatus.NOT_FOUND);
        }

        vehicleService.deleteCar(id);
        return new ResponseEntity<CarModel>(HttpStatus.NO_CONTENT);
    }

}
