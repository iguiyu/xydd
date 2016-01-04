<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>校园叮咚教育平台</title>
	<link rel="shortcut icon" href="/favicon.ico"/>
	<link rel="bookmark" href="/favicon.ico"/>
	<link rel="stylesheet" type="text/css" href="css/global.css">
	<link rel="stylesheet" type="text/css" href="css/center.css">
</head>
<%
List<Map> classStudent=(List<Map>)request.getAttribute("classStudent");

%>
<body>
	<header class="header">
		<div class="fn-clear container">
			<div class="fn-left">
				<a href="###" class="logo"></a>
			</div>
			<div class="fn-right personal-info">
				欢迎您，<strong>张三娣</strong>
				<a href="###" class="logout">退出</a>
			</div>
		</div>
	</header>
	<article class="content">
		<nav class="top-nav">
			<ul class="fn-clear container" id="nav_link">
				<li class="active js-nav">	
					<a href="/xydd/classStudentManager.shtml">学生管理</a>
				</li>
				<li class="js-nav">
					<a href="/xydd/perfomanceManager.shtml">考试成绩</a>
				</li>
			</ul>
		</nav>
		<div class="container table-content">
			<table class="table">
				<thead>
					<tr>
						<th>学校名称</th>
						<th>班级</th>
						<th>学生管理</th>
					</tr>
				</thead>
				<tbody>
			 <%
				 if(classStudent!=null&&classStudent.size()>0)
				 {
					 for(int i=0;i<classStudent.size();i++)
					 {
						 Map c=classStudent.get(i);
				%>
				<tr><td width="350"><%=c.get("SCHOOL_NAME") %></td>
				    <td width="350"><%=c.get("CLASS_NAME") %></td>
				    <td width="350"><a href="/xydd/searchStudentList.shtml?classID=<%=c.get("CLASS_ID") %>&schoolName=<%=c.get("SCHOOL_NAME") %>&className=<%=c.get("CLASS_NAME") %>">查看</a>  <a href="/xydd/delSchoolClass.shtml?classID=<%=c.get("CLASS_ID") %>">删除</a></a></td></tr>
				 <%		 
					 }
				 }
			 %>
					
				</tbody>
			</table>
			<div class="text-right">
				<a href="javascript:;" class="btn" id="add_btn">新建班级</a>
			</div>
		</div>
	</article>
	<footer class="footer">
		<div class="container">
			Copyright©2015-2015 Tenpay All Rights Reserved 校园叮咚教育 版权所有
		</div>
	</footer>

	<!-- 新增考试成绩弹层 -->
	<div class="layer fn-hide" id="add_layer">
		<div class="layer-box add-box">
			<div class="box-title">
				新增班级
			</div>
			<div class="box-content fn-clear">
				<form class="ui-form" name="" method="post" action="/xydd/addClass.shtml">
			        <div class="ui-form-item ui-form-item-error ">
			            <label for="" class="ui-label">
			            	学校名称
			            </label>
			            <input class="ui-input" type="text" name="schoolName">
	            	</div>
			        <div class="ui-form-item ui-form-item-error ">
			            <label for="" class="ui-label">
			            	班级名称
			            </label>
			            <input class="ui-input" type="text" name="className">
	            	</div>
				    <div class="ui-form-item fn-left ui-form-item-error">
				        <label for="" class="ui-label">
				                                            当前年级
				        </label>
				        <input class="ui-input" type="text" name="dqnj">
				        <p class="ui-form-explain"></p>
				    </div>
			  
			        <div class="text-center">
			        	<input type="submit" class="btn" value="保存">
			        	<button type="button" class="btn js-dismiss">取消</button>
			        </div>
				</form>
			   </div>
			</div>
		</div>


	<script src="http://cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>
	<script type="text/javascript">
		$(".js-nav").click(function(){
	        $(this).addClass("active");
	        $(this).siblings().removeClass("active");
	    });

		$("#add_btn").click(function(){
			$("#add_layer").removeClass("fn-hide");
		});

		$(".js-dismiss").click(function(){
			$("#add_layer").addClass("fn-hide");
		});

	</script>
</body>
</html>
