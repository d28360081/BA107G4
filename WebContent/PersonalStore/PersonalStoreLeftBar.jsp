<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<%@page import="com.PersonalStore.model.*"%>
<%@page import="com.member.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <style type="text/css">
		.photo{
        	width: 210px;
        	height: 200px;
        	border-radius: 50%;
			overflow: hidden;
        	
        }
        .photo	img{
        	width:100%;
            height:100%;
        }
        </style>
        
</head>

<%String RealactiveOrderList=(String)request.getAttribute("activeOrderList");
request.setAttribute("RealactiveOrderList", RealactiveOrderList);






String RealactiveAddProduct=(String)request.getAttribute("activeAddProduct");
request.setAttribute("RealactiveAddProduct", RealactiveAddProduct);


String RealactiveAddStore=(String)request.getAttribute("activeAddStore");
request.setAttribute("RealactiveAddStore", RealactiveAddStore);

String RealactiveOnProduct=(String)request.getAttribute("activeOnProduct");
request.setAttribute("RealactiveOnProduct", RealactiveOnProduct);

String RealactiveOffProduct=(String)request.getAttribute("activeOffProduct");
request.setAttribute("RealactiveOffProduct", RealactiveOffProduct);


String RealactiveEditStore=(String)request.getAttribute("activeEditStore");
request.setAttribute("RealactiveEditStore", RealactiveEditStore);



PersonalStoreVO personalStore=(PersonalStoreVO) request.getAttribute("personalStore");



MemVO memVO = (MemVO) session.getAttribute("MemVO");
request.setAttribute("memVO", memVO);
String mem_id=memVO.getMem_id();
request.setAttribute("mem_id", mem_id);

PersonalStoreVO MempersonalStore2=new PersonalStoreVO();
PersonalStoreService  personalStoreService2=new PersonalStoreService();
MempersonalStore2=personalStoreService2.findByMemId(mem_id);
request.setAttribute("MempersonalStore2", MempersonalStore2);

%>

<body>
<div class="row">
				<div class="col-xs-12 col-sm-3">
					<div class="media">
						<div class="media-left media-bottom">
							<a href="#"> <img src="<%=request.getContextPath()%>/member/MemPhoto?acc=<%=memVO.getAcc()%>" class="photo">
							</a>
						</div>
					</div>					
					<div class="list-group">
				<c:if test="${MempersonalStore2 ne null}">	
					<a href="${pageContext.request.contextPath}/OrderListFront/OrderListPersonalStore.jsp" class="list-group-item ${(RealactiveOrderList=='activeOrderList')?'active':''}">訂單管理</a>
					
						<a href="${pageContext.request.contextPath}/PersonalStore/PersonalStore.jsp" class="list-group-item ${(RealactiveAddProduct=='activeAddProduct')?'active':''}">新增商品</a>
			
			
			
						<a href="${pageContext.request.contextPath}/PersonalStore/PersonalStoreProductSTS_ON.jsp" class="list-group-item ${(RealactiveOnProduct=='activeOnProduct')?'active':''}">商品管理</a>
					
											
							<a href="${pageContext.request.contextPath}/PersonalStore/PersonalStoreEdit.jsp" class="list-group-item ${(RealactiveEditStore=='activeEditStore')?'active':''}">商店資料更改</a>
					</c:if>						
							<c:if test="${MempersonalStore2 eq null}">	
							<a href="${pageContext.request.contextPath}/PersonalStore/AddPersonalStore.jsp" class="list-group-item ${(RealactiveAddStore=='activeAddStore')?'active':''}">							
							新增商店							
							</a>	
							</c:if>		

							
					</div>

				</div>
</body>
</html>