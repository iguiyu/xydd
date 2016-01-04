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
String type=(String)request.getAttribute("type");
List<Map> schools=(List<Map>)request.getAttribute("schools");
%>
<body>

    <div class="pad">
        <div class="reg-search">
        	<form action="regSchool.shtml" id="form1" method="POST">
            <input type="text" name="name" placeholder="搜索"  />
            <span class="reg-search-ico" onclick="form1.submit()"></span>
            </form>
        </div>
        <div class="reg-list">
            <div class="reg-list-top">
                <ul class="clear">
                    <li class="">
                        <div class="table-li">
                            <a <% if(type!=null&&type.equals("X")){%>class="on"<%} %> href="regSchool.shtml?type=X" >
                                <p>小学</p>
                            </a>
                        </div>
                    </li>
                    <li class="">
                        <div class="table-li">
                            <a <% if(type!=null&&type.equals("C")){%>class="on"<%} %> href="regSchool.shtml?type=C" >
                                <p>初中</p>
                            </a>
                        </div>
                    </li>
                    <li class="">
                        <div class="table-li">
                            <a <% if(type!=null&&type.equals("G")){%>class="on"<%} %> href="regSchool.shtml?type=G" >
                                <p>高中</p>
                            </a>
                        </div>
                    </li>
                </ul>
            </div>
            <div class="reg-list-ul">
                <ul class="">
                <% 
                if(schools!=null&&schools.size()>0)
                {
                	for(int i=0;i<schools.size();i++)
                	{
                %>
                    <li class="db"><a href="regClass.shtml?SCHOOL_NAME=<%=(String)schools.get(i).get("SCHOOL_NAME")%>&SCHOOL_TYPE=<%=schools.get(i).get("SCHOOL_TYPE")%>"><div class="reg-list-text fx1"><%=schools.get(i).get("SCHOOL_NAME") %></div></a></li>
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