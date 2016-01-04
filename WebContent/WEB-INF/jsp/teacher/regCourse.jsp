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
String CLASS_ID=(String)request.getAttribute("CLASS_ID");
String CLASS_NAME=(String)request.getAttribute("CLASS_NAME");
String SCHOOL_NAME=(String)request.getAttribute("SCHOOL_NAME");
String SCHOOL_TYPE=(String)request.getAttribute("SCHOOL_TYPE");
String CURRENT_LEVEL=(String)request.getAttribute("CURRENT_LEVEL");
String TEACHER_TYPE=(String)request.getAttribute("TEACHER_TYPE");
String TEACHER_TYPE_STR="";
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
            <p>学 校：   <%=SCHOOL_NAME %></p>
            <p>班 级：  <%=CURRENT_LEVEL %>年级 <%=CLASS_NAME %></p>
            <p>身 份：  <%=TEACHER_TYPE_STR %></p>
        </div>
        <div class="sel-sourse">
            <ul class="clear sel-sourse-li" id="SEL_COURSE">                
                <li class="on">语文<span class="id-ico-right"></span></li>        
                <li>数学<span class="id-ico-right"></span></li>
                <li>英语<span class="id-ico-right"></span></li>            
                <li >自然<span class="id-ico-right"></span></li> 
                <li class="on">德育<span class="id-ico-right"></span></li>
                <li>体育<span class="id-ico-right"></span></li>
                <li id="NO_COURSE">无任课<span class="id-ico-right"></span></li>
                
            </ul>
            <a href="javascript:next()" class="btn btn-primary">下一步</a>
        </div>
    </div>
    
    <jsp:include page="bottom.jsp" flush="true">     
	<jsp:param name="type" value="1"/> 
 	</jsp:include>
 	
<script language="javascript">
 $("#SEL_COURSE li").click( function () {
	 if($(this).text()=="")
	 {
		 
	 }
	 else if($(this).text()=="无任课")
	 {
		 $("#SEL_COURSE li").removeClass("on");
		 $("#NO_COURSE").addClass("on");
	 }
	 else
	 {
		 $(this).toggleClass("on");
		 if($(this).hasClass("on"))
		 {
			 $("#NO_COURSE").removeClass("on");
		 }
	 }
 });
 
 function next()
 {
	 TEACH_LESSON="";
	 if($("#SEL_COURSE li").is(".on"))
	 {
		 $("#SEL_COURSE li").each(function(){
			 	if($(this).hasClass("on"))
				 {
				if(TEACH_LESSON!="")TEACH_LESSON+=" ";
			 	TEACH_LESSON+=$(this).text();
				 }
			  });

		 //alert(TEACH_LESSON);
		 location.href="regMobile.shtml?CLASS_ID=<%=CLASS_ID%>&CLASS_NAME=<%=CLASS_NAME%>&SCHOOL_NAME=<%=SCHOOL_NAME%>&SCHOOL_TYPE=<%=SCHOOL_TYPE%>&CURRENT_LEVEL=<%=CURRENT_LEVEL%>&TEACHER_TYPE=<%=TEACHER_TYPE%>&TEACH_LESSON="+TEACH_LESSON;
	 }
	 else
	 {
		 alert("请选择任课信息");
	 }
 }
 
 </script>
</body>
</html>