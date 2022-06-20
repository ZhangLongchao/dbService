package lc.dbservice.driver.dao;

import lc.dbservice.driver.entity.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author cocos
 */
public interface DriverRepository extends JpaRepository<DriverEntity,Long> {
}
