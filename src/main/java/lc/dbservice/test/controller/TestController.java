package lc.dbservice.test.controller;

import com.flowsea.common.result.JsonResult;
import lc.dbservice.datasource.dynamic.DataSourceContextHolder;
import lc.dbservice.datasource.dynamic.DataSourceEnum;
import lc.dbservice.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author cocos
 */
@RestController
@RequestMapping(value = "test")
public class TestController {
    @Autowired
    TestService service;

    @PostMapping
    public JsonResult<?> testConnection() throws InterruptedException {

        DataSourceContextHolder.setDataSource(DataSourceEnum.ORACLE);
        List<Map<String, Object>> search = service.search();
        DataSourceContextHolder.cleanDataSource();
        return null;
    }

    @PostMapping(value = "v1")
    public JsonResult<?> testConnection2() throws InterruptedException {

        DataSourceContextHolder.setDataSource(DataSourceEnum.MARIADB);
        List<Map<String, Object>> search = service.search();
        DataSourceContextHolder.cleanDataSource();
        return null;
    }
}
