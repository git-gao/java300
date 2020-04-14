package com.java.server.servlet;

import com.java.server.Person;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * SAX 解析 xml
 * 解析 web.xml 数据
 */
public class WebXMLParse {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        // 1. 获取解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // 2. 从解析工厂获取解析器
        SAXParser parser = factory.newSAXParser();
        // 3. 加载文档 document 注册处理器
        // 4. 编写处理器
        WebHandler handler = new WebHandler();
        // 5. 解析
        parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("com/java/server/servlet/web.xml"), handler);

        // 获取数据
        List<Entity> entities = handler.getEntities();
        List<Mapping> mappings = handler.getMappings();
        for (Entity entity: entities) {
            System.out.println(entity.getName() + ": " + entity.getClz());
        }
        for (Mapping mapping: mappings) {
            System.out.println(mapping.getName() + ": " + mapping.getPatterns());
        }


    }
}

class WebHandler extends DefaultHandler {

    private List<Entity> entities;
    private List<Mapping> mappings;
    private Entity entity;
    private Mapping mapping;

    // 存储操作的标签
    private String tagName;
    private boolean isMapping = false;

    @Override
    public void startDocument() {
        System.out.println("--------解析 XML 开始----------");
        entities = new ArrayList<Entity>();
        mappings = new ArrayList<Mapping>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println(qName + "--->解析开始");
        if (null != qName) {
            tagName = qName; // 存储标签名
            if (tagName.equals("servlet")) {
                entity = new Entity();
                isMapping = false;
            } else if (tagName.equals("servlet-mapping")) {
                mapping = new Mapping();
                isMapping = true;
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String contents = new String(ch, start, length).trim();
        if (null != tagName) {
            if (isMapping) { // 操作 servlet-mapping
                if (tagName.equals("servlet-name")) {
                    mapping.setName(contents);
                } else if (tagName.equals("url-pattern")) {
                    mapping.addPattern(contents);
                }
            } else { // 操作 servlet
                if (tagName.equals("servlet-name")) {
                    entity.setName(contents);
                } else if (tagName.equals("servlet-class")) {
                    entity.setClz(contents);
                }
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println(qName + "--->解析结束");
        if (null != qName) {
            if (qName.equals("servlet")) {
                entities.add(entity);
            } else if (qName.equals("servlet-mapping")) {
                mappings.add(mapping);
            }
        }

        tagName = null;
    }

    @Override
    public void endDocument() {
        System.out.println("--------解析 XML 结束----------");
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Mapping> getMappings() {
        return mappings;
    }

}
