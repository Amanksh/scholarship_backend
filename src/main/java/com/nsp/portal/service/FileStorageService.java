package com.nsp.portal.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * Service interface for file storage operations.
 * 
 * DEVELOPER 5 TASK: Implement the following methods in FileStorageServiceImpl:
 * 1. storeFile() - Store uploaded files
 * 2. loadFile() - Load stored files
 * 3. deleteFile() - Delete stored files
 * 
 * This service is responsible for:
 * - Handling all file uploads for scholarship applications
 * - Managing file storage and retrieval
 * - File validation and security
 * - File path management
 */
public interface FileStorageService {
    
    /**
     * Stores an uploaded file.
     * 
     * @param file the file to store
     * @param directory the directory to store the file in
     * @return the file path where the file was stored
     */
    String storeFile(MultipartFile file, String directory);
    
    /**
     * Loads a stored file.
     * 
     * @param fileName the name of the file to load
     * @param directory the directory where the file is stored
     * @return the file as a byte array
     */
    byte[] loadFile(String fileName, String directory);
    
    /**
     * Deletes a stored file.
     * 
     * @param fileName the name of the file to delete
     * @param directory the directory where the file is stored
     * @return true if file was deleted successfully, false otherwise
     */
    boolean deleteFile(String fileName, String directory);
    
    /**
     * Generates a unique filename for uploaded files.
     * 
     * @param originalFilename the original filename
     * @return a unique filename
     */
    String generateUniqueFilename(String originalFilename);
}
