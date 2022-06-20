package lc.dbservice.common.config;

import com.flowsea.common.snowflake.SnowflakeShardingKeyGenerator;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;


/**
 * @author cocos
 */
@Component
public class SnowflakeIdGenerator implements IdentifierGenerator {

    public static final String GENERATOR = "lc.dbservice.common.config.SnowflakeIdGenerator";
    private static SnowflakeShardingKeyGenerator generator;

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return Objects.requireNonNull(generator, "generator is null").generateKey();
    }

    @Autowired
    public void setGenerator(SnowflakeShardingKeyGenerator generator) {
        SnowflakeIdGenerator.generator = generator;
    }
}
