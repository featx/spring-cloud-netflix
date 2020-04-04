package org.featx.templet.remote.endpoint;

import org.featx.spec.model.BaseResponse;
import org.featx.templet.remote.endpoint.fallback.AccountEndpointFallbackFactory;
import org.featx.templet.remote.model.AccountInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Excepts
 * @since 2019/11/23 12:15
 */
@FeignClient(value = "account-rest-service", fallbackFactory = AccountEndpointFallbackFactory.class)
public interface AccountEndpoint {

    @GetMapping(value = "/user/")
    BaseResponse<AccountInfo> retrieve(@RequestParam("code") String mobile);

}

