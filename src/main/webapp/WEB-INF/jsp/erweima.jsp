<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link href="${pageContext.request.contextPath }/statics/css/bootstrap.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/js/html2canvas.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/js/html2canvas.min.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/js/qrcode.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/statics/js/qrcode.min.js"></script>
 <script type="text/javascript" src="${pageContext.request.contextPath }/statics/js/jquery.min.js"></script>
<style>
.black_overlay {
	display: none;
	position: absolute;
	top: 0%;
	left: 0%;
	width: 100%;
	height: 100%;
	z-index: 1001;
	-moz-opacity: 0.8;
	opacity: .80;
	filter: alpha(opacity = 88);
}

.white_content {
	display: none;
	position: absolute;
	top: 5%;
	left: 5%;
	width: 90%;
	height: 50%;
	padding: 20px;
	z-index: 1002;
	overflow: auto;
}

a {
	text-align: center;
	margin-left: 30px;
}

a {
	text-deco\1ration: none;
}

#close a {
	padding-left: 35px;
	lin-height: 15px;
}

#close {
	margin-top: 5px;
}
</style>
</head>
<body>
	<div id="light" class="white_content">
		<div id='qrcode'></div>
	</div>
	</div>
	<div id="fade" class="black_overlay"></div>
	<script type="text/javascript">
		$(document).ready(function() {
			document.getElementById('light').style.display = 'block';
			document.getElementById('fade').style.display = 'block'; 
			createQrcode();
		});
		
		var qrcode = new QRCode('qrcode', {
			width : 150,
			height : 150,
		});
		function createQrcode() {
			var id=1;
			var QRCodeStr = 'http://62.234.188.11:8080/AppInfoSystem/register.jsp&id='+id;/* 这里写二维码的地址  可以拼接   在线生成二维码   */
			qrcode.makeCode(QRCodeStr);
		}
	</script>
</body>
</html>