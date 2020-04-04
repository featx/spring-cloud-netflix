package org.featx.templet.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Excepts
 * @since 2020/1/8 1:18
 */
@Data
public class ServiceCategoryTypeMessage {

    private Long id;

    private Integer status;

    private String name;

    private LocalDateTime createdAt;

}
