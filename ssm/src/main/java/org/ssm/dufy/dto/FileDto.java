package org.ssm.dufy.dto;

public class FileDto {

	private String filePath;
	private String fileName;
	
	
	
	public FileDto() {
	}
	
	public FileDto(String filePath, String fileName) {
		super();
		this.filePath = filePath;
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
