package org.featx.templet.model;

import lombok.Data;
import org.featx.spec.model.Unified;

/**
 * The service category type in returned list/set/collection
 *
 * @author Excepts
 * @since 2019/12/31 10:10
 */
@Data
public class ServiceCategoryTypeItem implements Unified {

    private static final long serialVersionUID = 8594108578537949017L;

    private String code;

    private String name;

    private Integer type;
}
