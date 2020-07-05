package Digitact.Backend.Storage;

import Digitact.Backend.Model.User.Applicant;
import Digitact.Backend.Model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;

import static Digitact.Backend.Storage.DB.Query.*;

@Repository
public interface IHRInfoRepository extends JpaRepository<User, Long> {
   List<User> findAll();

    @Modifying
    @Transactional
    @Query(value = setStatusQuery, nativeQuery = true)
    int setStatus( Integer status, Long appID);
    @Modifying
    @Transactional
    @Query(value = setHRCommentQuery, nativeQuery = true)
    int setHRComment( String comment, Long appID);

}
