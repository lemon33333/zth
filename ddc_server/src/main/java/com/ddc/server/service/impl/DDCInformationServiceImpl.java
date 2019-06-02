package com.ddc.server.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.ddc.server.entity.DDCAdmin;
import com.ddc.server.entity.DDCInformation;
import com.ddc.server.mapper.DDCAdminMapper;
import com.ddc.server.mapper.DDCInformationMapper;
import com.ddc.server.service.IDDCAdminService;
import com.ddc.server.service.IDDCInformationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dingpengfei
 * @since 2019-05-09
 */
@Service
public class DDCInformationServiceImpl extends ServiceImpl<DDCInformationMapper, DDCInformation> implements IDDCInformationService {
@Resource private DDCInformationMapper informationMapper;
    @Override
    public List<DDCInformation> selectByKeyword(String search){
        return informationMapper.selectByTitle(search);
    }

    @Override
    public List<DDCInformation> selectMemberList(){
        List<DDCInformation> informationList=informationMapper.selectAll();
        return informationList;
    }






}
