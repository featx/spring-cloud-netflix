package org.featx.templet.persistence.service.impl;

import com.google.common.collect.Lists;
import org.featx.templet.entity.ServiceCategoryTypeEntity;
import org.featx.templet.model.*;
import org.featx.templet.persistence.mapper.ServiceCategoryTypeMapper;
import org.featx.templet.persistence.service.ServiceCategoryTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Excepts
 * @since 2019/12/31 17:14
 */
@Service
public class ServiceCategoryTypeServiceImpl implements ServiceCategoryTypeService {

    @Resource
    private ServiceCategoryTypeMapper serviceCategoryTypeMapper;

    @Override
    @Transactional
    public ServiceCategoryTypeMessage create(
            ServiceCategoryTypeCreateRequest ServiceCategoryTypeCreateRequest) {
        ServiceCategoryTypeEntity entity = toEntity(ServiceCategoryTypeCreateRequest);
        serviceCategoryTypeMapper.insert(entity);
        ServiceCategoryTypeMessage message = new ServiceCategoryTypeMessage();
        message.setId(entity.getId());
        message.setName(entity.getName());
        //TODO Not do as this
        message.setStatus(entity.getType());
        message.setCreatedAt(entity.getCreatedAt());
        return message;
    }

    private ServiceCategoryTypeEntity toEntity(ServiceCategoryTypeCreateRequest createRequest) {
        ServiceCategoryTypeEntity entity = new ServiceCategoryTypeEntity();
        entity.setCode(createRequest.getCode());
        entity.setName(createRequest.getName());
        entity.setType(createRequest.getType());
        entity.setCreatedAt(LocalDateTime.now());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    @Override
    public void update(ServiceCategoryTypeModifyRequest ServiceCategoryTypeModifyRequest) {
        serviceCategoryTypeMapper.update(toEntity(ServiceCategoryTypeModifyRequest));
    }

    private ServiceCategoryTypeEntity toEntity(ServiceCategoryTypeModifyRequest createRequest) {
        ServiceCategoryTypeEntity entity = new ServiceCategoryTypeEntity();
        entity.setCode(createRequest.getCode());
        entity.setName(createRequest.getName());
        entity.setType(createRequest.getType());
        return entity;
    }

    @Override
    public void delete(String code) {
        serviceCategoryTypeMapper.delete(code);
    }

    @Override
    public ServiceCategoryTypeInfo getByCode(String code) {
        ServiceCategoryTypeEntity entity = serviceCategoryTypeMapper.selectByCode(code);
        return Optional.ofNullable(entity).map(this::toServiceCategoryTypeInfo).orElse(null);
    }

    private ServiceCategoryTypeInfo toServiceCategoryTypeInfo(ServiceCategoryTypeEntity entity) {
        ServiceCategoryTypeInfo info = new ServiceCategoryTypeInfo();
        info.setName(entity.getName());
        info.setCode(entity.getCode());
        info.setType(entity.getType());
        info.setCreatedAt(entity.getCreatedAt());
        info.setUpdatedAt(entity.getUpdatedAt());
        return info;
    }

    @Override
    public List<ServiceCategoryTypeItem> listByPage(ServiceCategoryTypePageRequest pageRequest) {
        return Optional.ofNullable(serviceCategoryTypeMapper.listByPage(pageRequest))
                .map(list -> list.stream().map(this::toItem).collect(Collectors.toList()))
                .orElseGet(Lists::newArrayList);
    }

    private ServiceCategoryTypeItem toItem(ServiceCategoryTypeEntity entity) {
        ServiceCategoryTypeItem item = new ServiceCategoryTypeItem();
        item.setCode(entity.getCode());
        item.setName(entity.getName());
        item.setType(entity.getType());
        return item;
    }

    @Override
    public long countByPage(ServiceCategoryTypePageRequest pageRequest) {
        return serviceCategoryTypeMapper.countByPage(pageRequest);
    }
}
