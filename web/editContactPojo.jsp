<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改数据</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <style type="text/css">

      label {
        vertical-align: bottom; /* 或者使用 top 或 middle */
      }
      textarea {
        vertical-align: top; /* 与标题对齐 */
      }
    </style>
  </head>
  <%@ page import="com.silences.entity.*" %>
  <body>
  <%  ContactPojo w = (ContactPojo)request.getAttribute("contactPojo"); %>
    <h1>xml信息 修改页面</h1>
    <form action="contactPojo" method="post" style="vertical-align: top;">
    <input type="hidden" name="method" value="updateContactPojo">
    <input type="hidden" name="id" value="<%=w.getId() %>">
      姓&emsp;&emsp;&emsp;名：<input type="text" name="person" value="<%=w.getPerson() %>"><br>
      标&emsp;&emsp;&emsp;签：<input type="text" name="tags" value="<%=w.getTags() %>"><br>
      名 字 标 题：<input type="text" name="title" value="<%=w.getTitle() %>"><br>
      名字的第一：<input type="text" name="firstName" value="<%=w.getFirstName() %>"><br>
      名字的第二：<input type="text" name="middleName"  value="<%=w.getMiddleName() %>"><br>
      名字的第三：<input type="text" name="lastName"  value="<%=w.getLastName() %>"><br>
      地&emsp;&emsp;&emsp;址：<input type="text" name="address"  value="<%=w.getAddress() %>"><br>
      纬&emsp;&emsp;&emsp;度：<input type="text" name="latitude"  value="<%=w.getLatitude() %>"><br>
      经&emsp;&emsp;&emsp;度：<input type="text" name="longitude"  value="<%=w.getLongitude() %>"><br>
      电 话 备 注：<input type="text" name="king"  value="<%=w.getKing() %>"><br>
      电 话 号 码：<input type="text" name="phone"  value="<%=w.getPhone() %>"><br>
      联&emsp;系&emsp;人：<input type="text" name="knows"  value="<%=w.getKnows() %>"><br>
      描&emsp;&emsp;&emsp;述：<textarea style="margin-top: 5px;width: 176px;" rows="10" type="text"
                                     name="description"><%=w.getDescription() %></textarea><br>
      <input type="submit" value="修改"
             style="margin-top: 10px;width: 85px;border: none;border-radius: 5px;background-color: cornflowerblue">
    </form>
  </body>
</html>
