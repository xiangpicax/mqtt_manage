package com.dlg.mm.datareciver.callback;

import com.dlg.mm.entity.DmmMqttServerData;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;

/**
 * Description:常规MQTT回调函数
 *
 * @author: 格兰德法则·祝
 * Date:2021/12/16 15:02
 **/
@Slf4j
public abstract class MyMqttClientCallback implements MqttCallback {

    /**
     * 系统的mqtt客户端id
     */
    private DmmMqttServerData mqttServerData;

    public void setMqttClient(DmmMqttServerData mqttServerData){
        this.mqttServerData = mqttServerData;
    };

    public DmmMqttServerData getMqttClient(){
        return this.mqttServerData;
    };

    public MyMqttClientCallback(DmmMqttServerData mqttServerData) {
        this.mqttServerData = mqttServerData;
    }

    public MyMqttClientCallback() {
        this.mqttServerData = new DmmMqttServerData();
    }

    /**
     * MQTT 断开连接会执行此方法
     */
    @Override
    public void connectionLost(Throwable throwable) {
        log.info("断开了MQTT连接 ：{}", throwable.getMessage());
        //log.error(throwable.getMessage(), throwable);
    }

    /**
     * publish发布成功后会执行到这里
     */
    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
        log.info("发布消息成功");
    }


}

