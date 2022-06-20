package lc.dbservice.driver.service;

import lc.dbservice.driver.dao.DriverRepository;
import lc.dbservice.driver.entity.DriverEntity;
import lc.dbservice.driver.vo.DriverVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author cocos
 */
@Service
public class DriverService {

    private final DriverRepository repository;

    public DriverService(DriverRepository repository) {
        Assert.notNull(repository,"参数为不能为 null");
        this.repository = repository;
    }

    public List<DriverEntity> findAll(){
       return repository.findAll();
    }

    public Boolean save(DriverVO vo){
        DriverEntity entity = new DriverEntity();
        BeanUtils.copyProperties(vo,entity);
        repository.save(entity);
        return true;
    }

    public Boolean del(long id){
        repository.deleteById(id);
        return true;
    }
}
