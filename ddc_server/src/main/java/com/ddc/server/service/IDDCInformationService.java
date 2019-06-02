package com.ddc.server.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.IService;
import com.ddc.server.entity.DDCInformation;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author dingpengfei
 * @since 2019-05-09
 */
public interface IDDCInformationService extends IService<DDCInformation> {


    List<DDCInformation> selectByKeyword(String simpleTitle);
    List<DDCInformation> selectMemberList();


}

