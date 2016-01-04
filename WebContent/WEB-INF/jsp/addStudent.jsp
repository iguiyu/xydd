<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>班级学生管理</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />
  
</head>
<body>
	<form method="post"  action="/xydd/addStudent">
		学号：<input type="text" name="stuID"></input>
		姓名：<input type="text" name="stuName"></input>
		<input type="submit" value="提交"></input>
	</form>
</body>
</html>