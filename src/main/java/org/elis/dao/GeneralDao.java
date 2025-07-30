package org.elis.dao;

import java.util.List;

public interface GeneralDao<T> {
	
	void insert(T t) throws Exception;
	void delete(T t) throws Exception;
	T getById(Long id) throws Exception;
	List<T> getAll() throws Exception;
	
}
