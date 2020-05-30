package Digitact.Backend.Storage;

import Digitact.Backend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

import static Digitact.Backend.Storage.DB.Query.getApplicantQuery;

/**
 * Java Persistence API for the integration between the Database and the App
 */
@Repository
public interface IDataRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    @Query(value = getApplicantQuery, nativeQuery = true)
    Collection<User> getApplicants();
}
