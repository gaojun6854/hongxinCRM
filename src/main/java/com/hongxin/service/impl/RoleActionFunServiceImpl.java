package com.hongxin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hongxin.dao.RoleActionFunDao;
import com.hongxin.entity.RoleActionFun;
import com.hongxin.service.RoleActionFunService;

@Service("roleActionFunService")
public class RoleActionFunServiceImpl implements RoleActionFunService {

	@Autowired
	private RoleActionFunDao roleActionFunDao;
	
	public RoleActionFun get(String id) {
		return roleActionFunDao.get(id);
	}

	public List<RoleActionFun> findAll() {
		return roleActionFunDao.findAll();
	}

	public void persist(RoleActionFun entity) {
		roleActionFunDao.persist(entity);
	}

	public String save(RoleActionFun entity) {
		return roleActionFunDao.save(entity);
	}

	public void saveOrUpdate(RoleActionFun entity) {
		roleActionFunDao.saveOrUpdate(entity);
		
	}

	public void delete(String id) {
		roleActionFunDao.delete(id);
	}

	public void flush() {
		roleActionFunDao.flush();
	}

	public RoleActionFun load(String id) {
		return null;
	}

	public List<RoleActionFun> selectByRoleId(String roleId) {
		return roleActionFunDao.selectByRoleId(roleId);
	}

	public void deleteByRoleId(String roleId) {
		roleActionFunDao.deleteByRoleId(roleId);
	}

}
