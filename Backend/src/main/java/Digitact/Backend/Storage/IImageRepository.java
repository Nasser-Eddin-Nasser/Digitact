package Digitact.Backend.Storage;

import static Digitact.Backend.Storage.DB.Query.getImageByID;

import Digitact.Backend.Model.Image.AppImage;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IImageRepository extends JpaRepository<AppImage, Long> {
    List<AppImage> findAll();

    @Query(value = getImageByID, nativeQuery = true)
    AppImage getImageByID(String ImageID);
}
