package com.java.server;

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
 * 解析 xml 数据
 */
public class XMLTest2 {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        // 1. 获取解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        // 2. 从解析工厂获取解析器
        SAXParser parser = factory.newSAXParser();
        // 3. 加载文档 document 注册处理器
        // 4. 编写处理器
        PersonHandler1 handler = new PersonHandler1();
        // 5. 解析
        parser.parse(Thread.currentThread().getContextClassLoader().getResourceAsStream("com/java/server/person.xml"), handler);

        // 获取数据
        List<Person> persons = handler.getPersons();
        for (Person person: persons) {
            System.out.println(person.getName() + ": " + person.getAge());
        }


    }
}

class PersonHandler1 extends DefaultHandler {

    private List<Person> persons;
    private Person person;
    private String tagName; // 存储操作的标签

    @Override
    public void startDocument() {
        System.out.println("--------解析 XML 开始----------");
        persons = new ArrayList<Person>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        System.out.println(qName + "--->解析开始");
        if (null != qName) {
            tagName = qName; // 存储标签名
            if (tagName.equals("person")) {
                person = new Person();
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String contents = new String(ch, start, length).trim();
        if (null != tagName) {
            if (tagName.equals("name")) {
                person.setName(contents);
            } else if (tagName.equals("age")) {
                if (contents.length() > 0) {
                    person.setAge(Integer.parseInt(contents));
                }
            } else {
                System.out.println("空");
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println(qName + "--->解析结束");
        if (null != qName) {
            tagName = qName; // 存储标签名
            if (tagName.equals("person")) {
                persons.add(person);
            }
        }

        tagName = null;
    }

    @Override
    public void endDocument() {
        System.out.println("--------解析 XML 结束----------");
    }

    public List<Person> getPersons() {
        return persons;
    }
}
