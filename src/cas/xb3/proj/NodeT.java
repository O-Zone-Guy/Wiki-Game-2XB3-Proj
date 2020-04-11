package cas.xb3.proj;

/**
   @brief A module that provides an ADT to represent a node in the graph.
   @file NodeT.java
   @author Omar Alkresh - alkersho
   @date 2020-03-20
*/

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
   @brief A class to represent a node in the graph. Contains the name, number, and neighbours of the node.
*/
public class NodeT {
    private int id;
    private String name;
    private Set<Integer> neighbours;

    /**
       @brief A constructor for the NodeT
       @param id: int The node ID/Number
       @param name: String The name of the wiki page.
       @param neighbours: Collection<Integer> The collection of the IDs of the neighbours.
    */
    public NodeT(int id, String name, Collection<Integer> neighbours){
        this.id = id;
        this.name = name;
        this.neighbours = new HashSet<>(neighbours);
    }

    /**
     * @return the id of the page.
     */
    public int getPageNumber() { return id; }

    /**
     * @return the name of the page
     */
    public String getPageName() { return name; }

    /**
     * @return the neighbours
     */
    public Collection<Integer> getNeighbours() { return neighbours; }

    /**
       @brief Checks if two NodeT objects are equal.
       @param The object to compare to.
       @return Whether the two objects are equal.
    */
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (! (other instanceof NodeT))
            return false;
        return this.id == ((NodeT) other).getPageNumber();
    }
    /**
       @brief The Hash function for the node
       @return The hash, its id.
    */
    public int hashCode(){
        return id;
    }

}
