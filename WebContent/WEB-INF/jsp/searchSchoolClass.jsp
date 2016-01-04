<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学校班级管理</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
  
</head>

<%
List<Map> classes=(List<Map>)request.getAttribute("classes");

%>

 <BODY>
 
 班级列表
 <table border=1>
 <tr><td>班级编码</td><td>学校</td><td>班级</td><td>当前年级</td></tr>
 <%
 if(classes!=null&&classes.size()>0)
 {
	 for(int i=0;i<classes.size();i++)
	 {
		 Map c=classes.get(i);
%>
<tr><td><%=c.get("CLASS_ID") %></td><td><%=c.get("SCHOOL_NAME") %></td><td><%=c.get("CLASS_NAME") %></td><td><%=c.get("CURRENT_LEVEL") %></td></tr>
 <%		 
	 }
 }
 %>
 </table>
 </BODY>
</HTML>
