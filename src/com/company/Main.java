package com.company;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XMLStreamException {

        // Что бы прочитать XML файл, нужно создать сборщик ( ◡‿◡ *)
        SAXParserFactory factory1 = SAXParserFactory.newInstance();
        SAXParser parser1 = factory1.newSAXParser();

        //Настройки для парсинга ╰(*´︶`*)╯
        DefaultHandler handler = new DefaultHandler(){
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                //Раньше было: super.startElement(uri, localName, qName, attributes);
                String name = attributes.getValue("category");
                if (name != null && !name.isEmpty()){
                    System.out.print(name);
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                //Раньше было: super.characters(ch, start, length);
                // выводим весь вложенный текст
                String s = "";
                for (int i=0; i<length; i++){
                    s += ch[start+i];
                }
                System.out.print(s);
            }
        };

        // --------------- 1 вариант
        parser1.parse(new File("text.xml"), handler);

        System.out.println("--------------- --------------- --------------- --------------- ");

        // --------------- либо 2 вариант (ﾉ´ヮ`)ﾉ*: ･ﾟ
        XMLInputFactory factory2 = XMLInputFactory.newFactory();
        XMLStreamReader parser2 =  factory2.createXMLStreamReader(new FileInputStream("text.xml"));

        while (parser2.hasNext()){
            int event = parser2.next();
            if (event == XMLStreamConstants.START_ELEMENT){
                if (parser2.getAttributeValue(0) != null)
                    System.out.print(parser2.getAttributeValue(0));
            }
            if (event == XMLStreamConstants.CHARACTERS){
                System.out.print(parser2.getText());
            }
        }

    }
}
