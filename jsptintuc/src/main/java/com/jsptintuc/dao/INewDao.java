package com.jsptintuc.dao;

import java.util.List;

import com.jsptintuc.model.NewModel;

public interface INewDao extends GenericDao<NewModel> {

	List<NewModel> findAll(Integer offset, Integer limit, String sortName, String sortBy);
	
	List<NewModel> findByCategoryId(Long categoryId);
	
	// Thuần
	Long save(NewModel insertNEW);
	
	void update(NewModel updateNEW);
	
	void delete(NewModel deleteNEW);
	
	// Hàm Chung
	Long insert1(NewModel insertNew1);
	
	void update1(NewModel updateNew1);
	
	void delete1(long id);
	
	// Trả về 1 cái model --- API
	NewModel findOne(long id);
	void delete(long id);
	
	// Đếm có bao nhiêu hàng trong database (item)
	int getTotalItem();
	
}
