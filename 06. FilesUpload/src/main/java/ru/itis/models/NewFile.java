package ru.itis.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class NewFile {
    private int id;
    private long size;
    private String originalName;
    private String uuidName;
    private String mimeType;
}
