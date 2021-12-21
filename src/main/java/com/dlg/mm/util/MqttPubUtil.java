package com.dlg.mm.util;


import com.dlg.mm.config.MqttAutoAssemblyConfig;
import com.dlg.mm.datareciver.client.MqttClientConnect;
import com.dlg.mm.entity.DmmMqttServerData;
import com.dlg.mm.mapper.DmmMqttServerDataMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Description:  mqtt消息发布工具
 * author: 格兰德法则·祝
 * Date:2021/12/17 14:23
 **/
@Component
@Slf4j
@AllArgsConstructor
public class MqttPubUtil {
    private final DmmMqttServerDataMapper dmmMqttServerDataMapper;
    private final MqttAutoAssemblyConfig mqttAutoAssemblyConfig;

    /**
     *  发布消息
     * @param pubCode 发布标识
     * @param topic 主题
     * @param message 消息内容
     * @param qos 消息质量    Qos：0、1、2   默认1
     * @throws Exception
     */
    public  void pubMessage(String pubCode, String topic, String message,int qos) throws Exception {
        List<DmmMqttServerData> dmmMqttServerDataList = dmmMqttServerDataMapper
                .getAllDmmMqttServerDataByApplicationNameAndCode(mqttAutoAssemblyConfig.getApplicationName(), pubCode);
        if(!CollectionUtils.isEmpty(dmmMqttServerDataList)){
            DmmMqttServerData dmmMqttServerData = dmmMqttServerDataList.get(0);
            MqttClientConnect.mqttClients.get(dmmMqttServerData.getId()).pub(topic,message,qos);
        }else {
            throw new Exception("No matching service was found");
        }
    }

    public  void pubMessage(String pubCode, String topic, String message) throws Exception {
        this.pubMessage(pubCode, topic, message,1);
    }


}
