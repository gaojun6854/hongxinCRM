package com.hongxin.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ResourceBak implements java.io.Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
    private Integer sourceId;//编号
	
	
    private Integer parentSourceId;//父级菜单编码
	
	
    private String sourceCode;//菜单编码
	
	
    private Integer seq;//排序
	
	
    private String sourceName;//菜单名称
	
	
    private String sourceUrl;//菜单地址
	
	
    private String style;//class 

	private String picUrl;
    private  ResourceBak parentResourceBak;
    
    private List<ActionFun> funlist;
    private List<ResourceBak>resourceBakss;
    
    private Set<ResourceBak>resourceBaks=new HashSet<ResourceBak>(0);
    private List<ResourceBak>childMenus=new ArrayList<ResourceBak>();
    
	public List<ResourceBak> getChildMenus(){
		return childMenus;
	}

	public void setChildMenus(List<ResourceBak> childMenus) {
		this.childMenus = childMenus;
	}

	public List<ActionFun> getFunlist() {
		return funlist;
	}

	public void setFunlist(List<ActionFun> funlist) {
		this.funlist = funlist;
	}

	public List<ResourceBak> getResourceBakss() {
		return resourceBakss;
	}

	public void setResourceBakss(List<ResourceBak> resourceBakss) {
		this.resourceBakss = resourceBakss;
	}

	public ResourceBak getParentResourceBak() {
		return parentResourceBak;
	}

	public void setParentResourceBak(ResourceBak parentResourceBak) {
		this.parentResourceBak = parentResourceBak;
	}

	public Set<ResourceBak> getResourceBaks() {
		return resourceBaks;
	}

	public void setResourceBaks(Set<ResourceBak> resourceBaks) {
		this.resourceBaks = resourceBaks;
	}

    public Integer getSourceId() {
        return sourceId;
    }

    public void setSourceId(Integer sourceId) {
        this.sourceId = sourceId;
    }
    
   public Integer getParentSourceId() {
        return parentSourceId;
    }

    public void setParentSourceId(Integer parentSourceId) {
        this.parentSourceId = parentSourceId ;
    }
    
    public String getSourceCode() {
        return sourceCode;
    }
    
    public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}
    
    public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	

    public String getSourceName() {
		return sourceName;
	}

	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}

	public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl == null ? null : sourceUrl.trim();
    }

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}