package Digitact.Backend.Storage;

import Digitact.Backend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DataRepository extends JpaRepository<User, Long> {
    List<User> findAll();
}
