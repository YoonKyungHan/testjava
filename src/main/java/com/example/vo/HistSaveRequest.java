package com.example.vo;

/**
 * 이력 저장 요청
 * 파라미터가 많아질 때는 이렇게 한 객체로 묶는 편이 읽기 좋음
 *
 * 사용 예:
 *   HistSaveRequest.ofCreate("MEMBER", po, after).tableNm("MEMBER")
 *   HistSaveRequest.ofUpdate("MEMBER", po, before, after).tableNm("MEMBER")
 */
public class HistSaveRequest {

    public static final String ACTION_CREATE = "CREATE";
    public static final String ACTION_UPDATE = "UPDATE";
    public static final String ACTION_DELETE = "DELETE";

    private String entity_type;
    private String table_nm;
    private String entity_id;
    private String action_type;
    private Object before_data;
    private Object after_data;
    private String actor_id;
    private String actor_nm;
    private String reason;
    private String req_ip;
    private String trace_id;

    public static HistSaveRequest ofCreate(String entityType, String entityId, Object afterData) {
        HistSaveRequest req = new HistSaveRequest();
        req.entity_type = entityType;
        req.entity_id = entityId;
        req.action_type = ACTION_CREATE;
        req.after_data = afterData;
        return req;
    }

    public static HistSaveRequest ofUpdate(String entityType, String entityId, Object beforeData, Object afterData) {
        HistSaveRequest req = new HistSaveRequest();
        req.entity_type = entityType;
        req.entity_id = entityId;
        req.action_type = ACTION_UPDATE;
        req.before_data = beforeData;
        req.after_data = afterData;
        return req;
    }

    public static HistSaveRequest ofDelete(String entityType, String entityId, Object beforeData) {
        HistSaveRequest req = new HistSaveRequest();
        req.entity_type = entityType;
        req.entity_id = entityId;
        req.action_type = ACTION_DELETE;
        req.before_data = beforeData;
        return req;
    }

    public HistSaveRequest tableNm(String tableNm) {
        this.table_nm = tableNm;
        return this;
    }

    public HistSaveRequest actor(String actorId, String actorNm) {
        this.actor_id = actorId;
        this.actor_nm = actorNm;
        return this;
    }

    public HistSaveRequest reason(String reason) {
        this.reason = reason;
        return this;
    }

    public HistSaveRequest reqIp(String reqIp) {
        this.req_ip = reqIp;
        return this;
    }

    public HistSaveRequest traceId(String traceId) {
        this.trace_id = traceId;
        return this;
    }

    public String getEntity_type() {
        return entity_type;
    }

    public String getTable_nm() {
        return table_nm;
    }

    public String getEntity_id() {
        return entity_id;
    }

    public String getAction_type() {
        return action_type;
    }

    public Object getBefore_data() {
        return before_data;
    }

    public Object getAfter_data() {
        return after_data;
    }

    public String getActor_id() {
        return actor_id;
    }

    public String getActor_nm() {
        return actor_nm;
    }

    public String getReason() {
        return reason;
    }

    public String getReq_ip() {
        return req_ip;
    }

    public String getTrace_id() {
        return trace_id;
    }
}
