package com.hackerthon.common;

import javax.xml.xpath.XPathFactory;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import org.w3c.dom.Document;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.transform.TransformerFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;

public class UtilTransform extends UtilC {

	private static final ArrayList<Map<String, String>> l = new ArrayList<Map<String, String>>();

	private static Map<String, String> map = null;

	public static void requestRansform() throws Exception {

		Source x = new StreamSource(new File("src/com/hackerthon/config/EmployeeRequest.xml"));
		Source s = new StreamSource(new File("src/com/hackerthon/config/Employee-modified.xsl"));
		Result o = new StreamResult(new File("src/com/hackerthon/config/EmployeeResponse.xml"));
		TransformerFactory.newInstance().newTransformer(s).transform(x, o);
	}

	public static ArrayList<Map<String, String>> xmlPaths() throws Exception {

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse("src/com/hackerthon/config/EmployeeResponse.xml");
		XPath x = XPathFactory.newInstance().newXPath();
		int n = Integer.parseInt((String) x.compile("count(//Employees/Employee)").evaluate(document, XPathConstants.STRING));
		for (int i = 1; i <= n; i++) {
			map = new HashMap<String, String>();
			map.put("XpathEmployeeIDKey", (String) x.compile("//Employees/Employee[" + i + "]/EmployeeID/text()")
					.evaluate(document, XPathConstants.STRING));
			map.put("XpathEmployeeNameKey", (String) x.compile("//Employees/Employee[" + i + "]/EmployeeFullName/text()")
					.evaluate(document, XPathConstants.STRING));
			map.put("XpathEmployeeAddressKey",
					(String) x.compile("//Employees/Employee[" + i + "]/EmployeeFullAddress/text()").evaluate(document,
							XPathConstants.STRING));
			map.put("XpathFacultyNameKey", (String) x.compile("//Employees/Employee[" + i + "]/FacultyName/text()")
					.evaluate(document, XPathConstants.STRING));
			map.put("XpathDepartmentKey", (String) x.compile("//Employees/Employee[" + i + "]/Department/text()")
					.evaluate(document, XPathConstants.STRING));
			map.put("XpathDesignationKey", (String) x.compile("//Employees/Employee[" + i + "]/Designation/text()")
					.evaluate(document, XPathConstants.STRING));
			l.add(map);
		}
		return l;
	}
}
