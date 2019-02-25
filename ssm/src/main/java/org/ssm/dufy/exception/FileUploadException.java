package org.ssm.dufy.exception;

public class FileUploadException extends Exception {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String message;

	    public FileUploadException(String message) {
	        //super(message);
	        this.message = message;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }
}
