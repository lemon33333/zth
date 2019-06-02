package com.ddc.server.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.ddc.server.entity.DDCAdmin;
import com.ddc.server.entity.DDCInformation;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Mapper 接口
 *
 * @author dingpengfei
 * @since 2019-05-09
 */
public interface DDCInformationMapper extends BaseMapper<DDCInformation> {
    @Select("select * from ddc_information")
    List<DDCInformation> selectAll();

    @Select("select * from ddc_information where simpleTitle=#{search}")
    List<DDCInformation> selectByTitle(@Param("search") String search);


}
