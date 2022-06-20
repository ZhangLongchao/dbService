package lc.dbservice.driver.controller;

import com.flowsea.common.result.JsonResult;
import lc.dbservice.driver.entity.DriverEntity;
import lc.dbservice.driver.vo.DriverVO;
import lc.dbservice.driver.service.DriverService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author cocos
 */
@RestController
@RequestMapping(path = "driver")
public class DriverController {

    private final DriverService service;
   public DriverController(DriverService service){
       this.service = service;
   }

    @GetMapping
    public JsonResult<List<DriverEntity>> findAll(){
       return JsonResult.success(service.findAll());
    }

    @PostMapping
    public JsonResult<Boolean> insert(@Validated(value = DriverVO.Insert.class) DriverVO vo){
        return JsonResult.success(service.save(vo));
    }

    @DeleteMapping("{id}")
    public JsonResult<Boolean> delete(@PathVariable(value = "id",required = true)Long id){
        //TODO: connections in use cannot be deleted
       return JsonResult.success(service.del(id));
    }
}
