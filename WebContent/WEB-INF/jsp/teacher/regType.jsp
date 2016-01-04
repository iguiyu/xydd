<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>school</title>
    <link href="../css/school.css" rel="stylesheet" type="text/css" />
</head>
<%
String CLASS_ID=(String)request.getAttribute("CLASS_ID");
String CLASS_NAME=(String)request.getAttribute("CLASS_NAME");
String SCHOOL_NAME=(String)request.getAttribute("SCHOOL_NAME");
String SCHOOL_TYPE=(String)request.getAttribute("SCHOOL_TYPE");
String CURRENT_LEVEL=(String)request.getAttribute("CURRENT_LEVEL");
%>
<body>
    <div class="pad">
        <div>
            <p>学 校：   <%=SCHOOL_NAME %></p>
            <p>班 级：  <%=CURRENT_LEVEL %>年级 <%=CLASS_NAME %></p>
        </div>
        <div class="sel-id">
            <a href="regCourse.shtml?CLASS_ID=<%=CLASS_ID%>&CLASS_NAME=<%=CLASS_NAME%>&SCHOOL_NAME=<%=SCHOOL_NAME%>&SCHOOL_TYPE=<%=SCHOOL_TYPE%>&CURRENT_LEVEL=<%=CURRENT_LEVEL%>&TEACHER_TYPE=B"><span class="id-ico1"></span><div class="sel-id-text">班主任</div><span class="id-ico-right"></span></a>
            <a href="regCourse.shtml?CLASS_ID=<%=CLASS_ID%>&CLASS_NAME=<%=CLASS_NAME%>&SCHOOL_NAME=<%=SCHOOL_NAME%>&SCHOOL_TYPE=<%=SCHOOL_TYPE%>&CURRENT_LEVEL=<%=CURRENT_LEVEL%>&TEACHER_TYPE=R" ><span class="id-ico2"></span><div class="sel-id-text">任课老师</div><span class="id-ico-right"></span></a>
            <a href="regCourse.shtml?CLASS_ID=<%=CLASS_ID%>&CLASS_NAME=<%=CLASS_NAME%>&SCHOOL_NAME=<%=SCHOOL_NAME%>&SCHOOL_TYPE=<%=SCHOOL_TYPE%>&CURRENT_LEVEL=<%=CURRENT_LEVEL%>&TEACHER_TYPE=Z"><span class="id-ico3"></span><div class="sel-id-text">值日生</div><span class="id-ico-right"></span></a>
        </div>
    </div>
    
    <jsp:include page="bottom.jsp" flush="true">     
	<jsp:param name="type" value="1"/> 
 	</jsp:include>
 	
</body>
</html>