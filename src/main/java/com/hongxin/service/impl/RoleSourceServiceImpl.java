package com.hongxin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hongxin.dao.RoleSourceDao;
import com.hongxin.entity.RoleSource;
import com.hongxin.service.RoleSourceService;

@Service("roleSourceService")
public class RoleSourceServiceImpl implements RoleSourceService {

	@Autowired
	private RoleSourceDao roleSourceDao;
	
	public RoleSource get(String id) {
		return roleSourceDao.get(id);
	}

	public List<RoleSource> findAll() {
		return roleSourceDao.findAll();
	}

	public void persist(RoleSource entity) {
		roleSourceDao.persist(entity);
	}

	public String save(RoleSource entity) {
		return roleSourceDao.save(entity);
	}

	public void saveOrUpdate(RoleSource entity) {
		roleSourceDao.saveOrUpdate(entity);
		
	}

	public void delete(String id) {
		roleSourceDao.delete(id);
	}

	public void flush() {
		roleSourceDao.flush();
	}

	public RoleSource load(String id) {
		return null;
	}

	public void deleteByRoleId(String roleId) {
		roleSourceDao.deleteByRoleId(roleId);
	}

	public List<RoleSource> getMyResourceByroleId(String roleId) {
		return roleSourceDao.getMyResourceByroleId(roleId);
	}


}
