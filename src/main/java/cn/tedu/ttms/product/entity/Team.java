package cn.tedu.ttms.product.entity;

import java.io.Serializable;
import java.util.Date;

public class Team implements Serializable{

	private static final long serialVersionUID = 368009064732092303L;
	
	/** 团目id */
	private Integer id;
	/** 团目名称 */
	private String name;
	/** 团目id */
	private Integer projectId;
	/** 团目状态 */
	private Integer valid;
	/** 团目备注 */
	private String note;
	/** 创建用户  */
	private String createdUser;
	/** 修改用户  */
	private String modifiedUser;
	/** 创建时间 */
	private Date createdTime;
	/** 修改时间 */
	private Date modifiedTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getProjectId() {
		return projectId;
	}
	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}
	public Integer getValid() {
		return valid;
	}
	public void setValid(Integer valid) {
		this.valid = valid;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}
	public String getModifiedUser() {
		return modifiedUser;
	}
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", projectId=" + projectId + ", valid=" + valid + ", note=" + note
				+ ", createdUser=" + createdUser + ", modifiedUser=" + modifiedUser + ", createdTime=" + createdTime
				+ ", modifiedTime=" + modifiedTime + "]";
	}
	
	
}
