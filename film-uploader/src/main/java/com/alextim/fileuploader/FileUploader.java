package com.alextim.fileuploader;

import org.apache.log4j.Logger;

import com.alextim.fileuploader.settings.FileUploaderSettings;

/**
 * 
 * Runnable that uploads the films files from a some directory
 * 
 *
 */
public class FileUploader implements Runnable{

    private final Logger log = Logger.getLogger(FileUploader.class);
    
    public void run() {
        FileUploaderSettings settings = new FileUploaderSettings();
        String directory = settings.getUploadDirectory();
        
        log.debug(directory);
    }


}
