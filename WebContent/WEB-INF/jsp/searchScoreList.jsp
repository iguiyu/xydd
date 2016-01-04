<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学校班级管理</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
   <script>

	function show(){
	document.getElementById("div").style.display="";
	//alert(document.getElementById("div").style.display)
	}
	function hidden(){
	document.getElementById("div").style.display="none";
	//alert(document.getElementById("div").style.display)
	}
</script>
</head>

<%
List<Map> students=(List<Map>)request.getAttribute("students");
%>

<BODY>
<div>
	学校：    班级：<tr><td></td></tr>
</div>
<br><br><br>
<div id="div" margin: 0 auto;>

	<form method="post"  action="/xydd/addStudent">
		<table border=1>
		    <tr>增加/删除学生</tr>
			<tr><td>姓名：<input type="text" name="stuID"></input></td></tr>
			<tr><td>成绩：<input type="text" name="stuName"></input></td></tr>
			<input type="hidden" name="classID" value=""></input>
			<tr><td><input type="submit" value="确认增加"><input type="submit" value="确认删除"></input></td></tr>
		</table>
	</form>
</div>
<div>
	 <table border=1>
	<tr bgcolor="blue"><td>学号</td><td>姓名</td><td>学号</td><td>姓名</td><td>学号</td><td>姓名</td></tr>
	 
	 <tr>
	 <%
	 
	 if(students!=null&&students.size()>0)
	 {
		 
		for(int i=1;i<students.size()+1;i++)
		{
			Map s=students.get(i-1);
			%>
			<td width="150"><%=s.get("STUDENT_ID") %></td><td width="150"><%=s.get("NAME") %></td>
			<%
			if(i%3==0){
				%>
				</tr><tr>
				<%
			} 
		}
	}
	%>
	</tr>
	 </table>
 </div>
 </BODY>
</HTML>