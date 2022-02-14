package ru.itis.services;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public interface FilesService {
    void upload(Part part);

    void download(String fileName, HttpServletResponse response);
}
