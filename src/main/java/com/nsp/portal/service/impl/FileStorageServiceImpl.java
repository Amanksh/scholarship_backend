package com.nsp.portal.service.impl;

import com.nsp.portal.service.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

/**
 * Implementation of FileStorageService for file storage operations.
 * 
 * DEVELOPER 5 TASK: Implement the following methods:
 * 1. storeFile() - Complete file storage logic
 * 2. loadFile() - Complete file loading logic
 * 3. deleteFile() - Complete file deletion logic
 * 4. generateUniqueFilename() - Complete filename generation logic
 * 
 * This service implementation is responsible for:
 * - Storing uploaded files in the file system
 * - Loading stored files for retrieval
 * - Deleting files when needed
 * - Managing file paths and directories
 * - File validation and security
 */
@Service
public class FileStorageServiceImpl implements FileStorageService {
    
    @Value("${file.upload.path:uploads}")
    private String uploadPath;
    
    /**
     * Stores an uploaded file in the specified directory.
     * 
     * TODO: DEVELOPER 5 - Implement this method:
     * 1. Create directory if it doesn't exist
     * 2. Generate unique filename
     * 3. Copy file to storage location
     * 4. Return the file path
     * 
     * @param file the file to store
     * @param directory the directory to store the file in
     * @return the file path where the file was stored
     */
    @Override
    public String storeFile(MultipartFile file, String directory) {
        // TODO: Implement file storage logic
        // 1. Create directory structure
        // 2. Generate unique filename
        // 3. Copy file to storage
        // 4. Return file path
        
        try {
            // Create the directory if it doesn't exist
            Path uploadDir = Paths.get(uploadPath, directory);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }
            
            // Generate unique filename
            String uniqueFilename = generateUniqueFilename(file.getOriginalFilename());
            Path filePath = uploadDir.resolve(uniqueFilename);
            
            // Copy file to storage location
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            
            return filePath.toString();
            
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file: " + e.getMessage(), e);
        }
    }
    
    /**
     * Loads a stored file from the specified directory.
     * 
     * TODO: DEVELOPER 5 - Implement this method:
     * 1. Construct file path
     * 2. Read file content
     * 3. Return file as byte array
     * 
     * @param fileName the name of the file to load
     * @param directory the directory where the file is stored
     * @return the file as a byte array
     */
    @Override
    public byte[] loadFile(String fileName, String directory) {
        // TODO: Implement file loading logic
        // 1. Construct file path
        // 2. Read file content
        // 3. Return byte array
        
        try {
            Path filePath = Paths.get(uploadPath, directory, fileName);
            if (!Files.exists(filePath)) {
                throw new RuntimeException("File not found: " + fileName);
            }
            
            return Files.readAllBytes(filePath);
            
        } catch (IOException e) {
            throw new RuntimeException("Failed to load file: " + e.getMessage(), e);
        }
    }
    
    /**
     * Deletes a stored file from the specified directory.
     * 
     * TODO: DEVELOPER 5 - Implement this method:
     * 1. Construct file path
     * 2. Check if file exists
     * 3. Delete file
     * 4. Return deletion result
     * 
     * @param fileName the name of the file to delete
     * @param directory the directory where the file is stored
     * @return true if file was deleted successfully, false otherwise
     */
    @Override
    public boolean deleteFile(String fileName, String directory) {
        // TODO: Implement file deletion logic
        // 1. Construct file path
        // 2. Check file existence
        // 3. Delete file
        // 4. Return result
        
        try {
            Path filePath = Paths.get(uploadPath, directory, fileName);
            if (Files.exists(filePath)) {
                Files.delete(filePath);
                return true;
            }
            return false;
            
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete file: " + e.getMessage(), e);
        }
    }
    
    /**
     * Generates a unique filename for uploaded files.
     * 
     * TODO: DEVELOPER 5 - Implement this method:
     * 1. Generate UUID
     * 2. Preserve file extension
     * 3. Return unique filename
     * 
     * @param originalFilename the original filename
     * @return a unique filename
     */
    @Override
    public String generateUniqueFilename(String originalFilename) {
        // TODO: Implement unique filename generation
        // 1. Generate UUID
        // 2. Extract file extension
        // 3. Combine UUID with extension
        
        if (originalFilename == null || originalFilename.isEmpty()) {
            return UUID.randomUUID().toString();
        }
        
        String extension = "";
        int lastDotIndex = originalFilename.lastIndexOf('.');
        if (lastDotIndex > 0) {
            extension = originalFilename.substring(lastDotIndex);
        }
        
        return UUID.randomUUID().toString() + extension;
    }
}
