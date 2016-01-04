<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>school</title>
    <link href="../css/school.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="../js/jquery.js"></script>
</head>
<%
String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
String CLASS_ID=(String)request.getAttribute("CLASS_ID");
String CLASS_NAME=(String)request.getAttribute("CLASS_NAME");
String SCHOOL_NAME=(String)request.getAttribute("SCHOOL_NAME");
String SCHOOL_TYPE=(String)request.getAttribute("SCHOOL_TYPE");
String CURRENT_LEVEL=(String)request.getAttribute("CURRENT_LEVEL");
String TEACHER_TYPE=(String)request.getAttribute("TEACHER_TYPE");
String TEACH_LESSON=(String)request.getAttribute("TEACH_LESSON");
String TEACHER_TYPE_STR="";
if(TEACHER_TYPE!=null)
if(TEACHER_TYPE.equals("B"))
{
	TEACHER_TYPE_STR="班主任";
}
else if(TEACHER_TYPE.equals("R"))
{
	TEACHER_TYPE_STR="任课老师";
}
else if(TEACHER_TYPE.equals("Z"))
{
	TEACHER_TYPE_STR="值日生";
}
%>
<body>
    <div class="pad">
        <div>
            <p>学 校：<%=SCHOOL_NAME %></p>
            <p>班 级： <%=CURRENT_LEVEL %>年级 <%=CLASS_NAME %></p>
            <p>身 份：  <%=TEACHER_TYPE_STR %></p>
            <p>任 课： <%=TEACH_LESSON %></p>
        </div>
        <div class="sel-info">
            <ul class="sel-info-li">                
                <li class="db vh">
                    <span class="ico-info-name"></span>
                    <div class="fx1">
                        <input type="text" id="name" placeholder="请输入真实姓名"  />
                    </div>
                </li>
                <li class="db vh">
                    <span class="ico-info-mobile"></span>
                    <div class="fx1">
                        <input class="fx1" type="number" id="mobile" placeholder="请输入手机号"  />
                    </div>
                </li>
                <li class="db vh">
                    <span class="ico-info-code"></span>
                    <div class="fx1">
                        <input class="fx1" type="text" id="verCode" value="1111" placeholder="验证码"  />
                    </div>
                    <a class="code-a" href="###">获取验证码</a>
                </li>                
            </ul>
            <a href="javascript:reg()" class="btn btn-primary">注册</a>
        </div>
    </div>
    
    <jsp:include page="bottom.jsp" flush="true">     
	<jsp:param name="type" value="1"/> 
 	</jsp:include>
 	
  <script language="javascript">
  function reg(){
	  	var CLASS_ID="<%=CLASS_ID%>";
	  	var TEACHER_TYPE="<%=TEACHER_TYPE%>";
	  	var TEACH_LESSON="<%=TEACH_LESSON%>";
		var name = $("#name").val();
		var mobile = $("#mobile").val();
		var verCode = $("#verCode").val();
		if(name==null || name==""){
			alert("请输入真实姓名！");
			return;
		}
		if(mobile==null || mobile==""){
			alert("请输入手机号码！");
			return;
		}
		if(verCode==null || verCode==""){
			alert("请输入验证码！");
			return;
		}
		
		$.ajax({
			type:"POST",
			url:"<%=basePath%>/teacher/regTeacher.shtml",
			data:{name:name,mobile:mobile,verCode:verCode,CLASS_ID:CLASS_ID,TEACHER_TYPE:TEACHER_TYPE,TEACH_LESSON:TEACH_LESSON},
			dataType:"json",
			success:function(result){
				var code = result.code;
				var msg = result.msg;
				if(code=="OK"){
					alert("注册成功！");
					
				}else{
					alert("["+code+"]"+msg);
				}
			}
		});
	}
  </script>
</body>
</html>