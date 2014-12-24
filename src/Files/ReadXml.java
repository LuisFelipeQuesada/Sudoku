/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Files;

import game.Block;
import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;


import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author  LuisFelipe
 * using http://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
 */
public class ReadXml {
    File xmlFile = null;
    DocumentBuilderFactory dbFactory = null;
    DocumentBuilder dBuilder = null;
    Document doc = null;
    int[][] matrix = null;

    public ReadXml() {
        try {
            String workingDir = System.getProperty("user.dir");
            String path = workingDir + "\\src\\Files\\files\\sol_1.xml";
            xmlFile = new File(path);
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public int[][] readFile() {
        NodeList nodeList = doc.getElementsByTagName("row");
        matrix = new int[3][3];
        int counter = 0;
        for (int temp = 0; temp < nodeList.getLength(); temp++) {
            Node node = nodeList.item(temp);
            
            Element element = (Element) node;
            element.getAttribute("id");
            matrix[temp][0] = Integer.valueOf(element.getElementsByTagName("cell").item(0).getTextContent());
            matrix[temp][1] = Integer.valueOf(element.getElementsByTagName("cell").item(1).getTextContent());
            matrix[temp][2] = Integer.valueOf(element.getElementsByTagName("cell").item(2).getTextContent());
            counter += 3;
            
            if(counter == 9) {
                Block block = new Block(matrix);
            }
	}
        return matrix;
    }
}
