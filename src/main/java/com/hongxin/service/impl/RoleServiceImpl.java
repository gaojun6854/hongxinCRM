package com.hongxin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongxin.dao.RoleDao;
import com.hongxin.entity.Role;
import com.hongxin.service.RoleService;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;
	
	public Role get(String id) {
		return roleDao.get(id);
	}

	public List<Role> findAll() {
		return roleDao.findAll();
	}

	public void persist(Role entity) {
		roleDao.persist(entity);
	}

	public String save(Role entity) {
		return roleDao.save(entity);
	}

	public void saveOrUpdate(Role entity) {
		roleDao.saveOrUpdate(entity);
		
	}

	public void delete(String id) {
		roleDao.delete(id);
	}

	public void flush() {
		roleDao.flush();
	}

	public Role load(String id) {
		return null;
	}

	public List<Role> getByRoleId(String roleId) {
		return roleDao.getByRoleId(roleId);
	}

	public int selectByName(String roleName) {
		return roleDao.selectByName(roleName);
	}

}
