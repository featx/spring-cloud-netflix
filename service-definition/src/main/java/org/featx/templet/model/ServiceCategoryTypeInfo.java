package org.featx.templet.model;

import lombok.Data;
import org.featx.spec.model.Unified;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Service category type usual returned DTO (data transfer object)
 * <p>
 * Why not extends AbstractUnified ?
 * Some of the property you would not want to expose, such id, password, etc.
 *
 * @author Excepts
 * @since 2019/12/31 10:10
 */
@Data
public class ServiceCategoryTypeInfo implements Serializable, Unified {

    private static final long serialVersionUID = -6253757001905454407L;

    private String code;

    private String name;

    private Integer type;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
