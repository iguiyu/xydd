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
String SCHOOL_NAME=(String)request.getAttribute("SCHOOL_NAME");
String SCHOOL_TYPE=(String)request.getAttribute("SCHOOL_TYPE");
String CURRENT_LEVEL=(String)request.getAttribute("CURRENT_LEVEL");
List<Map> classes=(List<Map>)request.getAttribute("classes");
%>
<body>
    <div class="pad">
        <div>
            <p>学 校：   <%=SCHOOL_NAME %></p>
        </div>
        <div class="reg-list">
            <div class="reg-list-top">
                <ul class="clear">
                    <li class="">
                        <div class="table-li">
                            <a <% if(CURRENT_LEVEL!=null&&CURRENT_LEVEL.equals("1")){%>class="on"<%} %> href="regClass.shtml?SCHOOL_NAME=<%=SCHOOL_NAME%>&SCHOOL_TYPE=<%=SCHOOL_TYPE%>&CURRENT_LEVEL=1" >
                                <p>一年级</p>
                                <p>First</p>
                            </a>
                        </div>
                    </li>
                    <li class="">
                        <div class="table-li">
                            <a <% if(CURRENT_LEVEL!=null&&CURRENT_LEVEL.equals("2")){%>class="on"<%} %> href="regClass.shtml?SCHOOL_NAME=<%=SCHOOL_NAME%>&SCHOOL_TYPE=<%=SCHOOL_TYPE%>&CURRENT_LEVEL=2" >
                                <p>二年级</p>
                                <p>Second</p>
                            </a>
                        </div>
                    </li>
                    <li class="">
                        <div class="table-li">
                            <a <% if(CURRENT_LEVEL!=null&&CURRENT_LEVEL.equals("3")){%>class="on"<%} %> href="regClass.shtml?SCHOOL_NAME=<%=SCHOOL_NAME%>&SCHOOL_TYPE=<%=SCHOOL_TYPE%>&CURRENT_LEVEL=3" >
                                <p>三年级</p>
                                <p>Third</p>
                            </a>
                        </div>
                    </li>
                    <%
                    if(SCHOOL_TYPE!=null&&SCHOOL_TYPE.equals("X"))
                    {
                    %>
                    <li class="">
                        <div class="table-li">
                            <a <% if(CURRENT_LEVEL!=null&&CURRENT_LEVEL.equals("4")){%>class="on"<%} %> href="regClass.shtml?SCHOOL_NAME=<%=SCHOOL_NAME%>&SCHOOL_TYPE=<%=SCHOOL_TYPE%>&CURRENT_LEVEL=4" >
                                <p>四年级</p>
                                <p>Fourth</p>
                            </a>
                        </div>
                    </li>
                    <li class="">
                        <div class="table-li">
                            <a <% if(CURRENT_LEVEL!=null&&CURRENT_LEVEL.equals("5")){%>class="on"<%} %> href="regClass.shtml?SCHOOL_NAME=<%=SCHOOL_NAME%>&SCHOOL_TYPE=<%=SCHOOL_TYPE%>&CURRENT_LEVEL=5" >
                                <p>五年级</p>
                                <p>Fifth</p>
                            </a>
                        </div>
                    </li>
                    <li class="">
                        <div class="table-li">
                            <a <% if(CURRENT_LEVEL!=null&&CURRENT_LEVEL.equals("6")){%>class="on"<%} %> href="regClass.shtml?SCHOOL_NAME=<%=SCHOOL_NAME%>&SCHOOL_TYPE=<%=SCHOOL_TYPE%>&CURRENT_LEVEL=6" >
                                <p>六年级</p>
                                <p>Sixth</p>
                            </a>
                        </div>
                    </li>
                    <%
                    }
                    %>
                </ul>
            </div>
            <div class="reg-list-ul">
                <ul class="">
                 <% 
                if(classes!=null&&classes.size()>0)
                {
                	for(int i=0;i<classes.size();i++)
                	{
                %>
                    <li class="db"><a href="regType.shtml?SCHOOL_NAME=<%=(String)classes.get(i).get("SCHOOL_NAME")%>&SCHOOL_TYPE=<%=classes.get(i).get("SCHOOL_TYPE")%>&CURRENT_LEVEL=<%=classes.get(i).get("CURRENT_LEVEL")%>&CLASS_NAME=<%=classes.get(i).get("CLASS_NAME")%>&CLASS_ID=<%=classes.get(i).get("CLASS_ID")%>"><div class="reg-list-text fx1"><%=classes.get(i).get("CLASS_NAME") %></div></a></li>
                <% 
                	}
                }
                %>
                </ul>
            </div>
        </div>

    </div>
    
    <jsp:include page="bottom.jsp" flush="true">     
	<jsp:param name="type" value="1"/> 
 	</jsp:include>
</body>
</html>