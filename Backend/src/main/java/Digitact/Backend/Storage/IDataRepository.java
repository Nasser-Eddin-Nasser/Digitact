package Digitact.Backend.Storage;

import static Digitact.Backend.Storage.DB.Query.getApplicantQuery;
import static Digitact.Backend.Storage.DB.Query.getFullApplicantsInfoQuery;

import Digitact.Backend.Model.User.Applicant;
import Digitact.Backend.Model.User.User;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/** Java Persistence API for the integration between the Database and the App */
@Repository
public interface IDataRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    @Query(value = getApplicantQuery, nativeQuery = true)
    Collection<Applicant> getApplicants();

    @Query(value = getFullApplicantsInfoQuery, nativeQuery = true)
    Collection<Applicant> getFullApplicantsInfo();
}
