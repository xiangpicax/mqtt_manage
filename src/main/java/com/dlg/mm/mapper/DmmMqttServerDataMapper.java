package com.dlg.mm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dlg.mm.entity.DmmMqttServerData;
import com.dlg.mm.entity.SdgMqttServerData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 格兰德法则·祝
 * @since 2021-12-20
 */
@Mapper
public interface DmmMqttServerDataMapper extends BaseMapper<DmmMqttServerData> {
    /**
     *  跨库查询所有Mqtt连接
     * @param name applicationName
     * @return 所有需要的连接
     */
    List<DmmMqttServerData> getAllDmmMqttServerDataByApplicationName(@Param("name") String name);

    /**
     *  跨库查询所有Mqtt连接
     * @param name applicationName
     * @param code 业务CODE
     * @return 所有需要的连接
     */
    List<DmmMqttServerData> getAllDmmMqttServerDataByApplicationNameAndCode(@Param("name") String name,@Param("code") String code);
}
