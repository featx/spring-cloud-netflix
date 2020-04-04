package org.featx.templet.model;

import lombok.Data;
import org.featx.spec.model.Unified;

/**
 * @author Excepts
 * @since 2019/12/31 10:10
 */
@Data
public class ServiceCategoryTypeModifyRequest implements Unified {

    private String code;

    private String name;

    private Integer type;
}
