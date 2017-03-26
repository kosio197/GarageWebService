package bg.web_service.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component("repairModel")
public class RepairModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long carId;
    private Date repairDate;
    private Integer currentMilage;
    private String description;
    private String recomendetUpcomingRepair;

    public RepairModel() {

    }

    public RepairModel(Long carId, Date repairDate, Integer currentMilage, String description,
            String recomendetUpcomingRepair) {
        this();
        this.carId = carId;
        this.repairDate = repairDate;
        this.currentMilage = currentMilage;
        this.description = description;
        this.recomendetUpcomingRepair = recomendetUpcomingRepair;
    }

    public RepairModel(Long id, Long carId, Date repairDate, Integer currentMilage, String description,
            String recomendetUpcomingRepair) {
        this();
        this.id = id;
        this.carId = carId;
        this.repairDate = repairDate;
        this.currentMilage = currentMilage;
        this.description = description;
        this.recomendetUpcomingRepair = recomendetUpcomingRepair;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Date getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(Date repairDate) {
        this.repairDate = repairDate;
    }

    public Integer getCurrentMilage() {
        return currentMilage;
    }

    public void setCurrentMilage(Integer currentMilage) {
        this.currentMilage = currentMilage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecomendetUpcomingRepair() {
        return recomendetUpcomingRepair;
    }

    public void setRecomendetUpcomingRepair(String recomendetUpcomingRepair) {
        this.recomendetUpcomingRepair = recomendetUpcomingRepair;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RepairModel) {
            if (((RepairModel) obj).getId() == this.id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.repairDate.toString() + "" + this.currentMilage;
    }

}
