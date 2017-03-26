package bg.web_service.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import bg.web_service.entity.RepairEntity;

@Repository
public interface RepairRepository extends CrudRepository<RepairEntity, Long> {

    @Query(value = "SELECT r FROM repairs r WHERE r.carId =:carId")
    Iterable<RepairEntity> findAllRepairByCarId(@Param("carId") Long carId);

    @Query(value = "SELECT r FROM repairs r ORDER BY r.repairDate DESC ")
    List<RepairEntity> findAllByOrderByDate(Pageable pageable);

}
