package org.featx.templet.remote.assemble.impl;

import lombok.extern.slf4j.Slf4j;
import org.featx.spec.error.ErrorCode;
import org.featx.spec.model.BaseResponse;
import org.featx.templet.remote.assemble.AccountAssemble;
import org.featx.templet.remote.endpoint.AccountEndpoint;
import org.featx.templet.remote.model.AccountInfo;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Excepts
 * @since 2020/4/4 17:28
 */
@Component
@Slf4j
public class AccountAssembleImpl implements AccountAssemble {

    @Resource
    private AccountEndpoint accountEndpoint;

    @Override
    public AccountInfo retrieveOne(String code) {
        return Optional.ofNullable(accountEndpoint.retrieve(code))
                .filter(r -> Objects.equals(r.getCode(), ErrorCode.NO_ERROR))
                .map(BaseResponse::getData)
                .orElseGet(() -> {
                    log.error("");
                    return null;
                });
    }
}
