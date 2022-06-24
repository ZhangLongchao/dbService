package lc.dbservice.common.datasource.dynamic;

/**
 * @author cocos
 */

public enum DataSourceEnum {
    /**
     *
     */
    SQLITE(1),MARIADB(2),ORACLE(3);

    DataSourceEnum(int number){
        this.number=number;
    }

    private final int number;


    public int getNumber() {
        return number;
    }
}
