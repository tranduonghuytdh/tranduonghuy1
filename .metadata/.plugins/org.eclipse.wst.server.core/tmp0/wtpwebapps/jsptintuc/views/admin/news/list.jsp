<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh Sách Bài Viết</title>
</head>
<body>
	<div class="main-content">
	<form action="<c:url value='/admin-new'/>" id="formSubmit" method="get">
	    <div class="main-content-inner">
	        <div class="breadcrumbs ace-save-state" id="breadcrumbs">
	            <ul class="breadcrumb">
	                <li>
	                    <i class="ace-icon fa fa-home home-icon"></i>
	                    <a href="#">Trang chủ</a>
	                </li>
	            </ul>
	        </div>
	        <div class="page-content">
	            <div class="row" >
	                <div class="col-xs-12">
	                	 <div class="row" >
	                		<div class="col-xs-12">
	                			<table class="table table-bordered">
									  <thead>
									    <tr>
									      <th>Id</th>
									      <th>Tên Bài Viết</th>
									      <th>Mô Tả Ngắn</th>
									      <th>Nội Dung</th>
									      <th>Ngày Tạo</th>
									    </tr>
									  </thead>
									  <tbody>
									  		<c:forEach var="item" items="${model.listResult}">
									  			 <tr>
										  			<td>${item.id}</td>
									      			<td>${item.title}</td>
									      			<td>${item.shortDescription}</td>
									      			<td>${item.content}</td>
									      			<td>${item.createdDate}</td>
									    		</tr>
									  		</c:forEach>
									  </tbody>
								</table>
								 <ul class="pagination" id="pagination"></ul>
								 <input type="hidden" value="" id="page" name="page" />
								 <input type="hidden" value="" id="maxPageItem" name="maxPageItem" />
								 <input type="hidden" value="" id="sortName" name="sortName" />
								 <input type="hidden" value="" id="sortBy" name="sortBy" />
	                		</div>
	            		 </div>
	                </div>
	            </div>
	        </div>
	    </div>
	    </form>
	</div>
	
	<script>
		var totalPages = ${model.totalPage};
		var currentPage = ${model.page}
		var limit = 2; // ví dụ : Hiển thị 2 hàng dữ liệu trong 1 ô
	    $(function () {
	        window.pagObj = $('#pagination').twbsPagination({
	            totalPages: totalPages,			// Tổng số ô
	            visiblePages: 10,				// Hiển thị bao nhiêu ô trong tổng số ô
	            startPage: currentPage,			// Ô đang được chọn
	            onPageClick: function (event, page) {
	            	if(currentPage != page) {
	            		$('#maxPageItem').val(limit); // Hiển thị bao nhiêu hàng dữ liệu trong 1 ô
		            	$('#page').val(page);
		            	$('#sortName').val('title');
		            	$('#sortBy').val('desc');
		            	$('#formSubmit').submit(); // Khi click từng ô thì submit lại bảng
	            	}
	            }
	        });
	    });
	</script>

</body>
</html>