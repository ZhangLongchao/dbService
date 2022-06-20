package lc.dbservice.driver.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author cocos
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DriverVO {

    private Long id;

    @NotNull(groups = Insert.class)
    private String name;

    @NotNull(groups = Insert.class)
    private String folderName;

    public interface Insert{}

}
