package org.featx.templet.facade.impl;

import lombok.extern.slf4j.Slf4j;
import org.featx.templet.facade.OtherFacade;
import org.featx.templet.model.ServiceCategoryTypeMessage;
import org.springframework.stereotype.Service;

/**
 * @author Excepts
 * @since 2020/1/8 1:22
 */
@Slf4j
@Service
public class OtherFacadeImpl implements OtherFacade {
    @Override
    public void invoke(ServiceCategoryTypeMessage message) {
        log.info("Service processed message {}", message);
    }
}
