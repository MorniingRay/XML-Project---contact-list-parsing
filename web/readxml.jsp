<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.silences.entity.*" %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>xml数据页面</title>
    <style type="text/css">
        table.gridtable {
            font-family: verdana, arial, sans-serif;
            font-size: 11px;
            color: #333333;
            border-width: 1px;
            border-color: #666666;
            border-collapse: collapse;
        }

        table.gridtable th {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #666666;
            background-color: #dedede;
        }

        table.gridtable td {
            border-width: 1px;
            padding: 8px;
            border-style: solid;
            border-color: #666666;
            background-color: #ffffff;
        }

        /* a {
           text-decoration: none;
        } */
        button {
            width: 85px;
            height: 30px;
            border-radius: 5px;
            border: none;
        }

        a {
            text-decoration: none;
        }
    </style>
</head>
<body>
<br>

<h1>xml 文件信息</h1>
<hr>
<br>
<a href="contactPojo?method=getContactPojos">
    <button style="background-color: cornflowerblue">返回</button>
</a>

<a href="/java_xml/contactPojo?method=readXML2">
    <button style="background-color: sandybrown">写入数据库</button>
</a>

<br><%=request.getAttribute("msg")!=null?request.getAttribute("msg"):""%><br>
<p>&lt;?xml version="1.0"?&gt;</p>
<p>&lt;contacts
        xmlns="http://www.example.com/contacts"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.example.com/contacts contacts9.xsd"
        source="Beginning XML 4E"
        version="1.0"&gt; </p>
<%
    List<ContactPojo> works = (List<ContactPojo>) request.getAttribute("contactPojos");
    // System.out.print(works);
    if (works != null) {
        for (ContactPojo w : works) {

%>
<p>&emsp;&lt;contact person="<%=w.getPerson() %>" tags="<%=w.getTags() %>"&gt; </p>
<p>&emsp;&emsp;&lt;name title="<%=w.getTitle() %>"&gt; </p>
<p>&emsp;&emsp;&emsp;&lt;first&gt;<%=w.getFirstName() %>&lt;/first&gt;</p>
<p>&emsp;&emsp;&emsp;&lt;middle&gt;<%=w.getMiddleName() %>&lt;/middle&gt;</p>
<p>&emsp;&emsp;&emsp;&lt;last&gt;<%=w.getLastName() %>&lt;/last&gt;</p>
<p>&emsp;&emsp;&lt;/name&gt; </p>
<p>&emsp;&emsp;&lt;location&gt; </p>
<p>&emsp;&emsp;&emsp;&lt;address&gt;<%=w.getAddress() %>&lt;/address&gt;</p>
<p>&emsp;&emsp;&emsp;&lt;latitude&gt;<%=w.getLatitude() %>&lt;/latitude&gt;</p>
<p>&emsp;&emsp;&emsp;&lt;longitude&gt;<%=w.getLongitude() %>&lt;/longitude&gt;</p>
<p>&emsp;&emsp;&emsp;&lt;/location&gt; </p>
<p>&emsp;&emsp;&lt;phone kind="<%=w.getKing() %>"&gt;<%=w.getPhone() %>&lt;/phone&gt;</p>
<p>&emsp;&emsp;&lt;knows contacts="<%=w.getKnows() %>"/ &gt;</p>
<p>&emsp;&emsp;&lt;description&gt; </p>
<p>&emsp;&emsp;&emsp;<%=w.getDescription() %></p>
<p>&emsp;&emsp;&lt;/description&gt; </p>
<p>&emsp;&lt;/contact&gt; </p>
<br>
<%
        }
    }
%>
&lt;/contacts>&gt;
</body>
</html>