package com.jsptintuc.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsptintuc.constant.SystemConstant;
import com.jsptintuc.model.NewModel;
import com.jsptintuc.service.INewService;
import com.jsptintuc.utils.FormUtil;

@WebServlet(urlPatterns = {"/admin-new"})

public class NewController extends HttpServlet {
	
	@Inject
	private INewService newService;

//	public NewController() {
//		newService = new NewService();
//	}
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		NewModel model = FormUtil.toModel(NewModel.class, request);
		
//		String pageStr = request.getParameter("page");
//		String maxPageItem = request.getParameter("maxPageItem");
//		
//		if(pageStr != null) {
//			model.setPage(Integer.parseInt(pageStr));
//		}
//		else {
//			model.setPage(1);
//		}
//		if(maxPageItem != null) {
//			model.setMaxPageItem(Integer.parseInt(maxPageItem));
//		}
		
		Integer offset = (model.getPage() - 1) * model.getMaxPageItem(); // Số hàng dữ liệu trong 1 table --- limit là số item hiển thị
		model.setListResult(newService.findAll(offset, model.getMaxPageItem(), model.getSortName(), model.getSortBy()));
		model.setTotalItem(newService.getTotalItem());
		model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getMaxPageItem()));
		request.setAttribute(SystemConstant.MODEL, model);
		
		RequestDispatcher rd = request.getRequestDispatcher("views/admin/news/list.jsp");
		rd.forward(request, response);
	
	}

}