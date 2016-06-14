package com.hongxin.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hongxin.dao.MenuDao;
import com.hongxin.entity.ResourceBak;
import com.hongxin.service.MenuService;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuDao menuDao;

	public ResourceBak load(Integer id) {
		return null;
	}

	public ResourceBak get(Integer id) {
		return menuDao.get(id);
	}

	public List<ResourceBak> findAll() {
		List<ResourceBak> resourceBakList=menuDao.findAll();
		for (ResourceBak resourceBak : resourceBakList) {
			List<ResourceBak> menuChilds=new ArrayList<ResourceBak>();
			for (ResourceBak resourceBak1 : resourceBak.getResourceBaks()) {
				menuChilds.add(resourceBak1);//加个排序
			}
			//////////////////////////////////////////////
			//插入排序  对下层子菜单进行排序
			ResourceBak key;
			int i;
			int j;
			for (j = 1; j < menuChilds.size(); j++) {
				key=menuChilds.get(j);
				i=j-1;
				while (i>=0 && Integer.parseInt(menuChilds.get(i).getSeq())>Integer.parseInt(key.getSeq())) {
					menuChilds.set(i+1, menuChilds.get(i));
					i--;
				}
				menuChilds.set(i+1, key);
			}
			////////////////////////////////////////////排序结束
			
			resourceBak.setChildMenus(menuChilds);//添加到每个主菜单下作为子菜单的成员
		}
		List<ResourceBak> menus=new ArrayList<ResourceBak>();
		for (ResourceBak resourceBak : resourceBakList) {
			if (resourceBak.getParentSourceId()==null) {
				menus.add(resourceBak);
			}
		}
		return menus;
	}

	public void persist(ResourceBak entity) {
		menuDao.persist(entity);
	}

	public Integer save(ResourceBak entity) {
		return menuDao.save(entity);
	}

	public void saveOrUpdate(ResourceBak entity) {
		menuDao.save(entity);
		
	}

	public void delete(Integer id) {
		menuDao.delete(id);
	}

	public void flush() {
		menuDao.flush();
	}

}
