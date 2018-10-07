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

public class Main {

	/**
	 * @param args starts class HalfOrder 
	 */
	public static void main(String[] args)
	{
		HalfOrder t = new HalfOrder();
		t.initOne();
		
		DijkstraAlgorithm dA = new DijkstraAlgorithm(t,null,null);
		


	}

}