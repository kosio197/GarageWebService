package bg.web_service.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import bg.web_service.entity.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Query(value = "SELECT u FROM users u WHERE u.username=:username and u.password=:password")
    UserEntity findOneByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query(value = "SELECT u FROM users u WHERE u.username=:username")
    UserEntity findOneByUsername(@Param("username") String username);

    @Query(value = "SELECT u FROM users u WHERE u.email=:email")
    UserEntity findOneByEmail(@Param("email") String email);

}