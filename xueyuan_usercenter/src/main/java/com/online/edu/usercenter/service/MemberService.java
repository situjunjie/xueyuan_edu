package com.online.edu.usercenter.service;

import com.online.edu.usercenter.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author situjunjie
 * @since 2021-05-15
 */
public interface MemberService extends IService<Member> {

    Integer getRegisterMemberCount(String date);
}
