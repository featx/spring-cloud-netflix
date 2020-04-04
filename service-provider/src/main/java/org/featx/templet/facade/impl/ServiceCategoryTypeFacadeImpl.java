package org.featx.templet.facade.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.featx.templet.facade.ServiceCategoryTypeFacade;
import org.featx.templet.model.*;
import org.featx.templet.persistence.service.ServiceCategoryTypeService;
import org.featx.templet.remote.assemble.AccountAssemble;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Excepts
 * @since 2019/11/23 12:04
 */
@Service
@Slf4j
public class ServiceCategoryTypeFacadeImpl implements ServiceCategoryTypeFacade {

    @Resource
    private ServiceCategoryTypeService serviceCategoryTypeService;

    @Resource
    private AccountAssemble serviceCategoryTypeAssemble;

    @Resource
    private RocketMQTemplate rocketMQTemplate;

    @Override
    public void create(ServiceCategoryTypeCreateRequest ServiceCategoryTypeCreateRequest) {
        ServiceCategoryTypeMessage message = serviceCategoryTypeService.create(ServiceCategoryTypeCreateRequest);
        rocketMQTemplate.convertAndSend("category", message);
    }

    @Override
    public void update(ServiceCategoryTypeModifyRequest ServiceCategoryTypeModifyRequest) {
        serviceCategoryTypeService.update(ServiceCategoryTypeModifyRequest);
    }

    @Override
    public void delete(String code) {
        serviceCategoryTypeService.delete(code);
    }

    @Override
    public ServiceCategoryTypeInfo retrieve(String code) {
        return serviceCategoryTypeService.getByCode(code);
    }

    @Override
    public List<ServiceCategoryTypeItem> retrieveByPage(ServiceCategoryTypePageRequest ServiceCategoryTypePageRequest) {
        return serviceCategoryTypeService.listByPage(ServiceCategoryTypePageRequest);
    }

    @Override
    public long countByPage(ServiceCategoryTypePageRequest pageRequest) {
        return serviceCategoryTypeService.countByPage(pageRequest);
    }
}
