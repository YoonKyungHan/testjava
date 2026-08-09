package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.service.PdfService;

/**
 * HTML → PDF 다운로드
 * GET /pdf/download.do
 *
 * 브라우저에서 바로 다운로드 (프론트 html2pdf 등 불필요)
 */
@Controller
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    private PdfService pdfService;

    @GetMapping("/download.do")
    public ResponseEntity<byte[]> download(
            @RequestParam(value = "title", required = false, defaultValue = "문서") String title)
            throws Exception {

        // 1) HTML 만들기 (실무: 화면과 같은 데이터로 HTML 조립/템플릿 렌더)
        String html = pdfService.buildSampleHtml(title, "서버에서 HTML을 PDF로 변환한 예시입니다.");

        // 2) PDF 변환
        byte[] pdfBytes = pdfService.htmlToPdf(html);

        // 3) 다운로드 응답
        String fileName = "document.pdf";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(pdfBytes.length)
                .body(pdfBytes);
    }
}
