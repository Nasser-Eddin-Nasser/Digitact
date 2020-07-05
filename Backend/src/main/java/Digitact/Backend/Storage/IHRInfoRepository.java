package Digitact.Backend.Storage;

import static Digitact.Backend.Storage.DB.Query.*;

import Digitact.Backend.Model.User.User;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IHRInfoRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    @Modifying
    @Transactional
    @Query(value = setStatusQuery, nativeQuery = true)
    int setStatus(Integer status, Long appID);

    @Modifying
    @Transactional
    @Query(value = setHRCommentQuery, nativeQuery = true)
    int setHRComment(String comment, Long appID);
}
