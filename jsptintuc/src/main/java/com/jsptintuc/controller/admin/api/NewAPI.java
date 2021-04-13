package com.jsptintuc.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsptintuc.model.NewModel;
import com.jsptintuc.service.INewService;
import com.jsptintuc.utils.HttpUtil;

@WebServlet(urlPatterns = {"/api-admin-new"})

public class NewAPI extends HttpServlet {
	
	@Inject
	private INewService newService;
	
//	public NewAPI() {
//		newService = new NewService();
//	}
	
	private static final long serialVersionUID = 1071555840997978259L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Client gửi tới Service là tiếng việt
		request.setCharacterEncoding("UTF-8");
		// Service gửi về Client là kdl json
		response.setContentType("application/json");
		NewModel insertModel = HttpUtil.of(request.getReader()).toModel(NewModel.class);
		// Lưu lên databse
		insertModel = newService.Them1(insertModel);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), insertModel);
	}
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Client gửi tới Service là tiếng việt
		request.setCharacterEncoding("UTF-8");
		// Service gửi về Client là kdl json
		response.setContentType("application/json");
		NewModel updateModel = HttpUtil.of(request.getReader()).toModel(NewModel.class);
		// Lưu lên databse
		updateModel = newService.Sua1(updateModel);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), updateModel);
	}
	
protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Client gửi tới Service là tiếng việt
		request.setCharacterEncoding("UTF-8");
		// Service gửi về Client là kdl json
		response.setContentType("application/json");
		NewModel deleteModel = HttpUtil.of(request.getReader()).toModel(NewModel.class);
		// Lưu lên databse
		newService.Xoa(deleteModel.getIds());
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(response.getOutputStream(), "{}");
	}

}
