package Digitact.Backend.Storage;

import static Digitact.Backend.Storage.DB.Query.getAllWorkExperiencesQuery;
import static Digitact.Backend.Storage.DB.Query.getWorkExperiencesByUserID;

import Digitact.Backend.Model.WorkExperience;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IEducationRepository extends JpaRepository<WorkExperience, Long> {
    List<WorkExperience> findAll();

    @Query(value = getWorkExperiencesByUserID, nativeQuery = true)
    Collection<WorkExperience> getWorkExperienceByUser(Long applicantID);

    @Query(value = getAllWorkExperiencesQuery, nativeQuery = true)
    Collection<WorkExperience> getAllWorkExperiencesInfo();
}
