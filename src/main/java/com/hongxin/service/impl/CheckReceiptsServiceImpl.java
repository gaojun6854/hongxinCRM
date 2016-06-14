package com.hongxin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hongxin.dao.CheckReceiptsDao;
import com.hongxin.entity.CheckReceipts;
import com.hongxin.service.CheckReceiptsService;

@Service("checkReceiptsService")
public class CheckReceiptsServiceImpl implements CheckReceiptsService {

	@Autowired
	private CheckReceiptsDao checkReceiptsDao;
	
	public CheckReceipts get(String id) {
		return checkReceiptsDao.get(id);
	}

	public List<CheckReceipts> findAll() {
		return checkReceiptsDao.findAll();
	}

	public void persist(CheckReceipts entity) {
		checkReceiptsDao.persist(entity);
	}

	public String save(CheckReceipts entity) {
		return checkReceiptsDao.save(entity);
	}

	public void saveOrUpdate(CheckReceipts entity) {
		checkReceiptsDao.saveOrUpdate(entity);
		
	}

	public void delete(String id) {
		checkReceiptsDao.delete(id);
	}

	public void flush() {
		checkReceiptsDao.flush();
	}

	public CheckReceipts load(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public String save4StrId(CheckReceipts checkReceipts) {
		return checkReceiptsDao.save4StrId(checkReceipts);//保存checkReceipts
	}
	/**
	 * 通过主键查询所有的改信息
	 * 结果为List
	 */
	public List<CheckReceipts> getByStrId(String Id) {
		return checkReceiptsDao.getByStrId(Id);//保存checkReceipts
	}

	public List<CheckReceipts> getByStrIdType(String id, String no) {
		return checkReceiptsDao.getByStrIdType(id,no);//保存checkReceipts
	}

	public void deleteByStrId(String recNum) {
		checkReceiptsDao.deleteByStrId(recNum);
	}

}
