package com.hongxin.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import com.hongxin.entity.CheckReceipts;
import com.hongxin.service.CheckReceiptsService;
import com.opensymphony.xwork2.ActionContext;

public class UploadCommon {
	
	/**
     * 普通批量上传文件方法
     * 
     * @param fa
     *            文件数组
     * @param fna
     *            文件名称数组
     * @return 上传文件的路径字符串
     */
    public String commUpload(File[] fa, String[] fna) {
        String paths = "";
        ActionContext ac = ActionContext.getContext();
        ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
        String rp = sc.getRealPath("/"), folder = "", newNm = "", ext = "";
        // String rp = "/data/web/static/", folder = "", newName = "";
        File dir = null, destFile = null;
        InputStream is = null;
        OutputStream os = null;
        for(int i = 0; i < fa.length; ++i) {
            ext = fna[i].substring(fna[i].lastIndexOf(".")).toLowerCase();
            if(".jpg.png.bmp.gif".indexOf(ext) != -1) {
                folder = newPath(1, rp);
            } else if(".flv".equals(ext)) {
                folder = newPath(2, rp);
            } else {
                folder = newPath(3, rp);
            }
            newNm = newName(fna[i]);
            dir = new File(folder);
            if(!dir.exists()) {
                dir.mkdirs();
            }
            destFile = new File(dir, newNm);
            try {
                is = new FileInputStream(fa[i]);
                os = new FileOutputStream(destFile);
                byte[] buffer = new byte[400];
                int length = 0;
                while((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                paths += dir + newNm +",";
            } catch(FileNotFoundException e) {
                e.printStackTrace();
            } catch(IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                    os.close();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        }
        paths = paths.substring(0, paths.length()-1);
        return paths;
    }
    /**
     * 单个上传文件方法
     * 
     * @param fa
     *            文件
     * @param fna
     *            文件名称
     * @param checkReceiptsService 
     * @return 上传文件的路径
     * @throws Exception 
     */
    public String singleUpload(File fa, String fna,String CPID, CheckReceiptsService checkReceiptsService) throws Exception {
        String path = "";
        ActionContext ac = ActionContext.getContext();
        ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
        String rp = sc.getRealPath("/"), folder = "", newNm = "", ext = "";
        // String rp = "/data/web/static/", folder = "", newName = "";
        File dir = null, destFile = null;
        InputStream is = null;
        OutputStream os = null;
        ext = fna.substring(fna.lastIndexOf(".")).toLowerCase();
        if(".jpg.png.bmp.gif".indexOf(ext) != -1) {
            folder = newPath(1, rp);
        } else if(".flv".equals(ext)) {
            folder = newPath(2, rp);
        } else {
            folder = newPath(3, rp);
        }
        newNm = newName(fna);
        dir = new File(folder);
        if(!dir.exists()) {
            dir.mkdirs();
        }
        destFile = new File(dir, newNm);
        try {
            is = new FileInputStream(fa);
            os = new FileOutputStream(destFile);
            byte[] buffer = new byte[400];
            int length = 0;
            while((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            path = dir + newNm;
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                os.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        //保存数据---MySQL
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        CheckReceipts checkReceipts=new CheckReceipts();
        checkReceipts.setRecNum(UUID.randomUUID().toString());//ID
        checkReceipts.setRecId(CPID);//编号信息
        checkReceipts.setRecSer(1);
        checkReceipts.setRecDesc("Image");
        checkReceipts.setRecType("3");
        checkReceipts.setRecPath(Constants.FILE_SAVE_ADDRESS);
        checkReceipts.setRecFile(newNm);
        checkReceipts.setRecRealFile("uploads/"+newNm);
        checkReceipts.setCreateDate(sdf.format(new Date()));
        checkReceipts.setCreateTime(Date2String8.time2String());
        try {
        	checkReceiptsService.save4StrId(checkReceipts);
		} catch (Exception e) {
			throw e;
		}
        return path;
    }
    /**
     * 上传文件路径
     * 
     * @param fType
     *            文件类型标识，1为图片，2为视频，3为其它文件
     * @param rp
     *            上传文件路径
     * @return 上传文件的路径
     */
    public String newPath(int fType, String rp) {
       /* SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String dts = sdf.format(new java.util.Date());*/
        switch(fType) {
            case 1 :
                rp += Constants.FILE_SAVE_ADDRESS+"/";
                break;
            case 2 :
                rp += Constants.FILE_SAVE_ADDRESS+"/";
                break;
            case 3 :
                rp += Constants.FILE_SAVE_ADDRESS+"/";
                break;
            default :
                rp += Constants.FILE_SAVE_ADDRESS+"/";
                break;
        }
        return rp;
    }
    /**
     * 重命名上传文件
     * 
     * @param srcName
     *            上传文件的文件名
     * @return 重命名后的文件名
     */
    public String newName(String srcName) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String ext = srcName.substring(srcName.lastIndexOf(".")), dt = sdf.format(new java.util.Date()), rd = Math.round(Math.random() * 900) + 100 + "";
        return dt + rd + ext;
    }
	@SuppressWarnings("unused")
	public String singleUploadChange(File fa, String fna,String CPID,String recNum, CheckReceiptsService checkReceiptsService) throws Exception {
        String path = "";
        ActionContext ac = ActionContext.getContext();
        ServletContext sc = (ServletContext) ac.get(ServletActionContext.SERVLET_CONTEXT);
        String rp = sc.getRealPath("/"), folder = "", newNm = "", ext = "";
        // String rp = "/data/web/static/", folder = "", newName = "";
        File dir = null, destFile = null;
        InputStream is = null;
        OutputStream os = null;
        ext = fna.substring(fna.lastIndexOf(".")).toLowerCase();
        if(".jpg.png.bmp.gif".indexOf(ext) != -1) {
            folder = newPath(1, rp);
        } else if(".flv".equals(ext)) {
            folder = newPath(2, rp);
        } else {
            folder = newPath(3, rp);
        }
        newNm = newName(fna);
        dir = new File(folder);
        if(!dir.exists()) {
            dir.mkdirs();
        }
        destFile = new File(dir, newNm);
        try {
            is = new FileInputStream(fa);
            os = new FileOutputStream(destFile);
            byte[] buffer = new byte[400];
            int length = 0;
            while((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            path = dir + newNm;
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                os.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        //保存数据---MySQL
       
        try {
        	CheckReceipts receipts=checkReceiptsService.get(recNum);
        	receipts.setRecFile(newNm);
        	receipts.setRecPath(Constants.FILE_SAVE_ADDRESS);
        	receipts.setRecRealFile(Constants.FILE_SAVE_ADDRESS+"/"+newNm);
        	checkReceiptsService.saveOrUpdate(receipts);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
        return Constants.FILE_SAVE_ADDRESS+"/"+newNm;
	}
}