package com.dlg.mm.listener;

import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * Description:
 *
 * @author: 格兰德法则·祝
 * Date:2021/12/17 9:49
 **/
public interface MqttListener {

    default void handler(MqttMessage message){}
}
