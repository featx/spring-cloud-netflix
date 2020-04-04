package org.featx.templet.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.featx.spec.entity.AbstractUnified;


/**
 * The DTO for service category type creation request
 * <p>
 * Why not extends AbstractUnified ?
 * Some of the property you would not want to expose, such id, password, etc.
 *
 * @author Excepts
 * @since 2019/12/31 10:09
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ServiceCategoryTypeCreateRequest extends AbstractUnified<Long> {

    private static final long serialVersionUID = 1405477456078636217L;

}
