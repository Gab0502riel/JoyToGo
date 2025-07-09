package org.elis.dao;

import java.util.List;

public interface GeneralDao<T> {
	
	T getById(int id) throws Exception;
	List<T> getAll() throws Exception;
	
}
