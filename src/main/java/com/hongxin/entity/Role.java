package com.hongxin.entity;
// Generated 2016-8-1 17:00:34 by Hibernate Tools 4.3.1.Final

/**
 * FlRole generated by hbm2java
 */
public class Role implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2662206017446441567L;
	private String roleId;
	private String roleName;
	private String description;

	public Role() {
	}

	public Role(String roleId) {
		this.roleId = roleId;
	}

	public Role(String roleId, String roleName, String description) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.description = description;
	}

	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
