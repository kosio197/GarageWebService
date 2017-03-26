package bg.web_service.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "cars")
public class CarEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private Long ownerId;
    private String registrationPlate;
    private String marka;
    private String model;
    private String vin;
    private String engineType;
    private Integer currentMilage;
    private Date lastVisitDate;
    private Date yearManifacture;
    private Date roadTaxisEndDate;
    private Date anualCheckEndDate;
    private Date liabilityInsuranseEndDate;
    private Date fullInsuranseEndDate;
    private Date vinnetesEndDate;

    public CarEntity() {

    }

    public CarEntity(Long ownerId, String registrationPlate, String marka, String model, String vin, String engineType,
            Integer currentMilage, Date yearManifacture, Date roadTaxisEndDate, Date anualCheckEndDate,
            Date liabilityInsuranseEndDate, Date fullInsuranseEndDate, Date vinnetesEndDate, Date lastVisitDate) {
        super();
        this.ownerId = ownerId;
        this.registrationPlate = registrationPlate;
        this.marka = marka;
        this.model = model;
        this.vin = vin;
        this.engineType = engineType;
        this.currentMilage = currentMilage;
        this.yearManifacture = yearManifacture;
        this.roadTaxisEndDate = roadTaxisEndDate;
        this.anualCheckEndDate = anualCheckEndDate;
        this.liabilityInsuranseEndDate = liabilityInsuranseEndDate;
        this.fullInsuranseEndDate = fullInsuranseEndDate;
        this.vinnetesEndDate = vinnetesEndDate;
        this.lastVisitDate = lastVisitDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "owner_id")
    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    @Column(name = "registration_plate", nullable = false)
    public String getRegistrationPlate() {
        return registrationPlate;
    }

    public void setRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
    }

    @Column(name = "marka", nullable = false)
    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    @Column(name = "model", nullable = false)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column(name = "vin")
    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Column(name = "year_manufacture", nullable = false)
    public Date getYearManifacture() {
        return yearManifacture;
    }

    public void setYearManifacture(Date yearManifacture) {
        this.yearManifacture = yearManifacture;
    }

    @Column(name = "engine_type", nullable = false)
    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    @Column(name = "current_milage", nullable = false)
    public Integer getCurrentMilage() {
        return currentMilage;
    }

    public void setCurrentMilage(Integer currentMilage) {
        this.currentMilage = currentMilage;
    }

    @Column(name = "road_taxis_end_date")
    public Date getRoadTaxisEndDate() {
        return roadTaxisEndDate;
    }

    public void setRoadTaxisEndDate(Date roadTaxisEndDate) {
        this.roadTaxisEndDate = roadTaxisEndDate;
    }

    @Column(name = "annual_check_end_date")
    public Date getAnualCheckEndDate() {
        return anualCheckEndDate;
    }

    public void setAnualCheckEndDate(Date anualCheckEndDate) {
        this.anualCheckEndDate = anualCheckEndDate;
    }

    @Column(name = "liability_insurances_end_date")
    public Date getLiabilityInsuranseEndDate() {
        return liabilityInsuranseEndDate;
    }

    public void setLiabilityInsuranseEndDate(Date liabilityInsuranseEndDate) {
        this.liabilityInsuranseEndDate = liabilityInsuranseEndDate;
    }

    @Column(name = "full_insurances_end_date")
    public Date getFullInsuranseEndDate() {
        return fullInsuranseEndDate;
    }

    public void setFullInsuranseEndDate(Date fullInsuranseEndDate) {
        this.fullInsuranseEndDate = fullInsuranseEndDate;
    }

    @Column(name = "vinettes__end_date")
    public Date getVinnetesEndDate() {
        return vinnetesEndDate;
    }

    public void setVinnetesEndDate(Date vinnetesEndDate) {
        this.vinnetesEndDate = vinnetesEndDate;
    }

    @Column(name = "last_visit_date")
    public Date getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(Date lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

}
