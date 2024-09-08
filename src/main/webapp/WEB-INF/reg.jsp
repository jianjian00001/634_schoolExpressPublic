<%@ page language="java" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <meta name="renderer" content="webkit">
    <title>校园快递物流管理系统</title>
    <link rel="stylesheet" href="<%=path %>/css/pintuer.css">
    <link rel="stylesheet" href="<%=path %>/css/admin.css">
    <script src="<%=path %>/js/jquery.js"></script>
    <script src="<%=path %>/js/pintuer.js"></script>

    		<link rel="stylesheet" href="<%=path%>/resource/bootstrap3/css/bootstrap.min.css"/>
		<link rel="stylesheet" href="<%=path%>/resource/bootstrap3/css/bootstrap-theme.min.css"/>
		<link rel="stylesheet" href="<%=path%>/resource/bootstrap3/css/bootstrap-datetimepicker.css" />
		<script src="<%=path%>/resource/bootstrap3/js/jquery-1.11.2.min.js"></script>
		<script src="<%=path%>/resource/bootstrap3/js/bootstrap.min.js"></script>
		<!-- 日期控件 -->
		<script src="<%=path%>/resource/My97DatePicker/WdatePicker.js"></script>
				<script language="javascript">
       function check()
       {
       				          if(document.formAdd.loginname.value=="")
		          {
		             alert("请输入登录名");return false;
		          }
		          if(document.formAdd.pwd.value=="")
		          {
		             alert("请输入密码");return false;
		          }
		          if(document.formAdd.realname.value=="")
		          {
		             alert("请输入真实姓名");return false;
		          }
		          if(document.formAdd.address.value=="")
		          {
		             alert("请输入地址");return false;
		          }
		          if(document.formAdd.sex.value=="")
		          {
		             alert("请输入性别");return false;
		          }
		          if(document.formAdd.tel.value=="")
		          {
		             alert("请输入电话");return false;
		          }
		          if(document.formAdd.age.value=="")
		          {
		             alert("请输入年龄");return false;
		          }
		          if(document.formAdd.email.value=="")
		          {
		             alert("请输入邮箱");return false;
		          }

          return true;
       }
    </script>
</head>
<body>
<div class="bg"></div>
<div class="container">
    <div class="line bouncein">
        <div class="x6 x2-move">
        <div style="height:100px;"></div>
            <div class="media media-y margin-big-bottom">
            </div>
            <div class="panel loginbox">
                <div class="text-center margin-big padding-big-top"><h1>SSM校园快递物流管理系统</h1></div>
                <div class="panel-body" style="padding:30px; padding-bottom:10px; padding-top:10px;">
                <form action="<%=path %>/user/reg.action" name="formAdd" method="post">
				  <table class="table table-hover  table-bordered table-striped">
						<tr>
							<th colspan="4">&nbsp;添加客户&nbsp;</td>
						</tr>
						    <tr>
							    <th width="50%">
							         登录名
							    </th>
							    <td width="50%">
							        	<input type="text" id="loginname" name="loginname" />
							    </td>
								</tr>
						    <tr>
							    <th width="50%">
							         密码
							    </th>
							    <td width="50%">
							        	<input type="text" id="pwd" name="pwd" />
							    </td>
								</tr>
						    <tr>
							    <th width="50%">
							         真实姓名
							    </th>
							    <td width="50%">
							        	<input type="text" id="realname" name="realname" />
							    </td>
								</tr>
						    <tr>
							    <th width="50%">
							         地址
							    </th>
							    <td width="50%">
							        	<input type="text" id="address" name="address" />
							    </td>
								</tr>
						    <tr>
							    <th width="50%">
							         性别
							    </th>
							    <td width="50%">
							        	<input type="text" id="sex" name="sex" />
							    </td>
								</tr>
						    <tr>
							    <th width="50%">
							         电话
							    </th>
							    <td width="50%">
							        	<input type="text" id="tel" name="tel" />
							    </td>
								</tr>
						    <tr>
							    <th width="50%">
							         年龄
							    </th>
							    <td width="50%">
							        	<input type="text" id="age" name="age" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')"/>
							    </td>
								</tr>
						    <tr>
							    <th width="50%">
							         邮箱
							    </th>
							    <td width="50%">
							        	<input type="text" id="email" name="email" />
							    </td>
								</tr>
						    <tr>
							    <th width="50%">
							         创建时间
							    </th>
							    <td width="50%">
							        	<input type="text" id="shijian" name="shijian" />
							    </td>
								</tr>
						<tr>
						    <th width="50%">
						        &nbsp;
						    </th>
						    <td width="50%">
						       <input type="submit" class="button bg-main" value="提交" onclick="return check()"/>&nbsp;
						    </td>
						</tr>
				</table>
			</form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
