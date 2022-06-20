package lc.dbservice.connection.dao;

import lc.dbservice.connection.entity.ConnectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * @author cocos
 */
public interface ConnectionRepository extends JpaRepository<ConnectionEntity, Long> {
    /** 根据通过连接ID或者连接名称获取连接信息
     * @param id   主键标识
     * @param name 连接名称
     * @return 根据条件查询的对应信息
     */
    List<ConnectionEntity> findAllByIdEqualsOrNameEquals(Long id,String name);

    /**
     * 根据 id 或连接名称删除对应连接配置
     * @param id 主键标识
     * @param name 连接名称
     */
     void deleteByIdOrName(Long id, String name);
}
