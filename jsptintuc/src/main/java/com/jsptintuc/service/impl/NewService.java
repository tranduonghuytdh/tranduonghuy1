package com.jsptintuc.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.jsptintuc.dao.INewDao;
import com.jsptintuc.model.NewModel;
import com.jsptintuc.service.INewService;

public class NewService implements INewService {

	@Inject
	private INewDao newDao;
	
//	public NewService() {
//		newDao = new NewDao();
//	}
	
	@Override
	public List<NewModel> findAll(Integer offset, Integer limit, String sortName, String sortBy) {
		return newDao.findAll(offset,limit,sortName, sortBy);
	}
	
	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		return newDao.findByCategoryId(categoryId);
	}

	// Thuần
	@Override
	public NewModel Them(NewModel insertnew) {
		Long newid = newDao.save(insertnew);
		return null;
	}

	@Override
	public NewModel Sua(NewModel updatenew) {
		newDao.update(updatenew);
		return null;
	}

	@Override
	public NewModel Xoa(NewModel deletenew) {
		newDao.delete(deletenew);
		return null;
	}

	// Hàm Chung
	@Override
	public NewModel Them1(NewModel insertnew1) {
		insertnew1.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		insertnew1.setCreatedBy("");
		Long newid = newDao.insert1(insertnew1);
		return newDao.findOne(newid);
	}

	@Override
	public NewModel Sua1(NewModel updatenew1) {
		// Update bài viết cũ
		NewModel oldNew = newDao.findOne(updatenew1.getId());
		updatenew1.setCreatedDate(oldNew.getCreatedDate());
		updatenew1.setCreatedBy(oldNew.getCreatedBy());
		updatenew1.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		updatenew1.setModifiedBy("");
		newDao.update1(updatenew1);
		return newDao.findOne(updatenew1.getId());
	}
	
	@Override
	public void Xoa1(long id) {
		newDao.delete(id);
	}

	// API
	@Override
	public void Xoa(long[] ids) {
		for(long id: ids) {
			newDao.delete1(id);
		}
	}

	@Override
	public int getTotalItem() {
		return newDao.getTotalItem();
	}

}
