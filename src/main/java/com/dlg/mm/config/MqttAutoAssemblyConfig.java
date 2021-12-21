package com.dlg.mm.config;


import com.dlg.mm.datareciver.callback.MyMqttClientCallback;
import com.dlg.mm.listener.MqttClientListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Description:自动装配
 * <p>
 * author: 格兰德法则·祝
 * Date:2021/12/16 17:38
 **/
@Component
@Slf4j

public class MqttAutoAssemblyConfig {
    private final MqttClientListener mqttClientListener;
    private final ApplicationContext applicationContext;

    public MqttAutoAssemblyConfig(MqttClientListener mqttClientListener,ApplicationContext applicationContext) {
        this.mqttClientListener = mqttClientListener;
        this.applicationContext = applicationContext;
        log.info("初始化Mqtt配置");
        Map<String, MyMqttClientCallback> beansOfType = applicationContext.getBeansOfType(MyMqttClientCallback.class);
        String applicationName = getApplicationName();
        log.info("applicationName:    {}", applicationName);
        for (String key : beansOfType.keySet()) {
            mqttClientListener.linkMqtt(applicationName, beansOfType.get(key));
        }
    }

    public String getApplicationName() {
        return applicationContext.getEnvironment().getProperty("spring.application.name");
    }
}
