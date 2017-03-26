package bg.web_service.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bg.web_service.entity.UserEntity;
import bg.web_service.model.CarModel;
import bg.web_service.model.UserModel;
import bg.web_service.repository.UserRepository;

@Service("userServiceImpl")
public class UserServiceImpl implements UserService, Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleServiceImpl vehicleServiceImpl;

    @Override
    public void invalidateUserSession() {
        try {
            FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addUser(UserModel userModel) {
        UserEntity userEntity = userModelToEntity(userModel);
        userRepository.save(userEntity);
    }

    @Override
    public UserModel findById(long id) {
        UserEntity entity = userRepository.findOne(id);
        return userEntityToModel(entity);
    }

    @Override
    public String getUserNameById(Long ownerId) {
        UserEntity entity = userRepository.findOne(ownerId);
        return entity.getFirstName() + " " + entity.getLastName();
    }

    @Override
    public UserModel getUserByUsername(String username) {
        UserEntity userEntity = userRepository.findOneByUsername(username);
        return userEntityToModel(userEntity);
    }

    @Override
    public UserModel getUserByEmail(String email) {
        UserEntity userEntity = userRepository.findOneByEmail(email);
        return userEntityToModel(userEntity);
    }

    @Override
    public List<UserModel> getAllUsers() {
        List<UserModel> models = new ArrayList<>();
        Iterable<UserEntity> entities = userRepository.findAll();
        for (UserEntity userEntity : entities) {
            UserModel model = new UserModel(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword(),
                    userEntity.getRole(), userEntity.getFirstName(), userEntity.getLastName(), userEntity.getEmail(),
                    userEntity.getLastVisite(), userEntity.getTelephone(), userEntity.getDaysToEvent(),
                    new ArrayList<>());
            models.add(model);
        }
        return models;
    }

    @Override
    public void deleteUser(Long userId) {
        vehicleServiceImpl.deleteAllCarByUser(userId);
        userRepository.delete(userId);
    }

    private UserModel userEntityToModel(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }

        List<CarModel> cars = vehicleServiceImpl.getUserCars(userEntity.getId());

        UserModel model = new UserModel(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword(),
                userEntity.getRole(), userEntity.getFirstName(), userEntity.getLastName(), userEntity.getEmail(),
                userEntity.getLastVisite(), userEntity.getTelephone(), userEntity.getDaysToEvent(), cars);
        return model;
    }

    private UserEntity userModelToEntity(UserModel model) {
        UserEntity entity = new UserEntity(model.getUsername(), model.getPassword(), model.getRole(),
                model.getFirstName(), model.getLastName(), model.getEmail(), model.getLastVisit(), model.getTelephone(),
                model.getDaysToEvent());
        if (model.getId() != null) {
            entity.setId(model.getId());
        }

        return entity;

    }

}
