package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.service.HistoryService;
import com.example.vo.HistEventVO;

/**
 * 변경이력 조회
 *
 * GET /hist/list.do?entityType=MEMBER&entityId=pp2026-001
 * GET /hist/detail.do?histId=1
 */
@Controller
@RequestMapping("/hist")
public class HistController {

    @Autowired
    private HistoryService historyService;

    @GetMapping(value = "/list.do", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public List<HistEventVO> list(
            @RequestParam("entityType") String entityType,
            @RequestParam("entityId") String entityId) {
        return historyService.listByEntity(entityType, entityId);
    }

    @GetMapping(value = "/detail.do", produces = "application/json; charset=UTF-8")
    @ResponseBody
    public HistEventVO detail(@RequestParam("histId") Long histId) {
        return historyService.getDetail(histId);
    }
}
