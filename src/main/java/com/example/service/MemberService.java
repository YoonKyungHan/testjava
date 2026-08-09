package com.example.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.MemberMapper;
import com.example.vo.HistSaveRequest;
import com.example.vo.MemberVO;

@Service
public class MemberService {

    private static final String ENTITY_TYPE = "MEMBER";
    private static final String TABLE_NM = "MEMBER";

    @Autowired
    private MemberMapper memberMapper;

    @Autowired
    private HistoryService historyService;

    /**
     * 회원 등록
     * VO 그대로 Mapper 전달
     * po 규칙: pp + 년도 + - + 3자리  예) pp2026-001
     */
    @Transactional
    public int insertMember(MemberVO memberVO) {

        String po = createPo();
        memberVO.setPo(po);

        int result = memberMapper.insertMember(memberVO);

        historyService.save(
                HistSaveRequest.ofCreate(ENTITY_TYPE, po, memberVO)
                        .tableNm(TABLE_NM)
        );

        return result;
    }

    /** 회원 수정 (VO) + before/after 이력 */
    @Transactional
    public int updateMember(MemberVO memberVO) {

        MemberVO before = memberMapper.selectMember(memberVO.getPo());

        int result = memberMapper.updateMember(memberVO);

        historyService.save(
                HistSaveRequest.ofUpdate(ENTITY_TYPE, memberVO.getPo(), before, memberVO)
                        .tableNm(TABLE_NM)
        );

        return result;
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
