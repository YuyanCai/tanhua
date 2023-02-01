package com.study.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.study.entiy.UserInfo;
import org.apache.ibatis.annotations.Select;

public interface UserInfoMapper extends BaseMapper<UserInfo> {
    @Select("select * from tb_user_info where id in (\n" +
            "  SELECT black_user_id FROM tb_black_list where user_id=#{userId}\n" +
            ")")
    IPage<UserInfo> findBlackList(Page pages, Long userId);
}
