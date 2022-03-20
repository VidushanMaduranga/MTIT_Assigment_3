package com.hackerthon.common;

import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;

public class UtilQ extends UtilC {
	
	public static String Q(String id) throws Exception {
		NodeList nodeList; Element element = null;
		
		nodeList = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new File("src/com/hackerthon/config/EmployeeQuery.xml")).getElementsByTagName("query");
		
		for (int x = 0; x < nodeList.getLength(); x++) {
			element = (Element) nodeList.item(x);
			if (element.getAttribute("id").equals(id))
				break;
		}
		
		return element.getTextContent().trim();
	}
}
