<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- p189~ -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%@ page import="com.pmt.model.*" %>
<%	PmtService pmtSvc = new PmtService();
	List<PmtVO> list = pmtSvc.getAll();
	pageContext.setAttribute("list",list);%>
<jsp:useBean id="pmtDetSvc" class="com.pmt_det.model.PmtDetService" scope="page"/>
<html>
<head>
<jsp:include page="/com.HeaderFooter/BackEndHeader.jsp"/>
<style>

.myTable {
	width: 1000px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	border: 1px solid #CCCCFF;
	font-size: 16px;		
}

.myTable th,
.myTable td{
	font-weight: 100;
    padding: 5px;
    text-align: center;
    border: 1px solid #CCCCFF;
    text-align:center;
}
</style>
</head>
<body>

	<table style="width:100%"  class="myTable">	
		<tr>
			<th width="80px">促銷編號</th>
			<th width="80px">員工編號</th>
			<th width="100px">促銷名稱</th>
			<th width="250px">方案說明</th>
			<th width="100px">開始</th>
			<th width="100px">結束</th>
			<th width="70px">狀態</th>
			<th width="75px">促銷折扣</th>
			<th width="200px" >方案圖片</th>		
			<th width="150px">商品</th>
			<th width="50px"></th>		
		</tr>
 		<%@ include file="page1.file" %> 
		<c:forEach var="pmtVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" >
		<tr>
			<th>${pmtVO.pmt_id}</th>
			<th>${pmtVO.emp_no}</th>
			<th>${pmtVO.pmt_name}</th>
			<th>${pmtVO.pmt_intro}</th>
			<th><fmt:formatDate value="${pmtVO.pmt_s_date}" pattern="yyyy-MM-dd HH:mm"/></th>
			<th><fmt:formatDate value="${pmtVO.pmt_e_date}" pattern="yyyy-MM-dd HH:mm"/></th>
			<th>${pmtVO.pmt_sts}</th>
			<th>${pmtVO.pmt_discount}</th>			
			<th><img src="<%=request.getContextPath()%>/DBGifReader?table=promotion&pmt_id=${pmtVO.pmt_id}"  width="320" height="80"></th>
			<th>
				<c:forEach var="pmtDetVO" items="${pmtDetSvc.getByPMT_ID(pmtVO.pmt_id)}">
					
					<img src="<%=request.getContextPath()%>/DBGifReader?table=product&it_id=${pmtDetVO.it_id}"	width="50px" height="50px">
				</c:forEach>
			</th>		
			<th>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/pmt/pmt.do" style="margin-bottom: 0px">
					<input type="submit" value="修改">
					<input type="hidden" name="pmt_id"  value="${pmtVO.pmt_id}">
					<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
					<input type="hidden" name="whichPage"	value="<%=whichPage%>">
					<input type="hidden" name="action"	value="getOne_For_Update">
				</FORM>
			</th>
		</tr>
		</c:forEach>
		</table>		
		<%@ include file="page2.file" %>
</body>	
</html>