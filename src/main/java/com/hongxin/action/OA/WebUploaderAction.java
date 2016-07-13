package com.hongxin.action.OA;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.hongxin.service.CheckReceiptsService;
import com.hongxin.utils.AjaxUtils;
import com.hongxin.utils.UploadCommon;
import com.opensymphony.xwork2.ActionSupport;

public class WebUploaderAction extends ActionSupport {
	
	@Autowired
	CheckReceiptsService checkReceiptsService;
	
    private static final long serialVersionUID = 1L;
    private File file;
    private String fileFileName;
    private String fileContentType;
    private File[] up;
    private String[] upFileName;
    private String[] upContentType;
    private int code;
    private String msg;
    
    private String url;
	private String custIDS;
	private String picType;
    private String recNum;
    
    public String ajaxUpload() {
        String ret = "input", ext = "";
        if(file == null) {
            code = 1000;
            msg = "There is No File to Upload!";
        } else {
            ext = this.getFileFileName().substring(this.getFileFileName().lastIndexOf(".")).toLowerCase();
            if(".jpg.png.bmp.gif.xls.xlsx.doc.docx.ppt.pptx.rar.zip.7z".indexOf(ext) != -1) {
                UploadCommon upload = new UploadCommon();
                String fps="";
				try {
					fps = upload.singleUpload(file, this.getFileFileName(),custIDS,checkReceiptsService,picType);
				} catch (Exception e) {
					e.printStackTrace();
				}
                fps = fps.replace(",", "");
                if(!"".equals(fps)) {
                    code = 1001;
                    msg = "Upload Files Success!";
                    ret = "success";
                } else {
                    code = 1009;
                    msg = "Upload Files Failed!";
                }
            } else {
                code = 1002;
                msg = "The File of Type is not Allowed to Upload!";
            }
        }
        return ret;
    }
    public String commUpload() {
        String ret = "input", ext = "";
        if(file == null) {
            code = 1000;
            msg = "There is No File to Upload!";
        } else {
            boolean ck = false;
            for(int i=0; i<up.length; ++i) {
                ext = this.getUpFileName()[i].substring(this.getUpFileName()[i].lastIndexOf(".")).toLowerCase();
                if(".jpg.png.bmp.gif.xls.xlsx.doc.docx.ppt.pptx.rar.zip.7z".indexOf(ext) == -1) {
                    ck = true;
                    break;
                }
            }
            if(ck) {
                code = 1002;
                msg = "The Files which are not Allowed Type are Contained!";
            } else {
            	UploadCommon upload = new UploadCommon();
                String fps = upload.commUpload(up, this.getUpFileName());
                fps = fps.replace(",", "");
                if(!"".equals(fps)) {
                    code = 1001;
                    msg = "Upload Files Failed!";
                    ret = "success";
                } else {
                    code = 1009;
                    msg = "Upload Files Failed!";
                }
            }
        }
        return ret;
    }
    
    
    public void ajaxuploadChange() throws IOException {
        String ext = "";
        if(file == null) {
        	ServletActionContext.getResponse().getWriter().print("<script>window.parent.uploadSuccess('0');</script>");
        } else {
            ext = this.getFileFileName().substring(this.getFileFileName().lastIndexOf(".")).toLowerCase();
            if(".jpg.png.bmp.gif.xls.xlsx.doc.docx.ppt.pptx.rar.zip.7z".indexOf(ext) != -1) {
                UploadCommon upload = new UploadCommon();
                String fps="";
				try {
					fps = upload.singleUploadChange(file, this.getFileFileName(),custIDS,recNum,checkReceiptsService);
				} catch (Exception e) {
					e.printStackTrace();
				}
				ServletActionContext.getResponse().getWriter().print("<script>window.parent.uploadSuccess('"+fps+"','"+recNum+"');</script>");
            } else {
            	ServletActionContext.getResponse().getWriter().print("<script>window.parent.uploadSuccess('1','"+recNum+"');</script>");//格式不对
            }
        }
    }
    
    public void ajaxDaleteImg() {
    	HttpServletRequest request=ServletActionContext.getRequest();
    	String recNum=request.getParameter("recNum");
    	try {
    		checkReceiptsService.delete(recNum);
		} catch (Exception e) {
			e.printStackTrace();
			AjaxUtils.ajaxResponse("0");
		}
    	AjaxUtils.ajaxResponse("1");
    }
    
    
    public String getRecNum() {
		return recNum;
	}
	public void setRecNum(String recNum) {
		this.recNum = recNum;
	}
	public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }
    public String getFileFileName() {
        return fileFileName;
    }
    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }
    public String getFileContentType() {
        return fileContentType;
    }
    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }
    public File[] getUp() {
        return up;
    }
    public void setUp(File[] up) {
        this.up = up;
    }
    public String[] getUpFileName() {
        return upFileName;
    }
    public void setUpFileName(String[] upFileName) {
        this.upFileName = upFileName;
    }
    public String[] getUpContentType() {
        return upContentType;
    }
    public void setUpContentType(String[] upContentType) {
        this.upContentType = upContentType;
    }
    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCustIDS() {
		return custIDS;
	}
	public void setCustIDS(String custIDS) {
		this.custIDS = custIDS;
	}
	public String getPicType() {
		return picType;
	}
	public void setPicType(String picType) {
		this.picType = picType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
}