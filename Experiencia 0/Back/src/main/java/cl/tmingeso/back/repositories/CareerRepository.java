package cl.tmingeso.back.repositories;

import cl.tmingeso.back.models.Career;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerRepository extends JpaRepository<Career,Long> {

    // Get a Career by a Name.
    Career findCareerByName(String name);

    // Verify if a Career exists by a input Name
    Boolean existsCareerByName(String name);

}
