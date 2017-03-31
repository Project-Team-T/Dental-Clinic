/**
 * 
 */
package com.dentalclinic.webcontent.storage;

/**
 * @author fanfan
 *
 */
public class StorageFileNotFoundException extends StorageException {
	
	public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
