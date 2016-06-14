package com.hongxin.action.OA;
/**
 * 图片上传
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import com.hongxin.entity.CheckReceipts;
import com.hongxin.entity.Picture;
import com.hongxin.service.CheckReceiptsService;
import com.hongxin.utils.Constants;
import com.hongxin.utils.Date2String8;
import com.opensymphony.xwork2.ActionSupport;

public class PictureAction extends ActionSupport implements ServletRequestAware{

	private static final long serialVersionUID = -1896915260152387341L;
	@Autowired
	CheckReceiptsService checkReceiptsService;
    private HttpServletRequest request;  
    private List<File> fileName;//这里的"fileName"一定要与表单中的文件域名相同  
    private List<String> fileNameContentType;//格式同上"fileName"+ContentType  
    private List<String> fileNameFileName;//格式同上"fileName"+FileName  
    private String savePath;//文件上传后保存的路径  
    private String custIDS;
    private String picType;
    
    @Override
    public String execute() throws Exception {
        File dir=new File(getSavePath());  
        if(!dir.exists()){  
            dir.mkdirs();  
        }  
        List<File> files=getFileName();  
        for(int i=0;i<files.size();i++){  
        	Picture pic=new Picture();
        	//后缀
        	String fileHZM=fileNameFileName.get(i).substring(fileNameFileName.get(i).lastIndexOf(".") + 1, fileNameFileName.get(i).length());
        	//文件名
        	String picName=UUID.randomUUID().toString()+"."+fileHZM;
        	pic.setId(i);
            pic.setFileName(picName);
            pic.setSaveAddr(Constants.FILE_SAVE_ADDRESS);
        	//文件写入流
        	FileInputStream fis=new FileInputStream(getFileName().get(i));
        	//文件输出流
            FileOutputStream fos=new FileOutputStream(getSavePath()+"//"+picName); //getFileNameFileName().get(i) 
            byte []buffers=new byte[1024];  
            int len=0;  
            while((len=fis.read(buffers))!=-1){  
                fos.write(buffers,0,len);
            }
            fos.flush();
            fos.close();
            fis.close();
            
            CheckReceipts checkReceipts=new CheckReceipts();
			checkReceipts.setRecNum(custIDS);//合同编号
			checkReceipts.setRecId("1");
			checkReceipts.setRecSer(i);//循环picList
			
			if ("1".equals(picType)) {
				checkReceipts.setRecType("1");
				checkReceipts.setRecDesc("签约客户信息图片");
			}else if("2".equals(picType)){
				checkReceipts.setRecType("2");
				checkReceipts.setRecDesc("客户合约信息图片");
			}else{
				checkReceipts.setRecType("3");
				checkReceipts.setRecDesc("线下交易图片");
			}
			checkReceipts.setRecInfo("签约");
			checkReceipts.setRecManDesc("签约");
			checkReceipts.setRecPath(Constants.FILE_SAVE_ADDRESS);
			checkReceipts.setRecFile(picName);
			checkReceipts.setCreateDate(Date2String8.date2String(new Date()));
			checkReceipts.setCreateTime(Date2String8.time2String());
			checkReceiptsService.save4StrId(checkReceipts);
        } 
        return SUCCESS;  
    }  
    public List<File> getFileName() {  
        return fileName;  
    }  
  
    public void setFileName(List<File> fileName) {  
        this.fileName = fileName;  
    }  
  
    public List<String> getFileNameContentType() {  
        return fileNameContentType;  
    }  
  
    public void setFileNameContentType(List<String> fileNameContentType) {  
        this.fileNameContentType = fileNameContentType;  
    }  
  
    public List<String> getFileNameFileName() {  
        return fileNameFileName;  
    }  
  
    public void setFileNameFileName(List<String> fileNameFileName) {  
        this.fileNameFileName = fileNameFileName;  
    }  
  
    @SuppressWarnings("deprecation")  
    public String getSavePath() {  
        return request.getRealPath(savePath);  
    }  
  
    public void setSavePath(String savePath) {  
        this.savePath = savePath;  
    } 
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public void setServletRequest(HttpServletRequest req) {
		this.request=req; 
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
	
}  