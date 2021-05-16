package com.online.edu.usercenter.mapper;

import com.online.edu.usercenter.entity.Member;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author situjunjie
 * @since 2021-05-15
 */
public interface MemberMapper extends BaseMapper<Member> {

    public Integer registerMemberCount(String date);

}
