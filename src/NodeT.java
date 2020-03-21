/**
   @brief A module that provides an ADT to represent a node in the graph.
   @file NodeT.java
   @author Omar Alkresh - alkersho
   @date 2020-03-20
 */

import java.util.Collection;

/**
   @brief A class to represent a node in the graph. Contains the name, number, and neighbours of the node.
 */
public class NodeT {
    private int id;
    private String name;
    private Collection<Integer> neighbours;

    /**
       @brief A constructor for the NodeT
       @param id∶ int The node ID/Number
       @param name∶ String The name of the wiki page.
       @param neighbours∶ Collection<Integer> The collection of the IDs of the neighbours.
     */
    public NodeT(int id, String name, Collection<Integer> neihbours){

        this.id = id;
        this.name = name;
        this.neighbours = neighbours;

    }

	/**
	 * @return the id of the page.
	 */
	public int gePageNumber() {
		return id;
	}

	/**
	 * @return the name of the page
	 */
	public String getPageName() {
		return name;
	}


	/**
	 * @return the neighbours
	 */
	public Collection<Integer> getNeighbours() {
		return neighbours;
	}

}
