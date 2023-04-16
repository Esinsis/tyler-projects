package com.tyler.listener;

import org.slf4j.MDC;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.GenericApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.ResolvableType;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;

import java.util.Objects;

/**
 * @author Tyler
 * @version 1.0.0
 * @date 4/16/2023 3:19 PM
 * @description TODO
 */
public class LogMDCListener implements GenericApplicationListener {

    private static final String APPLICATION_CONFIG_PROPERTIES = "configurationProperties";
    public static final String APP_NAME = "spring.application.name";

    /**
     * 该方法返回true才会调用下面的方法(onApplicationEvent)
     *
     * @param eventType
     * @return
     */
    @Override
    public boolean supportsEventType(ResolvableType eventType) {
        return ApplicationEnvironmentPreparedEvent.class == eventType.getRawClass();
    }

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        ApplicationEnvironmentPreparedEvent environmentPreparedEvent = (ApplicationEnvironmentPreparedEvent) event;
        ConfigurableEnvironment environment = environmentPreparedEvent.getEnvironment();
        MutablePropertySources propertySources = environment.getPropertySources();
//        propertySources.forEach(propertySource -> {
//            System.out.println("数据源：" + propertySource);
//        });
        PropertySource<?> propertySource = propertySources.get(APPLICATION_CONFIG_PROPERTIES);
        String appName = Objects.isNull(propertySource) ? "log" : (String) propertySource.getProperty(APP_NAME);
        MDC.put("logName", appName);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 19;
    }
}
