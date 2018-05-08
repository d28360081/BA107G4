<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- p189~ -->
<%@ page import="com.pmt.model.*"%>
<%@ page import="com.pmt_det.model.*" %>

<jsp:useBean id="pmtDetSvc" class="com.pmt_det.model.PmtDetService"/>
<jsp:useBean id="productSvc" class="com.Product.model.ProductService"/>

<!DOCTYPE html>
<html>
<head>
</head>
<body>
<%-- 修改商品區域 --%>
	<table style="width:100%">
		<tr style="height:50px;"></tr>
		<tr>
			<th colspan="3" style="text-align:center;border:2px solid black;"><h3><b>已選商品區</b></h3></th>
		</tr>		
		<tr id="oriITs">
			<td class="sortgroup">	
				<c:if test="${pmtVO.pmt_id != null}">
				<c:forEach var="pmtDetVO" items="${pmtDetSvc.getByPMT_ID(pmtVO.pmt_id)}" varStatus="status">
						<div style="text-align:center; width:200px;height:200px;display:inline-block; " class="source_it_id">
							<img src="<%=request.getContextPath()%>/DBGifReader?table=product&it_id=${pmtDetVO.it_id}"	width="200px" height="200px">
							<input type="hidden" name="it_id" value="${pmtDetVO.it_id}">
						</div>	
				</c:forEach>
				</c:if>		
				<div style="min-height:120px;display:inline-block;"></div>
			</td>
		</tr>
	</table>
</body>
</html>