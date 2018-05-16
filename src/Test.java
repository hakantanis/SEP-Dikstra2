import com.sun.javafx.collections.ArrayListenerHelper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Test


		{

			public static void main (String[] args)
			{

				ArrayList<Node> nodelist = new ArrayList<Node>();

				nodelist.add(new Node("Knoten 1 "));
				nodelist.add(new Node("Knoten 2 "));
				nodelist.add(new Node("Knoten 3 "));
				nodelist.add(new Node("Knoten 4 "));

			//	k1.name("Knoten1");
				nodelist.get(0).setX(100);
				nodelist.get(0).setY(200);
				nodelist.get(1).setX(200);
				nodelist.get(1).setY(400);
				nodelist.get(2).setX(300);
				nodelist.get(2).setY(600);
				nodelist.get(3).setX(400);
				nodelist.get(3).setY(800);

				for(int i = 0; i <= nodelist.size();i++)
				{
					if (nodelist.size() >= i)
					{
						System.out.println(nodelist.get(i));
					}
					else{
						System.out.println("--------");
					}

				}

			ArrayList<Edge> edgelist = new ArrayList<Edge>();



				edgelist.add(new Edge(8.0));
				edgelist.add(new Edge (10.0));
				edgelist.add(new Edge (5.0));

				edgelist.get(0);
				edgelist.get(1);
				edgelist.get(2);

				edgelist.get(0).setDestination(nodelist.get(1));
				edgelist.get(0).setSource(nodelist.get(0));
				edgelist.get(1).setDestination(nodelist.get(2));
				edgelist.get(1).setSource(nodelist.get(1));
				edgelist.get(2).setSource(nodelist.get(0));
				edgelist.get(2).setDestination(nodelist.get(2));


						System.out.println(edgelist);


					}

				}
/*
				System.out.println(n1);
				//Kanten von Knoten 1 ZU:
				System.out.println(e1);
				System.out.println(e3);

				System.out.println(n2);
				//Kanten von Knoten 2 ZU:
				System.out.println(e2);

				System.out.println(n3);


*/


				
				


			

		
	
	
	
