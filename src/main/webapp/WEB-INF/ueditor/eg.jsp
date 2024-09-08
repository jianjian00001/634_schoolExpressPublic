<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/common/taglibs.jsp"%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<title></title>
		<link rel="stylesheet" href="<%=path%>/css/pintuer.css" />
		<link rel="stylesheet" href="<%=path%>/css/gcommon.css" />
		<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
		<script type="text/javascript" src="<%=path%>/js/pintuer.js"></script>

		<!-- 日期控件 -->
		<script src="<%=path%>/resource/My97DatePicker/WdatePicker.js"></script>
		<!-- 编辑器源码文件 -->
        <script type="text/javascript" charset="utf-8" src="<%=path%>/resource/ueditor/ueditor.config.js"></script>
    	<script type="text/javascript" charset="utf-8" src="<%=path%>/resource/ueditor/ueditor.all.min.js"> </script>
	    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
	    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
	    <script type="text/javascript" charset="utf-8" src="<%=path%>/resource/ueditor/lang/zh-cn/zh-cn.js"></script>

	</head>

	<body>
		<div class="container">
			<div class="xs12">
				<ul class="bread bg-main">

					<li><a href="#">账号信息添加</a> </li>
				</ul>
			</div>
		</div>
		<div class="container">
			<div class="panel gao-form-bg">
				<div class="panel-head bg-main">
					<strong>添加账号信息</strong>
				</div>
				<div class="panel-body">
					<form onsubmit="return true;" class="form-x radius-big" action="<%=path %>/account/save.action" name="gaoform" method="post">
								<div class="form-group" id="f_1510851941628">
									<div class="label">
										<label for="f_username_txt">
											登录名
										</label>
									</div>
									<div class="field">
								        	<input class="input" id="loginNm" name="loginNm" maxlength="50" value="" data-validate="required:请填写内容" placeholder="输入内容" type="text">
									</div>
								</div>
								<div class="form-group" id="f_1510851941628">
									<div class="label">
										<label for="f_username_txt">
											登陆密码
										</label>
									</div>
									<div class="field">
								        	<input class="input" id="loginPwd" name="loginPwd" maxlength="50" value="" data-validate="required:请填写内容" placeholder="输入内容" type="text">
									</div>
								</div>
								<div class="form-group" id="f_1510851941628">
									<div class="label">
										<label for="f_username_txt">
											盐验证
										</label>
									</div>
									<div class="field">
								        	<input class="input" id="salt" name="salt" maxlength="50" value="" data-validate="required:请填写内容" placeholder="输入内容" type="text">
									</div>
								</div>
								<div class="form-group" id="f_1510851941628">
									<div class="label">
										<label for="f_username_txt">
											创建时间
										</label>
									</div>
									<div class="field">
								        	<input class="input" id="createDt" name="createDt" maxlength="50" value="" data-validate="required:请填写内容" placeholder="输入内容" type="text">
									</div>
								</div>
								<div class="form-group" id="f_1510851941628">
									<div class="label">
										<label for="f_username_txt">
											修改时间
										</label>
									</div>
									<div class="field">
								        	<input class="input" id="updateDt" name="updateDt" maxlength="50" value="" data-validate="required:请填写内容" placeholder="输入内容" type="text">
									</div>
								</div>
								<div class="form-group" id="f_1510851941628">
									<div class="label">
										<label for="f_username_txt">
											注册IP
										</label>
									</div>
									<div class="field">
								        	<input class="input" id="regIp" name="regIp" maxlength="50" value="" data-validate="required:请填写内容" placeholder="输入内容" type="text">
									</div>
								</div>
								<div class="form-group" id="f_1510851941628">
									<div class="label">
										<label for="f_username_txt">
											最后登陆时间
										</label>
									</div>
									<div class="field">
									   <input id="lastLoginDt" name="lastLoginDt" readonly="readonly" class="input"  type="text" onfocus="WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})"/>
									</div>
								</div>
								<div class="form-group" id="f_1510851941628">
									<div class="label">
										<label for="f_username_txt">
											状态
										</label>
									</div>
									<div class="field">
								        	<input class="input" id="status" name="status" maxlength="50" value="" data-validate="required:请填写内容" placeholder="输入内容" type="text">
									</div>
								</div>
								<div class="form-group" id="f_1510851941628">
									<div class="label">
										<label for="f_username_txt">
											删除标记
										</label>
									</div>
									<div class="field">
								        	<input type="text" class="input" id="del" name="del" maxlength="5" value="" data-validate="required:请填写数字,number:请输入数字,length#<5:字数在0-5个" placeholder="输入数字">
										         <!-- 加载编辑器的容器 -->
										        <script id="editor" name="content" type="text/plain" style="width:100%;height:300px;">${t1['plan']['remark'] }</script>
											    <!-- 实例化编辑器 -->
											    <script type="text/javascript">
											    	//实例化编辑器
													//建议使用工厂方法getEditor创建和引用编辑器实例，如果在某个闭包下引用该编辑器，直接调用UE.getEditor('editor')就能拿到相关的实例
											        var editor = UE.getEditor('editor');
											        UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
											        UE.Editor.prototype.getActionUrl = function(action) {
											              if (action == 'uploadimage' || action == 'uploadscrawl' || action == 'uploadvideo') {
											                  return '${ctx}/ueditor/saveFile';
											              } else {
											                  return this._bkGetActionUrl.call(this, action);
											              }
											        }
											    </script>
									</div>
								</div>
						<div class="form-button">
								<button class="button bg-blue" type="submit"> 添加</button>
						</div>
					</form>
				</div>
			</div>
		</div>
   </body>
</html>
