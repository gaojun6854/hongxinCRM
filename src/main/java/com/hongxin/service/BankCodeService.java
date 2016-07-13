package com.hongxin.service;

import java.util.List;
import com.hongxin.entity.TBankCode;

public interface BankCodeService 
{
	TBankCode load(String id);

	TBankCode get(String id);

	List<TBankCode> findAll();

	void persist(TBankCode entity);

	String save(TBankCode entity);

	void saveOrUpdate(TBankCode entity);

	void delete(String id);

	void flush();
}
