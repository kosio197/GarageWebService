package bg.web_service.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;
    private String password;
    private String role = "USER";
    private String firstName;
    private String lastName;
    private String email;
    private Date lastVisit = new Date();
    private String telephone;
    private Integer daysToEvent;
    private List<CarModel> userCars;

    public UserModel() {
        this.userCars = new ArrayList<>();
    }

    public UserModel(String username, String password, String firstName, String lastName, String email,
            String telephone, Integer daysToEvent) {
        this();
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.telephone = telephone;
        this.daysToEvent = daysToEvent;
    }

    public UserModel(Long id, String username, String password, String role, String firstName, String lastName,
            String email, Date lastVisit, String telephone, Integer daysToEvent, List<CarModel> userCars) {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.lastVisit = lastVisit;
        this.telephone = telephone;
        this.daysToEvent = daysToEvent;
        this.userCars = userCars;
    }

    public void addCar(CarModel carModel) {
        this.userCars.add(carModel);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(Date lastVisit) {
        this.lastVisit = lastVisit;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getDaysToEvent() {
        return daysToEvent;
    }

    public void setDaysToEvent(Integer daysToEvent) {
        this.daysToEvent = daysToEvent;
    }

    public List<CarModel> getUserCars() {
        return userCars;
    }

    public void setUserCars(List<CarModel> userCars) {
        this.userCars = userCars;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UserModel) {
            if (((UserModel) obj).getId() == this.id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName + "   email " + this.email;
    }

}
