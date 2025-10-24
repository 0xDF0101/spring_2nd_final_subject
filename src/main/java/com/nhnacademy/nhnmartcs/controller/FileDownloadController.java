package com.nhnacademy.nhnmartcs.controller;

import com.nhnacademy.nhnmartcs.domain.Complain;
import com.nhnacademy.nhnmartcs.repository.ComplainRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Slf4j
@Controller
public class FileDownloadController {
    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam("filePath") String filePath) throws IOException {

        Path requestedPath = Paths.get(filePath).normalize();
        File fileToDownload = requestedPath.toFile();

        // 2. 파일 존재 여부 및 접근 권한 확인
        if (!fileToDownload.exists() || !fileToDownload.canRead()) {
            log.warn("File not found or not readable: {}", requestedPath);
            return ResponseEntity.notFound().build();
        }

        // 3. 파일의 MIME 타입 결정 (Content-Type)
        String contentType = Files.probeContentType(requestedPath);
        if (contentType == null) {
            contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
            log.debug("Could not probe content type for {}. Defaulting to {}", requestedPath, contentType);
        }

        // 파일 명 인코딩
        String finalDisplayFileName = requestedPath.getFileName().toString();
        String encodedDisplayFileName;
        try {
            encodedDisplayFileName = URLEncoder.encode(finalDisplayFileName, StandardCharsets.UTF_8.toString()).replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException e) {
            log.error("Error encoding display file name: {}", finalDisplayFileName, e);
            encodedDisplayFileName = finalDisplayFileName;
        }

        // 5. HTTP 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + encodedDisplayFileName + "\"");
        headers.add(HttpHeaders.CACHE_CONTROL, "no-cache, no-store, must-revalidate");
        headers.add(HttpHeaders.PRAGMA, "no-cache");
        headers.add(HttpHeaders.EXPIRES, "0");

        // 6. 파일을 InputStream으로 읽어 Resource 객체로 만듭니다.
        Resource resource = new InputStreamResource(new FileInputStream(fileToDownload));

        // 7. ResponseEntity를 반환합니다.
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.parseMediaType(contentType))
                .contentLength(fileToDownload.length())
                .body(resource);
    }


}
