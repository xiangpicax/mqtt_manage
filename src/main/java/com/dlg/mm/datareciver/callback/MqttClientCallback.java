package com.dlg.mm.datareciver.callback;


import com.dlg.mm.entity.DmmMqttServerData;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;


/**
 * Description:常规MQTT回调函数
 * @author: 格兰德法则·祝
 * Date:2021/12/16 15:02
 **/
@Slf4j
public class MqttClientCallback implements MqttCallback {

    /**
     * 系统的mqtt客户端id
     */
    private DmmMqttServerData mqttServerData;

    public MqttClientCallback(DmmMqttServerData mqttServerData) {
        this.mqttServerData = mqttServerData;
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

    /**
     * subscribe订阅后得到的消息会执行到这里
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        //  TODO    此处可以将订阅得到的消息进行业务处理、数据存储
        log.info("收到来自【 "+ mqttServerData.getId() + "】   topic:【   " + topic + "】 的消息：{}", new String(message.getPayload()));

    }
}

