package com.dlg.mm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author 格兰德法则·祝
 * @since 2021-12-20
 */
@Data
@Accessors(chain = true)
@TableName("dmm_mqtt_server_data")
public class DmmMqttServerData implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 唯一标识 唯一标识
     */
    private String id;

    /**
     * 服务器ip
     */
    private String serverHost;

    /**
     * 端口
     */
    private String serverPort;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 客户端id,自动添加三位随机数
     */
    private String clientId;

    /**
     * 客户端订阅的主题(以','隔开)
     */
    private String clientTopic;

    /**
     * 客户端每次重连是否清除session， 0:否 1:是
     */
    private String clientCleanSession;

    /**
     * 备注
     */
    private String remark;

    /**
     * 数据来源
     */
    private String dataFrom;

    /**
     * 应用名称 （配置文件中的spring.application.name）
     */
    private String appName;

    /**
     * 发布标识符，需要以此标识获取需要发送的消息的服务
     */
    private String pubCode;

    /**
     * 负责人
     */
    private String owner;

    /**
     * 机构ID
     */
    private String orgId;

    /**
     * 创建人
     */
    private String createdBy;

    /**
     * 创建时间
     */
    private Date createdOn;

    /**
     * 更新人
     */
    private String modifyBy;

    /**
     * 更新时间
     */
    private Date modifyOn;

    /**
     * 是否有效 0有效；1无效
     */
    private String isValid;


}
