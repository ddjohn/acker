package se.avelon.edge.utilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DajoXmlUtil {
	static DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

	public static Document createEmptyDocument() throws ParserConfigurationException {
		//DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		//DocumentBuilder builder = factory.newDocumentBuilder();
		//DOMImplementation impl = builder.getDOMImplementation();
		//return impl.createDocument(null,null,null);
		
	      DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
          Document doc = builder.newDocument();
          return doc;
	}

	public static Reader getFileAsStream(String filename) {
	
		try {
			return new FileReader(filename);
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return new InputStreamReader(DajoXmlUtil.class.getResourceAsStream(filename));
	}
	
	public static Document getDocument(String filename) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource is = new InputSource();
		//is.setCharacterStream(new FileReader(MyXmlUtil.class.getResource(filename).toString()));
		is.setCharacterStream(DajoXmlUtil.getFileAsStream(filename));
		return  db.parse(is);
	}

	public static Document createDocumentFromString(String xml) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilder db = dbf.newDocumentBuilder();
		InputSource is = new InputSource();
		is.setCharacterStream(new StringReader(xml));
		return db.parse(is);
	}

	public static String documentToString(Document doc) throws TransformerException {
		TransformerFactory transfac = TransformerFactory.newInstance();
		Transformer trans = transfac.newTransformer();
		trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		trans.setOutputProperty(OutputKeys.INDENT, "yes");
	
		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		DOMSource source = new DOMSource(doc);
		trans.transform(source, result);
	
		return sw.toString();
	}

	public static NodeList xpathSearch(Document doc, String search) throws XPathExpressionException {
		XPathFactory xPathFactory = XPathFactory.newInstance();
		XPath xPath = xPathFactory.newXPath();
		XPathExpression xPathExpression = xPath.compile(search);
		return (NodeList)xPathExpression.evaluate(doc, XPathConstants.NODESET);
	}

	public static Document merge(Document doc1, Document doc2) throws Exception {
		Node rootNode1 = doc1.getFirstChild();
		Node rootNode2 = doc2.getFirstChild();

		Document doc = DajoXmlUtil.createEmptyDocument();

		Transformer tx   = TransformerFactory.newInstance().newTransformer();

		if(rootNode1.getNodeName().compareTo(rootNode2.getNodeName()) == 0) {
			DOMSource source = new DOMSource(doc1);
			DOMResult result = new DOMResult();
			tx.transform(source,result);
			doc = (Document)result.getNode();

			Node node = doc.importNode(rootNode2, true);
			NodeList nodes = node.getChildNodes();
			for(int i = 0; i < nodes.getLength(); i++) {
				doc.getDocumentElement().appendChild(nodes.item(i));
			}
		}
		else {
			throw new Exception("Could not merge XML files. No common root node.");
		}

		return doc;
	}
}
