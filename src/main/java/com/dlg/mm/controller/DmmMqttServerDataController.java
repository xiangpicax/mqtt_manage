package com.dlg.mm.controller;


import com.dlg.mm.datareciver.client.MqttClientConnect;
import com.dlg.mm.listener.MqttClientListener;
import com.dlg.mm.util.MqttPubUtil;
import com.zyy.common.util.ApiResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 格兰德法则·祝
 * @since 2021-12-20
 */
@Slf4j
@RestController
@RequestMapping("/mqtt")
@AllArgsConstructor
public class DmmMqttServerDataController {
    private final MqttClientListener mqttClientListener;
    private final MqttPubUtil mqttPubUtil;


    @GetMapping("reloadMqtt")
    public ApiResult<String> reloadMqtt() {
        ConcurrentHashMap<String, MqttClientConnect> mqttClients = MqttClientConnect.mqttClients;
        try {
            for (String key:mqttClients.keySet()){
                MqttClientConnect mqttClientConnect = mqttClients.get(key);
                mqttClientConnect.close();
            }
//            mqttClientListener.onMessage();
        } catch (Exception e) {
            log.info("断开连接出现问题：    {}",e.getMessage());
            return ApiResult.fail();
        }
        return ApiResult.success();
    }


    @GetMapping("pub")
    public ApiResult<String> pub() {
        try {
            mqttPubUtil.pubMessage("TT_002","kkk","test");
        } catch (Exception e) {
            log.info("发布消息出现问题：    {}",e.getMessage());
            return ApiResult.fail();
        }
        return ApiResult.success();
    }

}
