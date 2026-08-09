package com.example.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.mapper.HistMapper;
import com.example.vo.HistEventVO;
import com.example.vo.HistSaveRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 범용 변경이력 서비스
 *
 * 사용 예:
 *   historyService.save(
 *       HistSaveRequest.ofUpdate("MEMBER", po, before, after)
 *           .tableNm("MEMBER")
 *           .actor(actorId, actorNm)
 *   );
 */
@Service
public class HistoryService {

    @Autowired
    private HistMapper histMapper;

    private final ObjectMapper objectMapper = new ObjectMapper();

    /** 이력 저장 */
    @Transactional
    public Long save(HistSaveRequest req) {

        HistEventVO vo = new HistEventVO();
        vo.setEntity_type(req.getEntity_type());
        vo.setTable_nm(req.getTable_nm());
        vo.setEntity_id(req.getEntity_id());
        vo.setAction_type(req.getAction_type());
        vo.setActor_id(req.getActor_id());
        vo.setActor_nm(req.getActor_nm());
        vo.setReason(req.getReason());
        vo.setReq_ip(req.getReq_ip());
        vo.setTrace_id(req.getTrace_id());
        vo.setBefore_data(toJson(req.getBefore_data()));
        vo.setAfter_data(toJson(req.getAfter_data()));

        histMapper.insertHistEvent(vo);
        histMapper.insertHistSnapshot(vo);

        return vo.getHist_id();
    }

    /** 대상 이력 목록 */
    public List<HistEventVO> listByEntity(String entityType, String entityId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("entity_type", entityType);
        param.put("entity_id", entityId);
        return histMapper.selectHistList(param);
    }

    /** 이력 상세(before/after 포함) */
    public HistEventVO getDetail(Long histId) {
        return histMapper.selectHistDetail(histId);
    }

    private String toJson(Object data) {
        if (data == null) {
            return null;
        }
        if (data instanceof String) {
            return (String) data;
        }
        try {
            return objectMapper.writeValueAsString(data);
        } catch (Exception e) {
            throw new IllegalStateException("이력 스냅샷 JSON 변환 실패", e);
        }
    }
}
