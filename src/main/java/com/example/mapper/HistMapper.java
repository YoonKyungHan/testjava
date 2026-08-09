package com.example.mapper;

import java.util.List;
import java.util.Map;

import com.example.vo.HistEventVO;

/**
 * 범용 변경이력 Mapper
 */
public interface HistMapper {

    /** HIST_EVENT 등록 (hist_id 채번 후 VO에 set) */
    int insertHistEvent(HistEventVO histEventVO);

    /** HIST_SNAPSHOT 등록 */
    int insertHistSnapshot(HistEventVO histEventVO);

    /** 대상 엔티티 이력 목록 (최신순) */
    List<HistEventVO> selectHistList(Map<String, Object> param);

    /** 이력 단건 + 스냅샷 */
    HistEventVO selectHistDetail(Long histId);
}
