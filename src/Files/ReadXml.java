/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Files;

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
    int level;

    public ReadXml(int level) {
        this.matrix = new int[9][9];
        this.level = level;
        
        String fileSeparator = System.getProperty("file.separator");
        String fileName;
        
        try {
            switch(level) {
                case 0:
                    fileName = "src" + fileSeparator + "Files" + fileSeparator + "files" + fileSeparator + "easy" + fileSeparator + "sol_1.xml";
                    break;
                case 1:
                    fileName = "src" + fileSeparator + "Files" + fileSeparator + "files" + fileSeparator + "medium" + fileSeparator + "sol_1.xml";
                    break;
                case 2:
                    fileName = "src" + fileSeparator + "Files" + fileSeparator + "files" + fileSeparator + "hard" + fileSeparator + "sol_1.xml";
                    break;
                default:
                    fileName = "src" + fileSeparator + "Files" + fileSeparator + "files" + fileSeparator + "easy" + fileSeparator + "sol_1.xml";
                    break;
            }
            
            
            String workingDir = System.getProperty("user.dir");
            String absoluteFilePath = workingDir + System.getProperty("file.separator") + fileName;
            
            xmlFile = new File(absoluteFilePath);
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public int[][] createMatrix() {
        NodeList nodeList = doc.getElementsByTagName("row");
        for (int row = 0; row < nodeList.getLength(); row++) {
            Node node = nodeList.item(row);
            
            Element element = (Element) node;
            
            for(int col = 0; col < 9; col++) {
                matrix[row][col] = Integer.valueOf(element.getElementsByTagName("cell").item(col).getTextContent());
            }
	}
        return matrix;
    }
}
