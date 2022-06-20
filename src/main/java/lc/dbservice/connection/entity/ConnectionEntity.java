package lc.dbservice.connection.entity;

import lc.dbservice.common.config.SnowflakeIdGenerator;
import lc.dbservice.driver.entity.DriverEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author cocos
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fs_connection")
public class ConnectionEntity {

    @Id
    @GeneratedValue(generator = "custom")
    @GenericGenerator(name = "custom", strategy = SnowflakeIdGenerator.GENERATOR)
    private Long id;

    /**
     * 非空
     */
    @Column(name = "name")
    private String name;

    @Column(name = "host")
    private String host;

    @Column(name = "port")
    private int port;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "character")
    private String character;

    @Column(name = "ssl")
    private String ssl;

    @Column(name = "initial_size")
    private String initialSize;

    @Column(name = "max_active")
    private int maxActive;

    @Column(name = "min_idle")
    private int minIdle;

    @Column(name = "max_wait")
    private int maxWait;

    @Column(name = "min_idle_time")
    private int minIdleTime;

    @Column(name = "max_idle_time")
    private int maxIdleTime;

    /**
     * 非空
     */
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private DriverEntity driver;

}
