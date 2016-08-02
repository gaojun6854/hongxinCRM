package com.hongxin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hongxin.dao.ActionFunDao;
import com.hongxin.entity.ActionFun;
import com.hongxin.service.ActionFunService;

@Service("actionFunService")
public class ActionFunServiceImpl implements ActionFunService {

	@Autowired
	private ActionFunDao actionFunDao;
	
	public ActionFun get(String id) {
		return actionFunDao.get(id);
	}

	public List<ActionFun> findAll() {
		return actionFunDao.findAll();
	}

	public void persist(ActionFun entity) {
		actionFunDao.persist(entity);
	}

	public String save(ActionFun entity) {
		return actionFunDao.save(entity);
	}

	public void saveOrUpdate(ActionFun entity) {
		actionFunDao.saveOrUpdate(entity);
		
	}

	public void delete(String id) {
		actionFunDao.delete(id);
	}

	public void flush() {
		actionFunDao.flush();
	}

	public ActionFun load(String id) {
		return null;
	}


}
