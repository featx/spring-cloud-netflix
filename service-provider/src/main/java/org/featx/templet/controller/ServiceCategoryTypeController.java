package org.featx.templet.controller;

import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.templet.definition.ServiceCategoryTypeEnd;
import org.featx.templet.facade.ServiceCategoryTypeFacade;
import org.featx.templet.model.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Excepts
 * @since 2019/12/31 14:14
 */

@RestController
public class ServiceCategoryTypeController implements ServiceCategoryTypeEnd {

    @Resource
    private ServiceCategoryTypeFacade serviceCategoryTypeFacade;

    @Override
    public BaseResponse<Void> create(@RequestBody ServiceCategoryTypeCreateRequest serviceCategoryTypeCreateRequest) {
        serviceCategoryTypeFacade.create(serviceCategoryTypeCreateRequest);
        return BaseResponse.succeeded();
    }

    @Override
    public BaseResponse<Void> update(@RequestBody ServiceCategoryTypeModifyRequest ServiceCategoryTypeModifyRequest) {
        serviceCategoryTypeFacade.update(ServiceCategoryTypeModifyRequest);
        return BaseResponse.succeeded();
    }

    @Override
    public BaseResponse<Void> drop(String code) {
        serviceCategoryTypeFacade.delete(code);
        return BaseResponse.succeeded();
    }

    @Override
    public BaseResponse<Void> delete(@RequestParam("code") String code) {
        serviceCategoryTypeFacade.delete(code);
        return BaseResponse.succeeded();
    }

    @Override
    public BaseResponse<ServiceCategoryTypeInfo> retrieve(String code) {
        return BaseResponse.succeeded(serviceCategoryTypeFacade.retrieve(code));
    }

    @Override
    public BaseResponse<ServiceCategoryTypeInfo> get(String code) {
        return BaseResponse.succeeded(serviceCategoryTypeFacade.retrieve(code));
    }

    @Override
    public PageResponse<ServiceCategoryTypeItem> retrieveByPage(ServiceCategoryTypePageRequest serviceCategoryTypePageRequest) {
        return PageResponse.succeeded(serviceCategoryTypeFacade.retrieveByPage(serviceCategoryTypePageRequest))
                .page(serviceCategoryTypePageRequest.getPage());
//                .total(Long.valueOf(serviceCategoryTypeFacade.countByPage(serviceCategoryTypePageRequest)).intValue());
    }
}
