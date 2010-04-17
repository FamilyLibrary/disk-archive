package com.alextim.diskarchive;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	BeanFactory factory = new XmlBeanFactory(new ClassPathResource(""));
    	
        System.out.println( "Hello World!" );
    }
}
