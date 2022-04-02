package ru.itis.services;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.exceptions.FileNotFoundException;
import ru.itis.models.Account;
import ru.itis.models.FileInfo;
import ru.itis.repositories.AccountsRepository;
import ru.itis.repositories.FileInfoRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FilesServiceImpl implements FilesService {

    private final FileInfoRepository fileInfoRepository;
    private final AccountsRepository accountsRepository;
    @Value("${files.storage.path}")
    private String storagePath;
    @Value("${files.url}")
    private String filesUrl;

    @Transactional
    @Override
    public String upload(MultipartFile multipart) {
        try {
            String extension = multipart.getOriginalFilename().substring(multipart.getOriginalFilename().lastIndexOf("."));

            String storageFileName = UUID.randomUUID() + extension;

            FileInfo file = FileInfo.builder()
                    .type(multipart.getContentType())
                    .originalFileName(multipart.getOriginalFilename())
                    .storageFileName(storageFileName)
                    .size(multipart.getSize())
                    .build();

            Files.copy(multipart.getInputStream(), Paths.get(storagePath, file.getStorageFileName()));

            fileInfoRepository.save(file);

            return storageFileName;
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void addFileToResponse(String fileName, HttpServletResponse response) {
        FileInfo file = fileInfoRepository.findByStorageFileName(fileName).orElseThrow(FileNotFoundException::new);
        response.setContentLength(file.getSize().intValue());
        response.setContentType(file.getType());
        response.setHeader("Content-Disposition", "filename=\"" + file.getOriginalFileName() + "\"");
        try {
            IOUtils.copy(new FileInputStream(storagePath + "\\" + file.getStorageFileName()), response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Account getAccount(String fileName) {
        Optional<Account> account = accountsRepository.findByStorageFileName(fileName);
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
