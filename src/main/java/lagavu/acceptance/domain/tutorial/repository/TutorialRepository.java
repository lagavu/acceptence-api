package lagavu.acceptance.domain.tutorial.repository;

import java.util.List;

import lagavu.acceptance.domain.tutorial.entity.Tutorial;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
    List<Tutorial> findByPublished(boolean published);

    List<Tutorial> findByTitleContaining(String title);
}