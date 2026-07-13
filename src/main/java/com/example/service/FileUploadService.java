package com.example.service;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 다중 파일 업로드 서비스
 * 유형: ahan_sm, bhan_sm, chan_sm, dhan_sm, ehan_sm
 */
@Service
public class FileUploadService {

    private static final String UPLOAD_PATH = "/upload/member/";

    public int uploadFiles(MultipartFile[] ahan_sm,
                           MultipartFile[] bhan_sm,
                           MultipartFile[] chan_sm,
                           MultipartFile[] dhan_sm,
                           MultipartFile[] ehan_sm) throws Exception {

        int saveCnt = 0;

        saveCnt += saveFileList(ahan_sm, "ahan_sm");
        saveCnt += saveFileList(bhan_sm, "bhan_sm");
        saveCnt += saveFileList(chan_sm, "chan_sm");
        saveCnt += saveFileList(dhan_sm, "dhan_sm");
        saveCnt += saveFileList(ehan_sm, "ehan_sm");

        return saveCnt;
    }

    private int saveFileList(MultipartFile[] files, String typeDir) throws Exception {

        if (files == null || files.length == 0) {
            return 0;
        }

        int cnt = 0;
        String dirPath = UPLOAD_PATH + typeDir + "/";
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];

            if (file == null || file.isEmpty()) {
                continue;
            }

            String orgName = file.getOriginalFilename();
            String saveName = UUID.randomUUID().toString() + "_" + orgName;

            file.transferTo(new File(dirPath + saveName));
            cnt++;

            // TODO: 파일정보 DB insert
        }

        return cnt;
    }
}
