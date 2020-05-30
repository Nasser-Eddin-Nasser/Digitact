package Digitact.Backend.Storage;

import Digitact.Backend.Model.Education;
import Digitact.Backend.Model.User.Applicant;
import Digitact.Backend.Model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

import static Digitact.Backend.Storage.DB.Query.getFullApplicantsInfoQuery;

public interface IApplicantRepository extends JpaRepository<Applicant, Long> {
    @Query(value = getFullApplicantsInfoQuery, nativeQuery = true)
    Collection<Applicant> getFullApplicantsInfo();
}
