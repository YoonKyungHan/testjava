package com.example.mapper;

import java.util.Map;

import com.example.vo.MemberVO;

/**
 * MyBatis Mapper
 * - VO 쓸 때: MemberVO
 * - 단순 파라미터일 때: HashMap
 */
public interface MemberMapper {

    /** 해당 년도 최대 po 조회 (HashMap, key: prefix) */
    String selectMaxPo(Map<String, Object> param);

    /** 회원 단건 조회 (이력 before용) */
    MemberVO selectMember(String po);

    /** 회원 등록 (VO) */
    int insertMember(MemberVO memberVO);

    /** 회원 수정 (VO) */
    int updateMember(MemberVO memberVO);
}
