package Digitact.Backend.Storage;

import static Digitact.Backend.Storage.DB.Query.setStatusQuery;

import Digitact.Backend.Model.User.User;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatusRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    @Modifying
    @Transactional
    @Query(value = setStatusQuery, nativeQuery = true)
    int setStatus(Integer status, Long appID);
}
