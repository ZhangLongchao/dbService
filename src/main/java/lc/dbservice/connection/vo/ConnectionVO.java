package lc.dbservice.connection.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;


/**
 * @author cocos
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionVO {

    @NotNull(groups = {Update.class})
    private Long id;

    /**
     * 非空
     */
    @NotNull(groups = {Insert.class})
    private String name;

    @Null(groups = Insert.class)
    private String host;

    @Null(groups = Insert.class)
    @Positive(groups = Insert.class)
    @Max(groups = Insert.class, value = 65535)
    private Integer port;

    @Null(groups = Insert.class)
    private String username;

    @Null(groups = Insert.class)
    private String password;

    @Null(groups = Insert.class)
    private String character;

    @Null(groups = Insert.class)
    private String ssl;

    @Null(groups = Insert.class)
    private String initialSize;

    @Null(groups = Insert.class)
    private Integer maxActive;

    @Null(groups = Insert.class)
    private Integer minIdle;

    @Null(groups = Insert.class)
    private Integer maxWait;

    @Null(groups = Insert.class)
    private Integer minIdleTime;

    @Null(groups = Insert.class)
    private Integer maxIdleTime;

    public interface Insert {

    }

    public interface Update {

    }
}
