package org.featx.templet.consume;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.featx.templet.facade.OtherFacade;
import org.featx.templet.model.ServiceCategoryTypeMessage;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Excepts
 * @since 2020/1/8 1:16
 */

@Slf4j
@Component
@RocketMQMessageListener(consumerGroup = "component", topic = "category")
public class GroupTopicListener implements RocketMQListener<ServiceCategoryTypeMessage> {

    @Resource
    private OtherFacade otherFacade;

    @Override
    public void onMessage(ServiceCategoryTypeMessage message) {
        otherFacade.invoke(message);
    }
}
