package lc.dbservice.sql.controller;

import com.flowsea.common.result.JsonResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cocos
 */
@RestController
@RequestMapping(value = "sql")
public class SqlController {

    @PostMapping
    public JsonResult<?> execSql(){
        //TODO:
        return null;
    }
}
