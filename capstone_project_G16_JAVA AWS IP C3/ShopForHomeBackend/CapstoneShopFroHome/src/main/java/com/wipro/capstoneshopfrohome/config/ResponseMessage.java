package com.wipro.capstoneshopfrohome.config;

public class ResponseMessage {

	private String message;
	private String fileDownloadUrl;
	
	
	public ResponseMessage(String message, String fileDownloadUrl) {
		this.message = message;
		this.fileDownloadUrl = fileDownloadUrl;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getFileDownloadUrl() {
		return fileDownloadUrl;
	}
	public void setFileDownloadUrl(String fileDownloadUrl) {
		this.fileDownloadUrl = fileDownloadUrl;
	}
	
}
