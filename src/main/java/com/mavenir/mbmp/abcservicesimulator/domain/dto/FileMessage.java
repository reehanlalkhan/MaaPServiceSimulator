package com.mavenir.mbmp.abcservicesimulator.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FileMessage {
    String fileUrl;
    String fileName;
    String fileMIMEType;
    long fileSize;

    @Override
    public String toString() {
        return "FileMessage{" +
            "fileUrl='" + fileUrl + '\'' +
            ", fileName='" + fileName + '\'' +
            ", fileMIMEType='" + fileMIMEType + '\'' +
            ", fileSize=" + fileSize +
            '}';
    }
}
