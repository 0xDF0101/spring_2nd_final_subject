package com.nhnacademy.nhnmartcs.domain;

import lombok.Value;

@Value
public class FileAttachment {
    private final String uuid;
    private final String fileName;
    private final String filePath;
    private final long fileSize;
}
