package lc.dbservice.datasource.dynamic;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author cocos
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    protected Object determineCurrentLookupKey(){
        return DataSourceContextHolder.getDataSource();
    }

    /***
     * 初始化动态数据源列表
     * @param sqlite
     * @param mariadb
     * @param oracle
     */
    public void initDataSource(DataSource sqlite,DataSource mariadb,DataSource oracle){
        this.setDefaultTargetDataSource(sqlite);
        Map<Object, Object> sourceMap = new HashMap<>(10);
        sourceMap.put(DataSourceEnum.SQLITE,sqlite);
        sourceMap.put(DataSourceEnum.MARIADB,mariadb);
        sourceMap.put(DataSourceEnum.ORACLE,oracle);
        this.setTargetDataSources(sourceMap);

    }

}
