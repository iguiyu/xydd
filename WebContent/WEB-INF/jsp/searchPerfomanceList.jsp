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
List<Map> pfList=(List<Map>)request.getAttribute("pfList");
%>
<%
String COURSE_NAME=new String((request.getParameter("COURSE_NAME")).getBytes("iso-8859-1"),"utf-8");
String EXAM_TIME = new String((request.getParameter("EXAM_TIME")).getBytes("iso-8859-1"),"utf-8");
String EXAM_NAME=new String((request.getParameter("EXAM_NAME")).getBytes("iso-8859-1"),"utf-8");
String CLASS_NAME = new String((request.getParameter("CLASS_NAME")).getBytes("iso-8859-1"),"utf-8");
String SCHOOL_NAME = new String((request.getParameter("SCHOOL_NAME")).getBytes("iso-8859-1"),"utf-8");
String CLASS_ID=request.getParameter("CLASS_ID");
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
				<li class="js-nav">
					<a href="/xydd/classStudentManager.shtml">学生管理</a>
				</li>
				<li class="active js-nav">
					<a href="/xydd/perfomanceManager.shtml">考试成绩</a>
				</li>
			</ul>
		</nav>
		<div class="container table-content">
		考试时间：<%=EXAM_TIME %> &nbsp 考试名称：<%=EXAM_NAME%> &nbsp 学校：<%=SCHOOL_NAME %> &nbsp 班级：<%=CLASS_NAME%> &nbsp 科目：<%=COURSE_NAME%>
			<table class="table">
				<thead>
					<tr>
						<th>姓名</th>
						<th>成绩</th>
						<th>姓名</th>
						<th>成绩</th>
						<th>姓名</th>
						<th>成绩</th>
					</tr>
				</thead>
				<tbody>
			 <tr>
				 <%
				 if(pfList!=null&&pfList.size()>0)
				 {
					 
					for(int i=1;i<pfList.size()+1;i++)
					{
						Map pf=pfList.get(i-1);
						%>
						<td width="150"><%=pf.get("NAME") %></td><td width="150"><%=pf.get("EXAM_SCORE") %></td>
						<%
						if(i%3==0){
							%>
							</tr><tr>
							<%
						} 
					}
				}
				%>	
				</tbody>
			</table>
			<div class="text-right">
				<a href="javascript:;" class="btn" id="add_btn">添加成绩</a>
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
				添加成绩
			</div>
			<div class="box-content fn-clear">
				<form class="ui-form" name="" method="post" action="/xydd/addStudent.shtml">
			        <div class="ui-form-item ui-form-item-error ">
			            <label for="" class="ui-label">
			            	学号
			            </label>
			            <input class="ui-input" type="text" name="stuNo">
			            <input class="ui-input" type="hidden" name="classID" value="<%=CLASS_ID %>">
			            <input class="ui-input" type="hidden" name="className" value="<%=CLASS_NAME %>">
			            <input class="ui-input" type="hidden" name="schoolName" value="<%=SCHOOL_NAME %>">
	            	</div>
			        <div class="ui-form-item ui-form-item-error ">
			            <label for="" class="ui-label">
			            	成绩
			            </label>
			            <input class="ui-input" type="text" name="stuName">
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
