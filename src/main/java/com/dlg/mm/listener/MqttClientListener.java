package com.dlg.mm.listener;


import com.dlg.mm.datareciver.callback.MyMqttClientCallback;
import com.dlg.mm.datareciver.client.MqttClientConnect;
import com.dlg.mm.entity.DmmMqttServerData;
import com.dlg.mm.mapper.DmmMqttServerDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

/**
 * Description:项目启动 监听主题
 *
 * @author: 格兰德法则·祝
 * Date:2021/12/16 15:03
 **/

@Slf4j
@Component
public class MqttClientListener {
    @Resource
    private DmmMqttServerDataMapper dmmMqttServerDataMapper;


    /**
     * 连接MQTT
     *
     * @param applicationName 项目名称
     * @param mqttCallback    回调类
     */
    public void linkMqtt(String applicationName, MyMqttClientCallback mqttCallback) {
        try {
            List<DmmMqttServerData> dmmMqttServerData = dmmMqttServerDataMapper.getAllDmmMqttServerDataByApplicationName(applicationName);
            dmmMqttServerData.forEach(mqtt -> {
                try {
                    mqttCallback.setMqttClient(mqtt);
                    MqttClientConnect mqttClientConnect = new MqttClientConnect();
                    mqttClientConnect.setMqttClient(mqtt);
                    String host = "tcp://" + mqtt.getServerHost() + ":" + mqtt.getServerPort();
                    mqttClientConnect.setMqttClient(host,
                            mqtt.getClientId() + new Random().nextInt(999),
                            mqtt.getUsername(),
                            mqtt.getPassword(),
                            !"0".equals(mqtt.getClientCleanSession()),
                            mqttCallback);
                    mqttClientConnect.sub(StringUtils.commaDelimitedListToStringArray(mqtt.getClientTopic()));
                    MqttClientConnect.mqttClients.put(mqtt.getId(), mqttClientConnect);
                    log.info("{}--连接成功！！订阅主题--{}", mqtt.getId(), mqtt.getClientTopic());
                } catch (MqttException e) {
                    log.info("连接出错    : {}", e.getMessage());
                }
            });
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }


    //    自动注入指定callBack
//    @Bean
//    public void onMessage() {
//        try {
//            List<SdgMqttServerData> sdgMqttServerData = sdgMqttServerDataService.list();
//            sdgMqttServerData.forEach(mqtt -> {
//                try {
//                    MqttClientConnect mqttClientConnect = new MqttClientConnect();
//                    mqttClientConnect.setMqttClientId(mqtt.getId());
//                    String host = "tcp://" + mqtt.getServerHost() + ":" + mqtt.getServerPort();
//                    mqttClientConnect.setMqttClient(host,
//                            mqtt.getClientId() + new Random().nextInt(999),
//                            mqtt.getUsername(),
//                            mqtt.getPassword(),
//                            mqtt.getClientCleanSession(),
//                            new MqttClientCallback(mqtt.getId()));
//                    mqttClientConnect.sub(StringUtils.commaDelimitedListToStringArray(mqtt.getClientTopic()));
//                    MqttClientConnect.mqttClients.put(mqtt.getId(), mqttClientConnect);
//                    log.info("{}--连接成功！！订阅主题--{}", mqtt.getId(), mqtt.getClientTopic());
//                } catch (MqttException e) {
//                    log.info("连接出错    : {}", e.getMessage());
//                }
//            });
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
//    }

}
