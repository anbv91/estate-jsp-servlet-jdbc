<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>


<link
	href="<c:url value='/template/admin/assets/css/bootstrap.min.css'/>"
	rel="stylesheet" type="text/css">
<link
	href="<c:url value='/template/admin/assets/font-awesome/4.5.0/css/font-awesome.min.css' />"
	rel="stylesheet" type="text/css">

<link
	href="<c:url value='/template/admin/assets/css/fonts.googleapis.com.css' />"
	rel="stylesheet" type="text/css">



<link href="<c:url  value='/template/admin/assets/css/ace.min.css'/>"
	class="ace-main-stylesheet" id="main-ace-style" rel="stylesheet"
	type="text/css">

<link
	href="<c:url value='/template/admin/assets/css/ace-skins.min.css' />"
	rel="stylesheet" type="text/css">
<link href="<c:url value='/template/admin/assets/css/ace-rtl.min.css'/>"
	rel="stylesheet" type="text/css">



</head>
<body id="page-top">

	<!--%@ include file="/common/admin/header.jsp"%-->

	<dec:body />

	<script
		src="<c:url value='/template/admin/assets/js/jquery-2.1.4.min.js'/>"></script>

	<script
		src="<c:url value='/template/admin/assets/js/ace-extra.min.js' />">
		
	</script>

	<script type="text/javascript">
		if ('ontouchstart' in document.documentElement)
			document
					.write("<script src='template/admin/assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"/script>");
	</script>
	<script
		src="<c:url value='/template/admin/assets/js/bootstrap.min.js'/>"></script>

	<script
		src="<c:url value='/template/admin/assets/js/jquery-ui.custom.min.js'/>">
		
	</script>
	<script
		src="<c:url value='/template/admin/assets/js/jquery.ui.touch-punch.min.js'/>"></script>
	<script
		src="<c:url value='/template/admin/assets/js/jquery.easypiechart.min.js'/>"></script>
	<script
		src="<c:url value='/template/admin/assets/js/jquery.sparkline.index.min.js'/>"></script>

	<script
		src="<c:url value='/template/admin/assets/js/ace-elements.min.js'/>"></script>
	<script src="<c:url value='/template/admin/assets/js/ace.min.js'/>"></script>

</body>
</html>