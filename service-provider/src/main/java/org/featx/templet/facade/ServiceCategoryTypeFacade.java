package org.featx.templet.facade;

import org.featx.templet.model.*;

import java.util.List;

/**
 * @author Excepts
 * @since 2019/11/23 12:03
 */
public interface ServiceCategoryTypeFacade {

    void create(ServiceCategoryTypeCreateRequest serviceCategoryTypeCreateRequest);

    void update(ServiceCategoryTypeModifyRequest serviceCategoryTypeModifyRequest);

    void delete(String code);

    ServiceCategoryTypeInfo retrieve(String code);

    List<ServiceCategoryTypeItem> retrieveByPage(ServiceCategoryTypePageRequest componentCategoryTypePageRequest);

    long countByPage(ServiceCategoryTypePageRequest pageRequest);
}
