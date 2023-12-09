package com.company;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        //Настройки для парсинга
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

        // Что бы прочитать XML файл, нужно создать сборщик
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        parser.parse(new File("text.xml"), handler);




    }
}
