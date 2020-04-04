package org.featx.templet.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.messaging.MappingFastJsonMessageConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.google.common.collect.Lists;
import org.apache.rocketmq.spring.support.RocketMQMessageConverter;
import org.featx.spec.constant.Formatter;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.ByteArrayMessageConverter;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.converter.StringMessageConverter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

import static org.featx.spec.constant.Formatter.*;

/**
 * @author Excepts
 * @since 2020/1/2 10:58
 */
@Configuration
public class JsonSerializeConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = BeanUtils.instantiateClass(ObjectMapper.class);
        objectMapper.setDateFormat(new SimpleDateFormat(DATE_TIME_PATTERN));
//        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
//        objectMapper.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CASE);
        objectMapper.setTimeZone(TimeZone.getTimeZone("GMT+8"));

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class,
                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
        javaTimeModule.addSerializer(LocalDate.class,
                new LocalDateSerializer(DateTimeFormatter.ofPattern(DATE_PATTERN)));
        javaTimeModule.addSerializer(LocalTime.class,
                new LocalTimeSerializer(DateTimeFormatter.ofPattern(TIME_PATTERN)));
        javaTimeModule.addDeserializer(LocalDateTime.class,
                new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DATE_TIME_PATTERN)));
        javaTimeModule.addDeserializer(LocalDate.class,
                new LocalDateDeserializer(DateTimeFormatter.ofPattern(DATE_PATTERN)));
        javaTimeModule.addDeserializer(LocalTime.class,
                new LocalTimeDeserializer(DateTimeFormatter.ofPattern(TIME_PATTERN)));
        objectMapper.registerModule(javaTimeModule)
                .registerModule(new ParameterNamesModule());
        return objectMapper;
    }

    @Bean
    public FastJsonConfig fastJsonConfig() {
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setDateFormat(Formatter.DATE_TIME_PATTERN);
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue
        );
        return fastJsonConfig;
    }

    @Bean
    public RocketMQMessageConverter createRocketMQMessageConverter(
            ObjectMapper objectMapper, FastJsonConfig fastJsonConfig) {
        ByteArrayMessageConverter byteArrayMessageConverter = new ByteArrayMessageConverter();
        byteArrayMessageConverter.setContentTypeResolver(null);

        MappingFastJsonMessageConverter fastJsonMessageConverter = new MappingFastJsonMessageConverter();
        fastJsonMessageConverter.setFastJsonConfig(fastJsonConfig);

        MappingJackson2MessageConverter jackson2MessageConverter = new MappingJackson2MessageConverter();
        jackson2MessageConverter.setObjectMapper(objectMapper);

        RocketMQMessageConverter rocketMQMessageConverter = new RocketMQMessageConverter();
        rocketMQMessageConverter.resetMessageConverter(Lists.newArrayList(
                byteArrayMessageConverter,
                new StringMessageConverter(),
                jackson2MessageConverter,
                fastJsonMessageConverter));
        return rocketMQMessageConverter;
    }
}