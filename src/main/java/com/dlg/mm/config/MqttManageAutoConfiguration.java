package com.dlg.mm.config;

import com.dlg.mm.datareciver.client.MqttClientConnect;
import com.dlg.mm.listener.MqttClientListener;
import com.dlg.mm.mapper.DmmMqttServerDataMapper;
import com.dlg.mm.service.DmmMqttServerDataService;
import com.dlg.mm.service.serviceImpl.DmmMqttServerDataServiceImpl;
import com.dlg.mm.util.MqttPubUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author: 格兰德法则·祝
 * Date:2021/12/20 9:54
 **/
@Configuration
@MapperScan(value = "com.dlg.mm.mapper")
@Slf4j
public class MqttManageAutoConfiguration {

    @Resource
    private ApplicationContext applicationContext;
    @Resource
    private DmmMqttServerDataMapper dmmMqttServerDataMapper;


    @Bean
    public MqttClientListener mqttClientListener() {
        return new MqttClientListener();
    }

    @Bean
    public MqttAutoAssemblyConfig mqttAutoAssemblyConfig() {
        log.info("######mqttAutoAssemblyConfig_begin#######");
        return new MqttAutoAssemblyConfig(mqttClientListener(), applicationContext);
    }

    @Bean
    public MqttClientConnect mqttClientConnect() {
        return new MqttClientConnect();
    }

    @Bean
    public DmmMqttServerDataService dmmMqttServerDataServiceImpl() {
        return new DmmMqttServerDataServiceImpl();
    }


    @Bean
    public MqttPubUtil mqttPubUtil() {
        return new MqttPubUtil(dmmMqttServerDataMapper, mqttAutoAssemblyConfig());
    }


}
