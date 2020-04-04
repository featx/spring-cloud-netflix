package org.featx.templet.remote.endpoint;

import org.featx.templet.definition.ServiceCategoryTypeEnd;
import org.featx.templet.definition.ServiceCategoryTypeFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author Excepts
 * @since 2020/1/2 13:53
 */
@FeignClient(name = "component-service", fallbackFactory = ServiceCategoryTypeFallbackFactory.class)
public interface ServiceCategoryTypeEndpoint extends ServiceCategoryTypeEnd {

}
