package com.silences.servlet;

import com.silences.entity.ContactPojo;
import com.silences.readutils.DomReadXml;
import com.silences.service.ContactPojoService;
import com.silences.writeutils.DomWriteXml;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/contactPojo")
public class ContactPojoServlet extends HttpServlet{
	
	ContactPojoService contactPojoService = new ContactPojoService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		doPost(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		//获取请求的方法
		String method = request.getParameter("method");
		//请求分发
		if("getContactPojos".equalsIgnoreCase(method)){
			getContactPojos(request, response);
		}else if("getContactPojo".equalsIgnoreCase(method)) {
			getContactPojo(request, response);
		}else if("updateContactPojo".equalsIgnoreCase(method)) {
			updateContactPojo(request, response);
		}else if("addContactPojo".equalsIgnoreCase(method)) {
			addContactPojo(request, response);
		}else if("deleteContactPojo".equalsIgnoreCase(method)) {
			deleteContactPojo(request, response);
		}else if("readXML".equalsIgnoreCase(method)){
			readXML(request, response);
		}else if("writeXML".equalsIgnoreCase(method)){
			writeXML(request, response);
		}else if("readXML2".equalsIgnoreCase(method)){
			readXML2(request, response);
		}


	}

	private void writeXML(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		ContactPojo contactPojo = contactPojoService.getContactPojo(id);
		// System.out.println(contactPojo.toString());
		DomWriteXml domWriteXml = new DomWriteXml();
		domWriteXml.DomWriteToXml(contactPojo);
		response.sendRedirect("/java_xml/contactPojo?method=getContactPojos");
	}

	private void readXML(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setCharacterEncoding("utf-8");
		DomReadXml dr = new DomReadXml();
		List<ContactPojo> list = dr.readXMLFile();
		request.setAttribute("contactPojos", list);
		request.getRequestDispatcher("/readxml.jsp").forward(request, response);
	}

	private void readXML2(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setCharacterEncoding("utf-8");
		DomReadXml dr = new DomReadXml();
		List<ContactPojo> list = dr.readXMLFile();
		for(ContactPojo c : list) {
			contactPojoService.insertContactPojo(c);
		}
		request.setAttribute("msg", "写入数据库成功！");
		request.getRequestDispatcher("/readxml.jsp").forward(request, response);
	}

	private void getContactPojos(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setCharacterEncoding("utf-8");
		List<ContactPojo> list = contactPojoService.getContactPojos();
		request.setAttribute("contactPojos", list);
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
	private void getContactPojo(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		int id = Integer.parseInt(request.getParameter("id"));
		ContactPojo contactPojo = contactPojoService.getContactPojo(id);
		request.setAttribute("contactPojo", contactPojo);
		request.getRequestDispatcher("/editContactPojo.jsp").forward(request, response);
	}
	
	private void updateContactPojo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String person = request.getParameter("person");
		String tags = request.getParameter("tags");
		String title = request.getParameter("title");
		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");
		String address = request.getParameter("address");
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		String king = request.getParameter("king");
		String phone = request.getParameter("phone");
		String knows = request.getParameter("knows");
		String description = request.getParameter("description");
		ContactPojo contactPojo = new ContactPojo(id, person, tags, title, firstName, middleName, lastName,
				address, latitude, longitude, king, phone, knows, description);
		contactPojoService.updateContactPojo(contactPojo);
		response.sendRedirect("/java_xml/contactPojo?method=getContactPojos");
	}
	
	private void deleteContactPojo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		contactPojoService.deleteContactPojo(id);
		response.sendRedirect("/java_xml/contactPojo?method=getContactPojos");
	}
	
	private void addContactPojo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String person = request.getParameter("person");
		String tags = request.getParameter("tags");
		String title = request.getParameter("title");
		String firstName = request.getParameter("firstName");
		String middleName = request.getParameter("middleName");
		String lastName = request.getParameter("lastName");
		String address = request.getParameter("address");
		String latitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");
		String king = request.getParameter("king");
		String phone = request.getParameter("phone");
		String knows = request.getParameter("knows");
		String description = request.getParameter("description");
		ContactPojo contactPojo = new ContactPojo(0, person, tags, title, firstName, middleName, lastName,
				address, latitude, longitude, king, phone, knows, description);
		contactPojoService.insertContactPojo(contactPojo);
		response.sendRedirect("/java_xml/contactPojo?method=getContactPojos");
	}
}
