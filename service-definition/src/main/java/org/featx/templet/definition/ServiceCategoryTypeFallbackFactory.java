package org.featx.templet.definition;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.featx.spec.enums.BusinessError;
import org.featx.spec.model.BaseResponse;
import org.featx.spec.model.PageResponse;
import org.featx.templet.model.*;

/**
 * @author Excepts
 * @since 2019/12/31 10:01
 */
@Slf4j
public class ServiceCategoryTypeFallbackFactory implements FallbackFactory<ServiceCategoryTypeEnd> {

    @Override
    public ServiceCategoryTypeEnd create(Throwable cause) {

        return new ServiceCategoryTypeEnd() {

            @Override
            public BaseResponse<Void> create(
                    ServiceCategoryTypeCreateRequest serviceCategoryTypeCreateRequest) {
                log.error("Service category type create error, {}", serviceCategoryTypeCreateRequest, cause);
                return BaseResponse.occur(BusinessError.SERVER_REMOTE_PROCEDURE_CALL);
            }

            @Override
            public BaseResponse<Void> update(
                    ServiceCategoryTypeModifyRequest serviceCategoryTypeCreateRequest) {
                log.error("Service category type update error, {}", serviceCategoryTypeCreateRequest, cause);
                return BaseResponse.occur(BusinessError.SERVER_REMOTE_PROCEDURE_CALL);
            }

            @Override
            public BaseResponse<Void> drop(String code) {
                log.error("Service category type drop error, {}", code, cause);
                return BaseResponse.occur(BusinessError.SERVER_REMOTE_PROCEDURE_CALL);
            }

            @Override
            public BaseResponse<Void> delete(String code) {
                log.error("Service category type delete error, {}", code, cause);
                return BaseResponse.occur(BusinessError.SERVER_REMOTE_PROCEDURE_CALL);
            }

            @Override
            public BaseResponse<ServiceCategoryTypeInfo> retrieve(String code) {
                log.error("Service category type retrieve error, {}", code, cause);
                return BaseResponse.occur(BusinessError.SERVER_REMOTE_PROCEDURE_CALL);
            }

            @Override
            public BaseResponse<ServiceCategoryTypeInfo> get(String code) {
                log.error("Service category type get error, {}", code, cause);
                return BaseResponse.occur(BusinessError.SERVER_REMOTE_PROCEDURE_CALL);
            }

            @Override
            public PageResponse<ServiceCategoryTypeItem> retrieveByPage(
                    ServiceCategoryTypePageRequest serviceCategoryTypePageRequest) {
                log.error("Service category type retrieveByPage error, {}", serviceCategoryTypePageRequest, cause);
                return PageResponse.occur(BusinessError.SERVER_REMOTE_PROCEDURE_CALL, null);
            }
        };
    }
}
