package lc.dbservice.common.datasource.dynamic;

/**
 * @author cocos
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<DataSourceEnum> DATA_SOURCE_HOLDER = new ThreadLocal<>();

    public static DataSourceEnum getDataSource() {
        return DATA_SOURCE_HOLDER.get();
    }

    public static void setDataSource(DataSourceEnum key) {
        DATA_SOURCE_HOLDER.set(key);
    }

    public static void cleanDataSource() {
        DATA_SOURCE_HOLDER.remove();
    }
}
