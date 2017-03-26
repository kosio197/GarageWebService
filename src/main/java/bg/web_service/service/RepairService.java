package bg.web_service.service;

import java.util.List;

import bg.web_service.model.RepairModel;

public interface RepairService {

    List<RepairModel> getAllRepairs();

    void addRepair(RepairModel model);

    void deleteRepair(Long repairId);

    List<RepairModel> findAllByCarId(Long id);

    void deleteRepairsByCar(Long deletedCarId);

    RepairModel findById(long id);

}
