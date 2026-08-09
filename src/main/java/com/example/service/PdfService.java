package com.example.service;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

/**
 * HTML → PDF (서버사이드)
 * 프론트 라이브러리 없이 Spring Boot + OpenHTMLToPDF
 *
 * Maven:
 * <dependency>
 *   <groupId>com.openhtmltopdf</groupId>
 *   <artifactId>openhtmltopdf-pdfbox</artifactId>
 *   <version>1.0.10</version>
 * </dependency>
 */
@Service
public class PdfService {

    /**
     * HTML 문자열을 PDF byte[] 로 변환
     */
    public byte[] htmlToPdf(String html) throws Exception {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.useFastMode();
        builder.withHtmlContent(html, null);  // baseUrl 필요시 두 번째 인자
        builder.toStream(out);
        builder.run();

        return out.toByteArray();
    }

    /**
     * 화면용 HTML 예시 (실제로는 Thymeleaf/JSP 렌더 결과를 넣으면 됨)
     */
    public String buildSampleHtml(String title, String bodyText) {

        // OpenHTMLToPDF 는 XHTML 에 가깝게 작성하는 게 안전
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>");
        sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
        sb.append("<head>");
        sb.append("<meta charset=\"UTF-8\" />");
        sb.append("<style type=\"text/css\">");
        sb.append("body { font-family: sans-serif; font-size: 12px; }");
        sb.append("h1 { font-size: 18px; }");
        sb.append("table { width: 100%; border-collapse: collapse; }");
        sb.append("th, td { border: 1px solid #333; padding: 6px; }");
        sb.append("</style>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("<h1>").append(escape(title)).append("</h1>");
        sb.append("<p>").append(escape(bodyText)).append("</p>");
        sb.append("<table>");
        sb.append("<tr><th>항목</th><th>내용</th></tr>");
        sb.append("<tr><td>샘플1</td><td>값1</td></tr>");
        sb.append("<tr><td>샘플2</td><td>값2</td></tr>");
        sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");

        return sb.toString();
    }

    private String escape(String s) {
        if (s == null) {
            return "";
        }
        return s.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;");
    }
}
