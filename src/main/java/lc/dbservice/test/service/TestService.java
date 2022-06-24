package lc.dbservice.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author cocos
 */
@Service
public class TestService {
    @Autowired
    private JdbcTemplate template;
    public List<Map<String, Object>> search(){
        return template.queryForList("select * from rcs_menus");
    }
}
