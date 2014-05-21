package com.company.lib.entity;

public class OperLog {
		
	private Long id;//
	private String userName;//
	private String resourcePattern;//
	private String resourceId;//
	private boolean  success; //
	private String  remarks;// 额为说明
	private java.util.Date createTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getResourcePattern() {
		return resourcePattern;
	}
	public void setResourcePattern(String resourcePattern) {
		this.resourcePattern = resourcePattern;
	}
	public String getResourceId() {
		return resourceId;
	}
	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public java.util.Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}
	public OperLog() {
		super();
	}
	@Override
	public String toString() {
		return "OperLog [id=" + id + ", userName=" + userName
				+ ", resourcePattern=" + resourcePattern + ", resourceId="
				+ resourceId + ", success=" + success + ", remarks=" + remarks
				+ ", createTime=" + createTime + "]";
	}
	
	
	
	
}
