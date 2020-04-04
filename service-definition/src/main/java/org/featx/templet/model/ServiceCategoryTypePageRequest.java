package org.featx.templet.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.featx.spec.model.PageRequest;

/**
 * @author Excepts
 * @since 2019/12/31 10:11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceCategoryTypePageRequest extends PageRequest {
    private static final long serialVersionUID = 191744519878496318L;
}
