package lc.dbservice.datasource.dbentity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author cocos
 */
@Data
@Component
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "spring.datasource.sqlite")
public class SqliteEntity {

    private String jdbcUrl;

    private String driverClassName;
}
