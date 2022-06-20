package lc.dbservice.connection.service;


import lc.dbservice.connection.dao.ConnectionRepository;
import lc.dbservice.connection.entity.ConnectionEntity;
import lc.dbservice.connection.vo.ConnectionVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * @author cocos
 */
@Service
public class ConnectionService {
    private final ConnectionRepository repository;

    public ConnectionService(ConnectionRepository repository) {
        this.repository = repository;
    }

    public List<ConnectionEntity> find(String name) {
        return Optional.ofNullable(name).isPresent() ? repository.findAllByIdEqualsOrNameEquals(numberFormat(name), name) : repository.findAll();
    }

    public Boolean save(ConnectionVO vo) {
        repository.save(to(vo));
        return true;
    }

    public Boolean del(String id) {
        repository.deleteByIdOrName(numberFormat(id),id);
        return  true;
    }

    private static ConnectionEntity to(ConnectionVO vo) {
        ConnectionEntity entity = new ConnectionEntity();
        BeanUtils.copyProperties(vo, entity);
        return entity;
    }

    private static Long numberFormat(String str) {
        Long l;
        try {
            l = Long.valueOf(str);
        } catch (NumberFormatException exception) {
            l = null;
        }
        return l;
    }
}
