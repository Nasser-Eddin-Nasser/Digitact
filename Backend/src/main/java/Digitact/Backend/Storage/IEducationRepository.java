package Digitact.Backend.Storage;

import Digitact.Backend.Model.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

import static Digitact.Backend.Storage.DB.Query.getAllEducationsQuery;
import static Digitact.Backend.Storage.DB.Query.getEducationsByUserID;

@Repository
public interface IEducationRepository extends JpaRepository<Education, Long> {
  List<Education> findAll();

  @Query(value = getEducationsByUserID, nativeQuery = true)
  Collection<Education> getEducationsByUser(Long applicantID);

  @Query(value = getAllEducationsQuery, nativeQuery = true)
  Collection<Education> getAllEducationsInfo();
}
