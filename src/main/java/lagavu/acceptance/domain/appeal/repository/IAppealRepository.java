package lagavu.acceptance.domain.appeal.repository;

import lagavu.acceptance.domain.appeal.entity.Appeal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAppealRepository extends JpaRepository<Appeal, Long> {
}
