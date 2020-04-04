# create database spring_cloud_netflix default character set utf8mb4 collate utf8mb4_unicode_ci;
#
# use spring_cloud_netflix;

create table if not exists t_service_category_type
(
    `id`         bigint(20)  not null auto_increment comment 'default id',
    `code`       varchar(64) not null default '' comment 'business code',
    `name`       varchar(64) not null default '' comment 'entity name',
    `type`       int         not null default 0 comment '',
    `deleted`    tinyint     not null default 0 comment 'soft deleted mark',
    `created_at` datetime    not null default current_timestamp comment '',
    `updated_at` datetime    not null default current_timestamp on update current_timestamp comment '',
    primary key (`id`)
) comment 'domain service category type';

create unique index unq_service_category_type_code on t_service_category_type (code);
create index idx_service_category_type_name on t_service_category_type (name(20));