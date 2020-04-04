package org.featx.templet.persistence.service;

import org.featx.templet.model.*;

import java.util.List;

/**
 * @author Excepts
 * @since 2019/12/31 17:13
 */
public interface ServiceCategoryTypeService {

    ServiceCategoryTypeMessage create(ServiceCategoryTypeCreateRequest ServiceCategoryTypeCreateRequest);

    void update(ServiceCategoryTypeModifyRequest ServiceCategoryTypeModifyRequest);

    void delete(String code);

    ServiceCategoryTypeInfo getByCode(String code);

    List<ServiceCategoryTypeItem> listByPage(ServiceCategoryTypePageRequest pageRequest);

    long countByPage(ServiceCategoryTypePageRequest pageRequest);
}
