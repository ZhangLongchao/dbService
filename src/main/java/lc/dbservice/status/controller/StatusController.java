package lc.dbservice.status.controller;

import com.flowsea.common.result.JsonResult;
import lc.dbservice.connection.controller.ConnectionController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cocos
 */
@RestController
@RequestMapping(value = "status")
public class StatusController {

    @GetMapping
    public JsonResult<?> findStatus(ConnectionController.Param param) {
        //TODO:
        return null;
    }

    @GetMapping(value = "{id}")
    public JsonResult<?> findCondition(@PathVariable(required = true) String id){

        return null;
    }
}
