package com.hongxin.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongxin.dao.ActionFunDao;
import com.hongxin.dao.MenuDao;
import com.hongxin.entity.ActionFun;
import com.hongxin.entity.ResourceBak;
import com.hongxin.service.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;
	@Autowired
	private ActionFunDao actionFunDao;

	public ResourceBak load(Integer id) {
		return null;
	}

	public ResourceBak get(Integer id) {
		return menuDao.get(id);
	}

	public List<ResourceBak> findAll() {
		
		List<ResourceBak> parentResourceBakList=menuDao.getSubMenuList(null);
		for (ResourceBak resourceBak : parentResourceBakList) {
			//二级菜单
			List<ResourceBak> resourceBakList=menuDao.getSubMenuList(Integer.toString(resourceBak.getSourceId()));
			resourceBak.setChildMenus(resourceBakList);
		}
		return parentResourceBakList;
	}

	public void persist(ResourceBak entity) {
		menuDao.persist(entity);
	}

	public Integer save(ResourceBak entity) {
		return menuDao.save(entity);
	}

	public void saveOrUpdate(ResourceBak entity) {
		menuDao.saveOrUpdate(entity);
		
	}

	public void delete(Integer id) {
		menuDao.delete(id);
	}

	public void flush() {
		menuDao.flush();
	}

	public Integer save4parentSourceId(ResourceBak resourceBak) {
		return menuDao.save4parentSourceId(resourceBak);
		
	}

	public List<ResourceBak> getallMenuList(Integer parentsourceId) {
		return menuDao.getallMenuList(parentsourceId);
	}

	/**
	 * 获取菜单的功能点列表信息
	 */
	public List<ActionFun> getFunctionList(String sourceId) {
		return actionFunDao.getBySourceId(sourceId);
	}

	public List<ResourceBak> getMenuListFunlist() {
		List<ResourceBak> parentResourceBakList=menuDao.getSubMenuList(null);
		for (ResourceBak resourceBak : parentResourceBakList) {
			if(resourceBak!=null && parentResourceBakList.size()>0){
				//一级的功能点
				List<ActionFun> actionOneList = actionFunDao.getFunctionList(resourceBak.getSourceId());
				resourceBak.setFunlist(actionOneList);
			}
			//二级菜单
			List<ResourceBak> resourceBakList=menuDao.getSubMenuList(Integer.toString(resourceBak.getSourceId()));
			resourceBak.setChildMenus(resourceBakList);
			
			for (ResourceBak resourceBak2 : resourceBak.getChildMenus()) {
				if(resourceBak2!=null && resourceBak.getChildMenus().size()>0){
					//二级的功能点
					List<ActionFun> actionOneList = actionFunDao.getFunctionList(resourceBak2.getSourceId());
					resourceBak2.setFunlist(actionOneList);
				}
			}
		}
		return parentResourceBakList;
	}

	public List<ResourceBak> getSubMenuList(String PID) {
		return actionFunDao.getSubMenuList(PID);
	}

}
