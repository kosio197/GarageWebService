package bg.web_service.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import bg.web_service.entity.CarEntity;

@Repository
public interface CarRepository extends CrudRepository<CarEntity, Long> {

    @Query(value = "select c from cars as c where c.ownerId =:userId")
    Iterable<CarEntity> findByOwnerId(@Param("userId") Long userId);

    @Query(value = "select c from cars as c left join users as u  on u.id = c.ownerId "
            + "where  to_days(c.roadTaxisEndDate) - to_days(curdate()) < u.daysToEvent ")
    Iterable<CarEntity> findAllByRoadTaxisDeadLine();

    @Query(value = "select c from cars as c left join users as u  on u.id = c.ownerId "
            + "where  to_days(c.anualCheckEndDate) - to_days(curdate()) < u.daysToEvent")
    Iterable<CarEntity> findAllByAnnualCheckDeadLine();

    @Query(value = "select c from cars as c left join users as u  on u.id = c.ownerId "
            + "where  to_days(c.liabilityInsuranseEndDate) - to_days(curdate()) < u.daysToEvent")
    Iterable<CarEntity> findAllByLiabilityDeadLine();

    @Query(value = "select c from cars as c left join users as u  on u.id = c.ownerId "
            + "where  to_days(c.fullInsuranseEndDate) - to_days(curdate()) < u.daysToEvent")
    Iterable<CarEntity> findAllByFullInsurancesDeadLine();

    @Query(value = "select c from cars as c left join users as u  on u.id = c.ownerId "
            + "where  to_days(c.vinnetesEndDate) - to_days(curdate()) < u.daysToEvent")
    Iterable<CarEntity> findAllByVinettesDeadLine();

}
