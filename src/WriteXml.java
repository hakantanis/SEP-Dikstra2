
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;


/**
 * Project Dijkstra Algorithm
 * This class is used to start the project
 *
 * @author Hakan Tanis
 * @author Kevin Adamczewski
 * @author Jonas Litmeyer
 * Date 30.05.2018
 * @version 3.0
 *
 * Last Change:
 * by: Kevin Adamczewski
 * date: 04.06.2018
 */
public class WriteXml
{

    /**
     * Create xml.
     */
    public void createXML()
    {
        HalfOrder halfOrder = new HalfOrder();
        halfOrder.init();

        try
        {
            DocumentBuilderFactory dbF = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbF.newDocumentBuilder();

            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("Nodes");
            doc.appendChild(rootElement);

            Element nodes = doc.createElement("Node");
            rootElement.appendChild(nodes);

        
			for (int i = 0; i < halfOrder.nodes.size(); i++)            
			{

                Node node = halfOrder.returnNode(i);
                System.out.println(i + ". Node = " + node);
                node.generateXml(doc, nodes);
            }

            Element edges = doc.createElement("Edge");
            rootElement.appendChild(edges);
            for (int i = 0; i < halfOrder.edges.size() ; i++)
            {
                Edge edge = halfOrder.returnEdge(i);
                System.out.println((i + 1) + ". Edge = " + edge);
                edge.generateXml(doc, edges);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:\\Users\\Kevin\\Desktop\\tsp.xml"));
            transformer.transform(source, result);
        } catch (Exception e)
        {
            e.printStackTrace();

        }
    }


    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args)
    {
        WriteXml xml = new WriteXml();
        xml.createXML();
    }

}
