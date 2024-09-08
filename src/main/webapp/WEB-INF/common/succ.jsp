<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page isELIgnored="false"%>

<%
	String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
<meta http-equiv="description" content="This is my page" />
<script src="<%=path%>/resource/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
<link rel="stylesheet" href="<%=path%>/resource/bootstrap3/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="<%=path%>/resource/bootstrap3/css/bootstrap-theme.min.css" />
<script src="<%=path%>/resource/bootstrap3/js/jquery-1.11.2.min.js"></script>
<script src="<%=path%>/resource/bootstrap3/js/bootstrap.min.js"></script>

</head>

<body>
	<div class="container">
		<div class="row">
			<div class="col-ms-12">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">信息提示</h3>
					</div>
					<div class="panel-body">
						<pre>
							<font color="red">${msg}</font>
						</pre>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
