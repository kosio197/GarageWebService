package bg.web_service.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import bg.web_service.entity.RepairEntity;
import bg.web_service.model.RepairModel;
import bg.web_service.repository.RepairRepository;

@Service("repairServiceImpl")
public class RepairServiceImpl implements RepairService, Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private RepairRepository repairRepository;

    @Override
    public RepairModel findById(long id) {
        RepairEntity entity = repairRepository.findOne(id);
        if (entity != null) {
            return repairEntityToModel(entity);
        }
        return null;
    }

    @Override
    public void addRepair(RepairModel model) {
        repairRepository.save(repairModelToEntity(model));
    }

    @Override
    public List<RepairModel> getAllRepairs() {
        List<RepairModel> repairs = new ArrayList<>();
        Iterable<RepairEntity> carRepairs = repairRepository.findAllByOrderByDate(new PageRequest(0, 15));
        for (RepairEntity repairEntity : carRepairs) {
            repairs.add(repairEntityToModel(repairEntity));
        }

        return repairs;
    }

    @Override
    public List<RepairModel> findAllByCarId(Long id) {
        List<RepairModel> repairs = new ArrayList<>();
        Iterable<RepairEntity> carRepairs = repairRepository.findAllRepairByCarId(id);
        for (RepairEntity repairEntity : carRepairs) {
            repairs.add(repairEntityToModel(repairEntity));
        }

        return repairs;
    }

    @Override
    public void deleteRepair(Long id) {
        repairRepository.delete(id);
    }

    @Override
    public void deleteRepairsByCar(Long deletedCarId) {
        for (RepairModel model : findAllByCarId(deletedCarId)) {
            deleteRepair(model.getId());
        }
    }

    private RepairEntity repairModelToEntity(RepairModel model) {
        RepairEntity entity = new RepairEntity(model.getCarId(), model.getRepairDate(), model.getCurrentMilage(),
                model.getDescription(), model.getRecomendetUpcomingRepair());
        if (model.getId() != null) {
            entity.setId(model.getId());
        }
        return entity;
    }

    private RepairModel repairEntityToModel(RepairEntity entity) {
        RepairModel model = new RepairModel(entity.getId(), entity.getCarId(), entity.getRepairDate(),
                entity.getCurrentMilage(), entity.getDescription(), entity.getRecomendetUpcomingRepair());
        return model;
    }

}
