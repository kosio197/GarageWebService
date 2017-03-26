package bg.web_service.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "repairs")
public class RepairEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long carId;
    private Date repairDate;
    private Integer currentMilage;
    private String description;
    private String recomendetUpcomingRepair;

    public RepairEntity() {

    }

    public RepairEntity(Long carId, Date repairDate, Integer currentMilage, String description,
            String recomendetUpcomingRepair) {
        super();
        this.carId = carId;
        this.repairDate = repairDate;
        this.currentMilage = currentMilage;
        this.description = description;
        this.recomendetUpcomingRepair = recomendetUpcomingRepair;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "car_id")
    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    @Column(name = "repair_date")
    public Date getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(Date repairDate) {
        this.repairDate = repairDate;
    }

    @Column(name = "milage")
    public Integer getCurrentMilage() {
        return currentMilage;
    }

    public void setCurrentMilage(Integer currentMilage) {
        this.currentMilage = currentMilage;
    }

    @Basic
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "recomendet_upcoming_repairs")
    public String getRecomendetUpcomingRepair() {
        return recomendetUpcomingRepair;
    }

    public void setRecomendetUpcomingRepair(String recomendetUpcomingRepair) {
        this.recomendetUpcomingRepair = recomendetUpcomingRepair;
    }

}
