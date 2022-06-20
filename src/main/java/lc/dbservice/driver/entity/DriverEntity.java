package lc.dbservice.driver.entity;

import lc.dbservice.common.config.SnowflakeIdGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


/**
 * @author cocos
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "fs_driver")
public class DriverEntity {


    @Id
    @GeneratedValue(generator = "custom")
    @GenericGenerator(name = "custom", strategy = SnowflakeIdGenerator.GENERATOR)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "folder_name")
    private String folderName;
}
