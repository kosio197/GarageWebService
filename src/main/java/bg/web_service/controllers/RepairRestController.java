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

import bg.web_service.model.RepairModel;
import bg.web_service.service.RepairService;

@RestController
@RequestMapping("/admin/*")
public class RepairRestController {

    @Autowired
    RepairService repairService;

    // ----------------- Retrieve All Repairs ------------------------

    @RequestMapping(value = "/allRepairs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<RepairModel>> listAllRepairs() {
        List<RepairModel> repairs = repairService.getAllRepairs();
        if (repairs.isEmpty()) {
            return new ResponseEntity<List<RepairModel>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<RepairModel>>(repairs, HttpStatus.OK);
    }

    // ------------------ Retrieve Single Repair ----------------------

    @RequestMapping(value = "/repairById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RepairModel> getRepair(@PathVariable("id") long id) {
        System.out.println("Fetching Repair with id " + id);
        RepairModel model = repairService.findById(id);
        if (model == null) {
            System.out.println("Repair with id " + id + " not found");
            return new ResponseEntity<RepairModel>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<RepairModel>(model, HttpStatus.OK);
    }

    // ---------------Create Repair -------------------------------

    @RequestMapping(value = "/repair/", method = RequestMethod.POST)
    public ResponseEntity<Void> createRepair(@RequestBody RepairModel model, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Repair " + model.getRepairDate());
        repairService.addRepair(model);

        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a Repair ---------------------------

    @RequestMapping(value = "/repair/{id}", method = RequestMethod.PUT)
    public ResponseEntity<RepairModel> updateRepair(@PathVariable("id") long id, @RequestBody RepairModel model) {
        System.out.println("Updating Repair " + id);

        RepairModel currenRepair = repairService.findById(id);

        if (currenRepair == null) {
            System.out.println("Repair with id " + id + " not found");
            return new ResponseEntity<RepairModel>(HttpStatus.NOT_FOUND);
        }

        currenRepair.setCarId(model.getCarId());
        currenRepair.setCurrentMilage(model.getCurrentMilage());
        currenRepair.setDescription(model.getDescription());
        currenRepair.setRecomendetUpcomingRepair(model.getRecomendetUpcomingRepair());
        currenRepair.setRepairDate(model.getRepairDate());

        repairService.addRepair(currenRepair);
        return new ResponseEntity<RepairModel>(currenRepair, HttpStatus.OK);
    }

    // ------------------- Delete a User -------------------

    @RequestMapping(value = "/repair/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<RepairModel> deleteRepair(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Repair with id " + id);

        RepairModel model = repairService.findById(id);
        if (model == null) {
            System.out.println("Unable to delete. Repair with id " + id + " not found");
            return new ResponseEntity<RepairModel>(HttpStatus.NOT_FOUND);
        }

        repairService.deleteRepair(id);
        return new ResponseEntity<RepairModel>(HttpStatus.NO_CONTENT);
    }

}
