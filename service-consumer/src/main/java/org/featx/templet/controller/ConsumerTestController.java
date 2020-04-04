package org.featx.templet.controller;

import org.featx.spec.model.BaseResponse;
import org.featx.templet.model.ServiceCategoryTypeInfo;
import org.featx.templet.remote.endpoint.ServiceCategoryTypeEndpoint;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Excepts
 * @since 2020/1/2 13:57
 */
@RestController
public class ConsumerTestController {

    @Resource
    private ServiceCategoryTypeEndpoint serviceCategoryTypeEndpoint;

    @GetMapping("/consumer/test/retrieve")
    public BaseResponse<ServiceCategoryTypeInfo> test(@RequestParam("code") String code) {
        return serviceCategoryTypeEndpoint.retrieve(code);
    }
}
