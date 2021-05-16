package com.online.edu.usercenter.service.impl;

import com.online.edu.usercenter.entity.Member;
import com.online.edu.usercenter.mapper.MemberMapper;
import com.online.edu.usercenter.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author situjunjie
 * @since 2021-05-15
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Override
    public Integer getRegisterMemberCount(String date) {

        Integer count = baseMapper.registerMemberCount(date);

        return count;
    }
}
