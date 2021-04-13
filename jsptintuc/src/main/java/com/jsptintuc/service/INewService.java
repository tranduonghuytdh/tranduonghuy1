package com.jsptintuc.service;

import java.util.List;

import com.jsptintuc.model.NewModel;

public interface INewService {

	List<NewModel> findAll(Integer offset, Integer limit, String sortName, String sortBy);
	
	List<NewModel> findByCategoryId(Long categoryId);
	
	// Thuần
	NewModel Them(NewModel insertnew);
	
	NewModel Sua(NewModel updatenew);
	
	NewModel Xoa(NewModel deletenew);
	
	// Hàm Chung
	NewModel Them1(NewModel insertnew1);
	
	NewModel Sua1(NewModel updatenew1);
	
	void Xoa1(long id);
	
	// API
	void Xoa(long[] ids);
	
	// Đếm có bao nhiêu hàng trong database (item)
	int getTotalItem();
	
}
