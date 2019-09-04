package com.senior.plan2task.FileService.File;

import com.senior.plan2task.FileService.Filter.TokenAuthenticationService;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class FileController {
    
    private final String Uri = "https://www.fileservice.plan2task.xyz/";
    
    @Autowired
    private FileStorageService fileStorageService;
    
    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;
    
    @GetMapping("/file/token")
    public ResponseEntity<FileStorageToken> getTokenFile(HttpServletRequest request) {
        String data = tokenAuthenticationService.createToken(request);
        String[] dataArray = data.split("\\.");
        return new ResponseEntity<>(new FileStorageToken(dataArray[0], dataArray[1], dataArray[2]), HttpStatus.OK);
    }
    
    @PostMapping("/upload/image/{planId}")
    public ResponseEntity<String> uploadImagePlan(HttpServletRequest request, @RequestParam("file") MultipartFile file, @PathVariable String planId) {
        if("jpg".equals(fileStorageService.checkTypeFile(file.getOriginalFilename())) || "png".equals(fileStorageService.checkTypeFile(file.getOriginalFilename()))){
            String userId = tokenAuthenticationService.getDataByToken(request);
            String typeFile = fileStorageService.checkTypeFile(file.getOriginalFilename());
            fileStorageService.storeFile(file, userId + "/" + planId, "plan." + typeFile);
            
            return new ResponseEntity<>(this.Uri + "file/" + planId + "/" + "plan." + typeFile + "?id=" + userId, HttpStatus.OK);
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "It is not an image file !!!");
        }
    }
    
    @PostMapping("/upload/image/{planId}/{taskId}")
    public ResponseEntity<String> uploadImageTask(HttpServletRequest request, @RequestParam("file") MultipartFile file, @PathVariable String planId, @PathVariable String taskId) {
        if("jpg".equals(fileStorageService.checkTypeFile(file.getOriginalFilename())) || "png".equals(fileStorageService.checkTypeFile(file.getOriginalFilename()))){
            String userId = tokenAuthenticationService.getDataByToken(request);
            String typeFile = fileStorageService.checkTypeFile(file.getOriginalFilename());
            fileStorageService.storeFile(file, userId + "/" + planId + "/" + taskId, "task." + typeFile);
            
            return new ResponseEntity<>(this.Uri + "file/" + planId + "/" + taskId + "/" + "task." + typeFile + "?id=" + userId, HttpStatus.OK);
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "It is not an image file !!!");
        }
    }
    
    @PostMapping("/upload/file/{planId}/{taskId}")
    public ResponseEntity<String> uploadFileTask(HttpServletRequest request, @RequestParam("file") MultipartFile file, @PathVariable String planId, @PathVariable String taskId) {
        String userId = tokenAuthenticationService.getDataByToken(request);
        String fileName = StringUtils.cleanPath((""+new Date()).replace(" ", "_") + "_" + file.getOriginalFilename());
        fileStorageService.storeFile(file, userId + "/" + planId + "/" + taskId, fileName);
        
        return new ResponseEntity<>(this.Uri + "files/" + planId + "/" + taskId + "/" + fileName, HttpStatus.OK);
    }
    
    @GetMapping("/file/{planId}/{fileName}")
    public StreamingResponseBody getSteamingImagePlan(HttpServletResponse response, @PathVariable String planId, @PathVariable String fileName, @RequestParam String id) throws URISyntaxException, IOException {
        
        response.setContentType("image/png");
        response.setHeader("Content-Disposition", "inline; filename=" + fileName);
        
        if(id!=null){

            InputStream inputStream = fileStorageService.getFileResource(id + "/" + planId,fileName).getInputStream();

            return outputStream -> {
                int nRead;
                byte[] data = new byte[1024];
                while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                    outputStream.write(data, 0, nRead);
                }
                inputStream.close();
            };
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "400 Bad Request");
        }
    }
    
    @GetMapping("/file/{planId}/{taskId}/{fileName}")
    public StreamingResponseBody getSteamingImageTask(HttpServletResponse response, @PathVariable String planId, @PathVariable String taskId, @PathVariable String fileName, @RequestParam String id) throws URISyntaxException, IOException {
        
        response.setContentType("image/png");
        response.setHeader("Content-Disposition", "inline; filename=" + fileName);
        
        if(id!=null){

            InputStream inputStream = fileStorageService.getFileResource(id + "/" + planId + "/" + taskId,fileName).getInputStream();

            return outputStream -> {
                int nRead;
                byte[] data = new byte[1024];
                while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                    outputStream.write(data, 0, nRead);
                }
                inputStream.close();
            };
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "400 Bad Request");
        }
    }
    
    @GetMapping("/files/{planId}/{taskId}/{fileName}")
    public StreamingResponseBody getSteamingFileTask(HttpServletRequest request, HttpServletResponse response, @PathVariable String planId, @PathVariable String taskId, @PathVariable String fileName, @RequestParam String write, @RequestParam String id, @RequestParam String from) throws URISyntaxException, IOException {
        String fileId = tokenAuthenticationService.getDataById(write + "." + id + "." + from);
        String userId = tokenAuthenticationService.getDataById(fileId);
        
        if(userId!=null){
        
            if("pdf".equals(fileStorageService.checkTypeFile(fileName))){
                response.setContentType("application/pdf");
            }

            if("jpg".equals(fileStorageService.checkTypeFile(fileName)) || "png".equals(fileStorageService.checkTypeFile(fileName))){
                response.setContentType("image/png");
            }

            response.setHeader("Content-Disposition", "inline; filename=" + fileName);

            InputStream inputStream = fileStorageService.getFileResource(userId + "/" + planId + "/" + taskId,fileName).getInputStream();

            return outputStream -> {
                int nRead;
                byte[] data = new byte[1024];
                while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                    outputStream.write(data, 0, nRead);
                }
                inputStream.close();
            };
        
        }else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "400 Bad Request");
        }
    }
    
    @DeleteMapping("/file/{fileName}")
    public String deleteFile(HttpServletResponse response,@PathVariable String fileName) {
        return fileStorageService.deleteFileResource(fileName);
    }
    
}