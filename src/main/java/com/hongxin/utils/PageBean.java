package com.hongxin.utils;

import java.util.List;
@SuppressWarnings("unused")
public class PageBean {  
    
    /*==============================================================*/  
    private List resultList;//结果集  
    private int recordSUM;//总记录数  
    private int pageSUM;//总页数  
    private int currentPage;//当前页  
    private int pageSize;//页记录数  
	private boolean isFirstPage;//是否是第一页     
    private boolean isLastPage;//是否是最后一页  
    private boolean hasPreviousPage;//是否有上一页  
    private boolean hasNextPage;//是否有下一页  
    /*===========================初始化===================================*/  
    public void init(){   
        this.isFirstPage = isFirstPage();   
        this.isLastPage = isLastPage();   
        this.hasPreviousPage = isHasPreviousPage();   
        this.hasNextPage = isHasNextPage();   
    }   
    /*=============================总页数=================================*/  
    public static int countTotalPage(int pageSize,int recordSUM){   
          
        int totalPage = recordSUM % pageSize == 0 ? recordSUM/pageSize : recordSUM/pageSize+1;   
          
        return totalPage;   
    }   
    /*============================当前页开始记录号 ==================================*/  
    public static int countOffset(final int pageSize,final int currentPage){   
          
        final int offset = pageSize*(currentPage-1);   
          
        return offset;   
    }   
    /*=============================当期页=================================*/  
    public static int countCurrentPage(int page){   
          
        final int curPage = (page==0?1:page);   
          
        return curPage;   
    }   
    /*============================判断当前页的状态==================================*/  
    public boolean isFirstPage() {   
        return currentPage == 1;      
    }   
    public boolean isLastPage() {   
        return currentPage == pageSUM;      
    }   
    public boolean isHasPreviousPage() {   
        return currentPage != 1;           
    }   
    public boolean isHasNextPage() {   
        return currentPage != pageSUM;      
    }   
    /*===========================Get/Set方法===================================*/  
    public List getResultList() {  
        return resultList;  
    }  
    public void setResultList(List resultList) {  
        this.resultList = resultList;  
    }  
    public int getRecordSUM() {  
        return recordSUM;  
    }  
    public void setRecordSUM(int recordSUM) {  
        this.recordSUM = recordSUM;  
    }  
    public int getPageSUM() {  
        return pageSUM;  
    }  
    public void setPageSUM(int pageSUM) {  
        this.pageSUM = pageSUM;  
    }  
    public int getCurrentPage() {  
        return currentPage;  
    }  
    public void setCurrentPage(int currentPage) {  
        this.currentPage = currentPage;  
    }  
    public int getPageSize() {  
        return pageSize;  
    }  
    public void setPageSize(int pageSize) {  
        this.pageSize = pageSize;  
    }  
    /*==============================================================*/  
       
}  