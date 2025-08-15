package com.silences.writeutils;

import com.silences.entity.ContactPojo;
import com.silences.readutils.DomReadXml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;

public class DomWriteXml {

    public static void main(String[] args) throws Exception {
        DomWriteXml domWriteXml = new DomWriteXml();
        domWriteXml.DomWriteToXml(new ContactPojo());
    }

    public void DomWriteToXml(ContactPojo contactPojo)  {
        // 获取document对象
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        Document document = null;
        try {
            document = documentBuilder.parse(DomWriteXml.class.getClassLoader().getResourceAsStream("contacts9.xml"));
            // document = documentBuilder.parse(new File("D:\\Idea Project\\java_xml\\src\\contacts9.xml"));
            // System.out.println(document);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 创建contact元素
        Element contactElement = document.createElement("contact");
        // 设置元素属性及值
        contactElement.setAttribute("person", contactPojo.getPerson());
        contactElement.setAttribute("tags", contactPojo.getTags());

        // 创建name元素
        Element nameElement = document.createElement("name");
        // 设置元素属性及值
        nameElement.setAttribute("title", contactPojo.getTitle());
        Element firstElement = document.createElement("first");
        firstElement.setTextContent(contactPojo.getFirstName());
        nameElement.appendChild(firstElement);

        Element middleElement = document.createElement("middle");
        middleElement.setTextContent(contactPojo.getMiddleName());
        nameElement.appendChild(middleElement);

        Element lastElement = document.createElement("last");
        lastElement.setTextContent(contactPojo.getLastName());
        nameElement.appendChild(lastElement);

        // 创建location元素
        Element locationElement = document.createElement("location");
        // 设置元素属性及值
        nameElement.setAttribute("title", contactPojo.getTitle());
        Element addressElement = document.createElement("address");
        addressElement.setTextContent(contactPojo.getAddress());
        locationElement.appendChild(addressElement);

        Element latitudeElement = document.createElement("latitude");
        latitudeElement.setTextContent(contactPojo.getLatitude());
        locationElement.appendChild(latitudeElement);

        Element longitudeElement = document.createElement("longitude");
        longitudeElement.setTextContent(contactPojo.getLongitude());
        locationElement.appendChild(longitudeElement);

        // 创建phone元素
        Element phoneElement = document.createElement("phone");
        // 设置元素属性及值
        phoneElement.setAttribute("kind", contactPojo.getKing());
        phoneElement.setTextContent(contactPojo.getPhone());

        // 创建knows元素
        Element knowsElement = document.createElement("knows");
        // 设置元素属性及值
        knowsElement.setAttribute("contacts", contactPojo.getKnows());

        // 创建knows元素
        Element descriptionElement = document.createElement("description");
        // 设置元素属性及值
        descriptionElement.setTextContent(contactPojo.getDescription());

        // 建立父子关系 使用appendChild(Node child)  由于Element继承Node 多态
        contactElement.appendChild(nameElement);
        contactElement.appendChild(locationElement);
        contactElement.appendChild(phoneElement);
        contactElement.appendChild(knowsElement);
        contactElement.appendChild(descriptionElement);

        // 再将Element与根元素contacts建立父子关系
        NodeList contactsNodeList = document.getElementsByTagName("contacts");
        // 该集合就一个元素 则无需便利直接使用item(int index)取元素
        contactsNodeList.item(0).appendChild(contactElement);

        // 至关重要的一步 就是将内存中document写入到xml文件 否则以上操作始终在内存
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        // transformer调用transform(Source s,Result r)方法
        // 首先获取两个参数
        DOMSource domSource = new DOMSource(document);
        // Result result = new StreamResult("contacts9.xml");
        // Result result = new StreamResult(new File("D:\\Idea Project\\java_xml\\src\\contacts9.xml"));

        Result result = null;
        try {
            result = new StreamResult(new File(DomWriteXml.class.getResource("/").toURI().getPath()+"contacts9.xml"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        // System.out.println("99"+result);
        // System.out.println("22"+domSource);
        // 调用方法 将document写入收藏信息.xml
        try {
            transformer.transform(domSource, result);
        } catch (TransformerException e) {
            e.printStackTrace();
        }


    }

}