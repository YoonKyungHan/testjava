package com.example.mapper;

import com.example.vo.MemberVO;

/**
 * MyBatis Mapper 인터페이스
 * - 메서드명 = XML id
 * - 패키지+클래스명 = XML namespace
 */
public interface MemberMapper {

    /** 해당 년도 최대 po 조회 (예: pp2026-) */
    String selectMaxPo(String prefix);

    /** 회원 등록 */
    int insertMember(MemberVO memberVO);

    /** 회원 수정 */
    int updateMember(MemberVO memberVO);
}
