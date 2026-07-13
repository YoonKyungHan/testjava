package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.service.FileUploadService;

/**
 * 파일 업로드 Controller
 * 유형별 팝업 → setAhan_sm 형태로 부모 전달 → 서버 업로드
 */
@Controller
@RequestMapping("/file")
public class FileUploadController {

    @Autowired
    private FileUploadService fileUploadService;

    /** 부모 화면 */
    @GetMapping("/parentForm.do")
    public String parentForm() {
        return "file/parentForm";
    }

    /**
     * 유형별 파일 팝업
     * /file/fileModal.do?fileType=ahan_sm
     */
    @GetMapping("/fileModal.do")
    public String fileModal(@RequestParam(value = "fileType", required = false) String fileType) {
        return "file/fileModal";
    }

    /**
     * 5유형 다중파일 업로드
     * name: ahan_sm, bhan_sm, chan_sm, dhan_sm, ehan_sm
     */
    @PostMapping(
            value = "/upload.do",
            consumes = "multipart/form-data",
            produces = "text/plain; charset=UTF-8"
    )
    @ResponseBody
    public String upload(
            @RequestParam(value = "ahan_sm", required = false) MultipartFile[] ahan_sm,
            @RequestParam(value = "bhan_sm", required = false) MultipartFile[] bhan_sm,
            @RequestParam(value = "chan_sm", required = false) MultipartFile[] chan_sm,
            @RequestParam(value = "dhan_sm", required = false) MultipartFile[] dhan_sm,
            @RequestParam(value = "ehan_sm", required = false) MultipartFile[] ehan_sm) throws Exception {

        int saveCnt = fileUploadService.uploadFiles(
                ahan_sm, bhan_sm, chan_sm, dhan_sm, ehan_sm);

        return "success:" + saveCnt;
    }
}
