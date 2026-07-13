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

    /** 회원 등록 (VO) */
    int insertMember(MemberVO memberVO);

    /** 회원 수정 (VO) */
    int updateMember(MemberVO memberVO);
}
