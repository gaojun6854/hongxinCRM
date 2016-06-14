package com.hongxin.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hongxin.dao.ProductDao;
import com.hongxin.entity.TProductInfo;
import com.hongxin.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	public TProductInfo load(String id) {
		return productDao.load(id);
	}

	public TProductInfo get(String id) {
		return productDao.get(id);
	}

	public List<TProductInfo> findAll() {
		return productDao.findAll();
		
	}

	public void persist(TProductInfo entity) {
		productDao.persist(entity);
	}

	public String save(TProductInfo entity) {
		return productDao.save(entity);
	}

	public int saveOrUpdateByEntity(TProductInfo entity) {
		return productDao.saveOrUpdateByEntity(entity);
		
	}

	public int deleteById(String id) {
		return productDao.deleteById(id);
	}

	public void flush() {
		productDao.flush();
	}

	public void saveOrUpdate(TProductInfo entity) {
		productDao.flush();
	}

	public void delete(String id) {
		productDao.delete(id);
	}

	public TProductInfo getStrId(String id) {
		return productDao.getStrId(id);
	}

}
