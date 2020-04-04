package org.featx.templet.remote.endpoint.fallback;

//import org.featx.common.constant.ApiCode;
//import org.featx.common.http.ResponseBase;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.featx.spec.enums.BusinessError;
import org.featx.spec.model.BaseResponse;
import org.featx.templet.remote.endpoint.AccountEndpoint;
import org.featx.templet.remote.model.AccountInfo;
import org.springframework.stereotype.Component;

/**
 * @author Excepts
 * @since 2019/11/23 12:15
 */
@Component
@Slf4j
public class AccountEndpointFallbackFactory implements FallbackFactory<AccountEndpoint> {

    @Override
    public AccountEndpoint create(Throwable cause) {
        return new AccountEndpoint() {
            @Override
            public BaseResponse<AccountInfo> retrieve(String mobile) {
                log.error("retrieve Error ", cause);
                return BaseResponse.occur(BusinessError.SERVER_REMOTE_PROCEDURE_CALL);
            }
        };

    }
}
