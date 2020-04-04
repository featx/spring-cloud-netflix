package org.featx.templet.facade.model;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

/**
 * @author Excepts
 * @since 2020/4/4 15:56
 */

public class ServiceCategoryTypePageRequestTest {
    @Test
    public void testEquals() {
        ServiceCategoryTypePageRequest serviceCategoryTypePageRequest = new ServiceCategoryTypePageRequest();
        serviceCategoryTypePageRequest.setPage(10);
        serviceCategoryTypePageRequest.setSize(20);

        ServiceCategoryTypePageRequest serviceCategoryTypePageReq = new ServiceCategoryTypePageRequest();
        serviceCategoryTypePageReq.setPage(20);
        serviceCategoryTypePageReq.setSize(50);

        Assertions.assertNotEquals(serviceCategoryTypePageRequest, serviceCategoryTypePageReq);
    }
}
