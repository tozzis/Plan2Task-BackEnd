package com.senior.plan2task.FileService.File;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    private final FileStorageProperties fileStorageProperties;

    @Autowired
    public FileStorageService(FileStorageProperties fileStoragePropertiesInput) {
        this.fileStorageProperties = fileStoragePropertiesInput;
        this.fileStorageLocation = Paths.get(fileStoragePropertiesInput.getUploadDir()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.",
                    ex);
        }
    }

    public String checkTypeFile(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
    }

    public String storeFile(MultipartFile file, String folder, String fileName) {

        try {
            Path targetLocation = Paths.get((this.fileStorageProperties.getUploadDir()) + "/" + folder).toAbsolutePath()
                    .normalize();

            File checkFolder = targetLocation.toFile();

            if (!checkFolder.exists()) {
                Files.createDirectories(targetLocation);
            }

            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            Files.copy(file.getInputStream(), targetLocation.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);

            return folder + "/" + fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource getFileResource(String folder, String fileName) {
        try {
            Path filePath = Paths.get((this.fileStorageProperties.getUploadDir()) + "/" + folder).toAbsolutePath()
                    .normalize().resolve(fileName);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }

    public String deleteFileResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                File file = filePath.toFile();
                if (file.delete()) {
                    return "File deleted successfully";
                } else {
                    throw new MyFileNotFoundException("Failed to delete the file " + fileName);
                }
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
}