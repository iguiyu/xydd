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
List<Map> pm=(List<Map>)request.getAttribute("pm");
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
			<table class="table">
				<thead>
					<tr>
						<th>时间</th>
						<th>考试名称</th>
						<th>考试科目</th>
						<th>学校名称</th>
						<th>班级</th>
						<th>成绩管理</th>
					</tr>
				</thead>
				<tbody>
			 <%
				 if(pm!=null&&pm.size()>0)
				 {
					 for(int i=0;i<pm.size();i++)
					 {
						 Map p=pm.get(i);
				%>
				<tr><td width="350"><%=p.get("EXAM_TIME") %></td><td width="350"><%=p.get("EXAM_NAME") %></td>
				    <td width="350"><%=p.get("COURSE_NAME") %></td><td width="350"><%=p.get("SCHOOL_NAME") %></td>
				    <td width="350"><%=p.get("CLASS_NAME") %></td>
				    <td width="350"><a href="/xydd/searchPerfomanceList.shtml?CLASS_ID=<%=p.get("CLASS_ID") %>&COURSE_NAME=<%=p.get("COURSE_NAME")%>&EXAM_TIME=<%=p.get("EXAM_TIME")%>&EXAM_NAME=<%=p.get("EXAM_NAME")%>&SCHOOL_NAME=<%=p.get("SCHOOL_NAME")%>&CLASS_NAME=<%=p.get("CLASS_NAME")%>">查看</a>   
				    <a href="/xydd/delPerfomance.shtml?CLASS_ID=<%=p.get("CLASS_ID") %>&COURSE_NAME=<%=p.get("COURSE_NAME")%>&EXAM_NAME=<%=p.get("EXAM_NAME")%>">删除</a></td></tr>
				 <%		 
					 }
				 }
			 %>
					
				</tbody>
			</table>
			<div class="text-right">
				<a href="javascript:;" class="btn" id="add_btn">新建考试</a>
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
				新增考试
			</div>
			<div class="box-content fn-clear">
				<form class="ui-form" name="" method="post" action="/xydd/addPerfomance.shtml">
			        <div class="ui-form-item ui-form-item-error ">
			            <label for="" class="ui-label">
			            	考试时间
			            </label>
			            <input class="ui-input" type="text" name="EXAM_TIME" value="">
	            	</div>
			        <div class="fn-clear">
			        	<div class="ui-form-item fn-left">
				            <label for="" class="ui-label">
				            	考试科目
				            </label>
				            <select class="ui-select" name="COURSE_NAME">
				            	<option value="语文">语文</option>
				            	<option value="数学">数学</option>
				            	<option value="英语">英语</option>
				            	<option value="历史">历史</option>
				            </select>
				            <i class="i-arrow"></i>
				            <p class="ui-form-explain"></p>
				        </div>
				        <div class="ui-form-item fn-left">
				            <label for="" class="ui-label">
				            	考试名称
				            </label>
				            <input class="ui-input" type="text" name="EXAM_NAME" value="">
				            <p class="ui-form-explain"></p>
				        </div>
			        </div>
			        <div class="fn-clear">
				        <div class="ui-form-item fn-left">
				            <label for="" class="ui-label">
				            	学校名称
				            </label>
				            <select class="ui-select" name="SCHOOL_NAME">
				             <%
							 if(pm!=null&&pm.size()>0)
							 {
								 for(int i=0;i<pm.size();i++)
								 {
									 Map p=pm.get(i);
							%>
				            	<option value="<%=p.get("SCHOOL_NAME") %>"><%=p.get("SCHOOL_NAME") %></option>
							<%		 
								 }
							 }
						     %>
				            </select>
				            <i class="i-arrow"></i>
				            <p class="ui-form-explain"></p>
				        </div>
				        <div class="ui-form-item fn-left">
				            <label for="" class="ui-label">
				            	所在班级
				            </label>
				            <select class="ui-select" name="CLASS_ID">
				             <%
							 if(pm!=null&&pm.size()>0)
							 {
								 for(int i=0;i<pm.size();i++)
								 {
									 Map p=pm.get(i);
							%>
				            	<option value="<%=p.get("CLASS_ID") %>"><%=p.get("CLASS_NAME") %></option>
							<%		 
								 }
							 }
						     %>
				            </select>
				            <i class="i-arrow"></i>
				            <p class="ui-form-explain"></p>
				        </div>
				    </div>
			        <div class="text-center">
			        	<button type="submit" class="btn">保存</button>
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
