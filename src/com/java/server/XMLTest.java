package com.java.server;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;

/**
 * SAX 解析 xml
 * 基本步骤
 */
public class XMLTest {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        // 1. 获取解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // 2. 从解析工厂获取解析器
        SAXParser parser = factory.newSAXParser();
        // 3. 加载文档 document 注册处理器
        // 4. 编写处理器
        PersonHandler handler = new PersonHandler();
        // 5. 解析
        parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("com/java/server/person.xml"), handler);


    }
}

class PersonHandler extends DefaultHandler {

    @Override
    public void startDocument() {
        System.out.println("--------解析 XML 开始----------");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println(qName + "--->解析开始");
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String contents = new String(ch, start, length).trim();
        System.out.println("内容为：" + contents);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println(qName + "--->解析结束");
    }

    @Override
    public void endDocument() {
        System.out.println("--------解析 XML 结束----------");
    }

}
