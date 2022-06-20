package lc.dbservice.connection.controller;

import com.flowsea.common.result.JsonResult;
import lc.dbservice.connection.entity.ConnectionEntity;
import lc.dbservice.connection.vo.ConnectionVO;
import lc.dbservice.connection.service.ConnectionService;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author cocos
 */
@RestController
@RequestMapping(path = "connection")
public class ConnectionController {

    private final ConnectionService service;

    public ConnectionController(ConnectionService service) {
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public JsonResult<List<ConnectionEntity>> findById(@PathVariable(name = "id") String id) {
        return JsonResult.success(service.find(id));
    }

    @GetMapping
    public JsonResult<List<ConnectionEntity>> find(Param param) {
        return JsonResult.success(service.find(Optional.ofNullable(param.name).orElse(param.id)));
    }

    @PostMapping
    public JsonResult<Boolean> insert(@RequestBody ConnectionVO vo) {
        return JsonResult.success(service.save(vo));
    }

    @PutMapping
    public JsonResult<Boolean> update() {
        //TODO: connections in use cannot be update
        return JsonResult.success(true);
    }

    @DeleteMapping(path = {"/{id}"})
    public JsonResult<Boolean> delete(@PathVariable(name = "id", required = true) String id) {
        //TODO: connections in use cannot be deleted
        return JsonResult.success(service.del(id));
    }

    @Data
    public static class Param {
        private String id;
        private String name;
    }
}
