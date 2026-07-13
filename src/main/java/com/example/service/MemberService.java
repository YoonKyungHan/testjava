package com.example.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mapper.MemberMapper;
import com.example.vo.MemberVO;

@Service
public class MemberService {

    @Autowired
    private MemberMapper memberMapper;

    /**
     * 회원 등록
     * VO 그대로 Mapper 전달
     * po 규칙: pp + 년도 + - + 3자리  예) pp2026-001
     */
    public int insertMember(MemberVO memberVO) {

        String po = createPo();
        memberVO.setPo(po);

        return memberMapper.insertMember(memberVO);
    }

    /** 회원 수정 (VO) */
    public int updateMember(MemberVO memberVO) {
        return memberMapper.updateMember(memberVO);
    }

    /**
     * po 채번
     * 단순 파라미터라 HashMap 사용
     */
    private String createPo() {

        String year = new SimpleDateFormat("yyyy").format(new Date());
        String prefix = "pp" + year + "-";

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("prefix", prefix);

        String maxPo = memberMapper.selectMaxPo(param);

        int nextNo = 1;
        if (maxPo != null && maxPo.length() > 0) {
            String noStr = maxPo.substring(maxPo.lastIndexOf("-") + 1);
            nextNo = Integer.parseInt(noStr) + 1;
        }

        return prefix + String.format("%03d", nextNo);
    }
}
