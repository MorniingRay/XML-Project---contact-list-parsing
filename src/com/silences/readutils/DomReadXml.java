package com.silences.readutils;

import com.silences.entity.ContactPojo;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * DOM 方式读取 XML文件
 *
 * @author silences
 */
public class DomReadXml {

    public static void main(String[] args) throws Exception {
        DomReadXml dr = new DomReadXml();
        System.out.println(dr.readXMLFile());
    }

    public List<ContactPojo> readXMLFile() {
        List<ContactPojo> list = new ArrayList<ContactPojo>();

        // 创建一个 DocumentBuilderFactory 实例，用于创建 DocumentBuilder
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 使用工厂创建一个 DocumentBuilder 实例，用于解析 XML 文档
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        // 使用 ClassLoader 获取资源文件的输入流，并解析成 Document 对象
        Document document = null;
        try {
            // document = builder.parse(DomReadXml.class.getClassLoader().getResourceAsStream("contacts9.xml"));
            document = builder.parse(new File("D:\\Idea Project\\java_xml\\src\\contacts9.xml"));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 获取 XML 文档中所有名为 "contact" 的元素节点
        NodeList nodes = document.getElementsByTagName("contact");
        // 遍历每个 "contact" 元素节点，并输出其子元素的内容
        for (int i = 0; i < nodes.getLength(); i++) {
            ContactPojo contactPojo = new ContactPojo();

            Node node = nodes.item(i);
            Element element = (Element) node;
            contactPojo.setPerson(element.getAttribute("person"));
            contactPojo.setTags(element.getAttribute("tags"));


            // String con = getNodeAsString(element); // 获取标签及其内容
            // String ct = element.getTextContent(); // 获取标签内的文本内容
            // 获取子元素的内容
            if (element.getElementsByTagName("name") != null) {
                //String con = dr.getNodeAsString((Element) element.getElementsByTagName("name").item(0));
                //System.out.println(con);
                Element et = (Element) element.getElementsByTagName("name").item(0);
                contactPojo.setTitle(et.getAttribute("title"));
                NodeList childNodes = et.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node childNode = childNodes.item(j);
                    //System.out.println(childNode.getTextContent() + "==" + childNode.getNodeName());
                    if ("first".equals(childNode.getNodeName())) contactPojo.setFirstName(childNode.getTextContent());
                    if ("middle".equals(childNode.getNodeName())) contactPojo.setMiddleName(childNode.getTextContent());
                    if ("last".equals(childNode.getNodeName())) contactPojo.setLastName(childNode.getTextContent());
                }
            }
            if (element.getElementsByTagName("location") != null) {
                Element et = (Element) element.getElementsByTagName("location").item(0);
                NodeList childNodes = et.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    Node childNode = childNodes.item(j);
                    if ("address".equals(childNode.getNodeName())) contactPojo.setAddress(childNode.getTextContent());
                    if ("latitude".equals(childNode.getNodeName())) contactPojo.setLatitude(childNode.getTextContent());
                    if ("longitude".equals(childNode.getNodeName()))
                        contactPojo.setLongitude(childNode.getTextContent());
                }
            }
            if (element.getElementsByTagName("phone") != null) {
                Element et = (Element) element.getElementsByTagName("phone").item(0);
                contactPojo.setPhone(element.getElementsByTagName("phone").item(0).getTextContent());
                contactPojo.setKing(et.getAttribute("kind"));
            }
            if (element.getElementsByTagName("knows") != null) {
                Element et = (Element) element.getElementsByTagName("knows").item(0);
                contactPojo.setKnows(et.getAttribute("contacts"));
            }
            if (element.getElementsByTagName("description") != null) {
                contactPojo.setDescription(element.getElementsByTagName("description").item(0).getTextContent());
            }
            //System.out.println(contactPojo.toString());
            list.add(contactPojo);
        }
        return list;
    }

    private String getNodeAsString(Element element) {
        StringBuilder builder = new StringBuilder();

        // 开始标签
        builder.append("<").append(element.getTagName()).append(">");

        // 处理子节点
        NodeList childNodes = element.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            builder.append(nodeToString(childNodes.item(i)));
        }

        // 结束标签
        builder.append("</").append(element.getTagName()).append(">");

        return builder.toString();
    }

    private String nodeToString(org.w3c.dom.Node node) {
        StringBuilder builder = new StringBuilder();

        // 文本节点
        if (node.getNodeType() == org.w3c.dom.Node.TEXT_NODE) {
            builder.append(node.getNodeValue());
        }

        // 元素节点
        else if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
            Element element = (Element) node;
            builder.append(getNodeAsString(element));
        }

        return builder.toString();
    }


}