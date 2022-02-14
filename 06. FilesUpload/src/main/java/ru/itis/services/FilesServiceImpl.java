package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.models.NewFile;
import ru.itis.repositories.FileRepository;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class FilesServiceImpl implements FilesService {

    private final FileRepository fileRepository;
    @Value("${storage.path}")
    private String storagePath;

    @Autowired
    public FilesServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public void upload(Part part) {
        NewFile file = savedFile(part);
        try {
            Files.copy(part.getInputStream(), Paths.get(storagePath + file.getUuidName()));
            fileRepository.save(file);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void download(String fileName, HttpServletResponse response) {
        try {
            NewFile file = fileRepository.findByUuidName(fileName).get();
            response.setHeader("Content-Disposition", "filename=\"" + file.getOriginalName() + "\"");
            response.setContentType(file.getMimeType());
            response.setContentLengthLong(file.getSize());
            Files.copy(Paths.get(storagePath + file.getUuidName()), response.getOutputStream());
        } catch (IOException | NoSuchElementException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private NewFile savedFile(Part part) {
        String[] fileName = part.getSubmittedFileName().split("\\.");
        String uuidName = fileName.length > 1 ? UUID.randomUUID() + "." + fileName[fileName.length - 1] : "";
        while (fileRepository.findByUuidName(uuidName).isPresent()) {
            uuidName = UUID.randomUUID() + "." + fileName[fileName.length - 1];
        }
        return NewFile.builder()
                .size(part.getSize())
                .originalName(part.getSubmittedFileName())
                .uuidName(uuidName)
                .mimeType(part.getContentType())
                .build();
    }
}
