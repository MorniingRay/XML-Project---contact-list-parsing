<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.silences.entity.*" %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>数据页面</title>
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
            width: 95px;
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

<h1>xml列表信息</h1>
<hr>
<%--<form action="/java_xml/contactPojo" method="post">--%>
<%--    <input type="hidden" name="method" value="getContactPojos">--%>
<%--    <input type="text" placeholder="请输入姓名" id="xh" name="stuNumber">--%>
<%--    <input type="submit" value="搜索">--%>
<%--</form>--%>
<br>
<a href="addContactPojo.jsp">
    <button style="background-color: cornflowerblue">添加</button>
</a>
<a href="/java_xml/contactPojo?method=readXML">
    <button style="background-color: cornflowerblue;width: 150px;" >读取xml文件信息</button>
</a>
<br><br>
<table class="gridtable">
    <tr>
        <th>序号</th>
        <th>姓名</th>
        <th>标签</th>
        <th>名字标题</th>
        <th>名字的第一</th>
        <th>名字的第二</th>
        <th>名字的第三</th>
        <th>地址</th>
        <th>纬度</th>
        <th>经度</th>
        <th>电话备注</th>
        <th>电话号码</th>
        <th>联系人</th>
        <th>描述</th>
        <th>操作</th>
    </tr>
    <%
        List<ContactPojo> works = (List<ContactPojo>) request.getAttribute("contactPojos");
       //  System.out.print(works);
        if (works != null) {
            for (ContactPojo w : works) {

    %>

    <tr>
        <td><%=w.getId() %></td>
        <td><%=w.getPerson()!=null?w.getPerson():"" %></td>
        <td><%=w.getTags()!=null?w.getTags():"" %></td>
        <td><%=w.getTitle()!=null?w.getTitle():"" %></td>
        <td><%=w.getFirstName()!=null?w.getFirstName():"" %></td>
        <td><%=w.getMiddleName()!=null?w.getMiddleName():"" %></td>
        <td><%=w.getLastName()!=null?w.getLastName():"" %></td>
        <td><%=w.getAddress()!=null?w.getAddress():"" %></td>
        <td><%=w.getLatitude()!=null?w.getLatitude():"" %></td>
        <td><%=w.getLongitude()!=null?w.getLongitude():"" %></td>
        <td><%=w.getKing()!=null?w.getKing():"" %></td>
        <td><%=w.getPhone()!=null?w.getPhone():"" %></td>
        <td><%=w.getKnows()!=null?w.getKnows():"" %></td>
        <td><%=w.getDescription()!=null?w.getDescription():"" %></td>
        <td style="width: 230px">
            <a href="contactPojo?method=getContactPojo&id=<%=w.getId() %>">
                <button style="background-color: orange;width: 55px;">修改</button>
            </a>
            <a href="contactPojo?method=deleteContactPojo&id=<%=w.getId() %>">
                <button style="margin-left:8px;background-color: red;width: 55px;">删除</button>
            </a>
            <a href="contactPojo?method=writeXML&id=<%=w.getId() %>">
                <button style="margin-left:8px;background-color: skyblue">写入XML文件</button>
            </a>
        </td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>