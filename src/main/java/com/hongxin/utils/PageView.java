package com.hongxin.utils;
import java.util.List;

/**
 *  在Action里的调用方法
  	//这里必须要构造新对象，不然刚打开没有currentPage参数传递过来，如果不新建也行，第一次打开必须传递currentPage参数过来
	private PageView<T>pageView=new PageView<T>();	
	
	public PageView<T> getPageView() {
		return pageView;
	}

	public void setPageView(PageView<T> pageView) {
		this.pageView = pageView;
	}
  	int maxresult=1;				
 	int firstindex=(pageView.getCurrentPage()-1)*maxresult;
 	QueryResult<T> Service.getScrollData(firstindex,maxresult, null, null, null);
 	pageView.setQueryResult(maxresult,qr);
 	request.put("pageView", pageView);
 */

public class PageView<T> {
	/** 分页数据 **/
	private List<T> records;

	/** 页码开始索引 ,例如显示第1 2 3 4 5 6 7 8 9 ，开始索引为1 **/
	private long startIndex;

	/** 页码结束索引 ,例如显示第1 2 3 4 5 6 7 8 9 ，结束索引为9 **/
	private long endIndex;

	/** 总页数 ，没有0页，所以设置默认值为1 **/
	private long totalPage = 1;

	/** 每页显示记录数 **/
	private int maxResult = 10;

	/** 当前页 **/
	private int currentPage = 1;

	/** 总记录数 **/
	private long totalRecord;

	/** 工具条上显示的页码数量 **/
	private int pageBarSize = 8;

	
	// 这只方法触发记录查询结果和总条数
	public void setQueryResult(int maxResult,QueryResult<T> qr) {
		this.maxResult = maxResult;
		this.records = qr.getResultlist();
		this.totalRecord = qr.getTotalrecord();
		this.totalPage = this.totalRecord % this.maxResult == 0 ? this.totalRecord/ this.maxResult : this.totalRecord / this.maxResult + 1;
		
		/*****************************************************/
		this.startIndex = currentPage - (pageBarSize % 2 == 0 ? pageBarSize / 2 - 1 : pageBarSize / 2);
		this.endIndex = currentPage + pageBarSize / 2;

		if (startIndex < 1) {
			startIndex = 1;
			if (totalPage >= pageBarSize)
				endIndex = pageBarSize;
			else
				endIndex = totalPage;
		}
		if (endIndex > totalPage) {
			endIndex = totalPage;
			if ((endIndex - pageBarSize) > 0)
				startIndex = endIndex - pageBarSize +1;	//最后一页显示多小条页
			else
				startIndex = 1;
		}
	}

	public List<T> getRecords() {
		return records;
	}

	public void setRecords(List<T> records) {
		this.records = records;
	}

	public long getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(long startIndex) {
		this.startIndex = startIndex;
	}

	public long getEndIndex() {
		return endIndex;
	}

	public void setEndIndex(long endIndex) {
		this.endIndex = endIndex;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage<1?1:currentPage;	//如果当前页为0，则显示第一页
	}

	public long getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getPageBarSize() {
		return pageBarSize;
	}

	public void setPageBarSize(int pageBarSize) {
		this.pageBarSize = pageBarSize;
	}
}
