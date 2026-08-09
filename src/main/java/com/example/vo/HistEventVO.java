package com.example.vo;

import java.util.Date;

/**
 * 범용 변경이력 VO
 * - HIST_EVENT + (조회 시) HIST_SNAPSHOT before/after
 */
public class HistEventVO {

    private Long hist_id;
    private String entity_type;
    private String table_nm;      // 실제 변경 테이블(선택)
    private String entity_id;
    private String action_type;   // CREATE / UPDATE / DELETE
    private String actor_id;
    private String actor_nm;
    private String reason;
    private String req_ip;
    private String trace_id;
    private Date changed_at;

    /** 스냅샷 JSON 문자열 */
    private String before_data;
    private String after_data;

    public Long getHist_id() {
        return hist_id;
    }

    public void setHist_id(Long hist_id) {
        this.hist_id = hist_id;
    }

    public String getEntity_type() {
        return entity_type;
    }

    public void setEntity_type(String entity_type) {
        this.entity_type = entity_type;
    }

    public String getTable_nm() {
        return table_nm;
    }

    public void setTable_nm(String table_nm) {
        this.table_nm = table_nm;
    }

    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public String getAction_type() {
        return action_type;
    }

    public void setAction_type(String action_type) {
        this.action_type = action_type;
    }

    public String getActor_id() {
        return actor_id;
    }

    public void setActor_id(String actor_id) {
        this.actor_id = actor_id;
    }

    public String getActor_nm() {
        return actor_nm;
    }

    public void setActor_nm(String actor_nm) {
        this.actor_nm = actor_nm;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReq_ip() {
        return req_ip;
    }

    public void setReq_ip(String req_ip) {
        this.req_ip = req_ip;
    }

    public String getTrace_id() {
        return trace_id;
    }

    public void setTrace_id(String trace_id) {
        this.trace_id = trace_id;
    }

    public Date getChanged_at() {
        return changed_at;
    }

    public void setChanged_at(Date changed_at) {
        this.changed_at = changed_at;
    }

    public String getBefore_data() {
        return before_data;
    }

    public void setBefore_data(String before_data) {
        this.before_data = before_data;
    }

    public String getAfter_data() {
        return after_data;
    }

    public void setAfter_data(String after_data) {
        this.after_data = after_data;
    }
}
