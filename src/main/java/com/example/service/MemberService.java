package com.example.service;

import java.text.SimpleDateFormat;
import java.util.Date;

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
     * po 고유키를 서버에서 채번 후 insert
     * 규칙: pp + 년도 + - + 3자리순번  예) pp2026-001
     */
    public int insertMember(MemberVO memberVO) {

        String po = createPo();
        memberVO.setPo(po);

        return memberMapper.insertMember(memberVO);
    }

    /** 회원 수정 */
    public int updateMember(MemberVO memberVO) {
        return memberMapper.updateMember(memberVO);
    }

    /**
     * po 채번
     * 1) 올해 최대 po 조회
     * 2) 없으면 pp2026-001
     * 3) 있으면 뒤 숫자 +1
     */
    private String createPo() {

        String year = new SimpleDateFormat("yyyy").format(new Date());
        String prefix = "pp" + year + "-";   // pp2026-

        String maxPo = memberMapper.selectMaxPo(prefix);

        int nextNo = 1;
        if (maxPo != null && maxPo.length() > 0) {
            // pp2026-003 → 003 → 3 → 4
            String noStr = maxPo.substring(maxPo.lastIndexOf("-") + 1);
            nextNo = Integer.parseInt(noStr) + 1;
        }

        // 3자리: 001, 002, ...
        String seq = String.format("%03d", nextNo);

        return prefix + seq;
    }
}
