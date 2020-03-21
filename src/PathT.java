/**
   @brief A module that provides an ADT to represent a path from one "node" to another.
   @author Omar Alkersh
   @file PathT.java
   @date 2020-03-20
 */

import java.util.ArrayList;

/**
   @brief A class to represent a path from one node to another.
 */
public class PathT {
    private ArrayList<NodeT> path;

    /**
       @brief The constructor for PathT.
    */
    public PathT(){
        path = new ArrayList<>();
    }

    /**
     * @return the path
     */
    public ArrayList<NodeT> getPath() {
        return path;
    }

    /**
       @brief Add a page to the end of the path.
       @param node∶ NodeT The node to add to the path.
     */
    public void addPage(NodeT node){
        path.add(node);
    }

    /**
       @brief The length of the path.
       @return The length of the path.
     */
    public int length(){
        return path.size();
    }

    /**
       @brief Checks if any of the nodes' page names contain the string.
       @param str∶ String The string to search for.
       @return True if any node contains the string.
     */
    public boolean contains(String str){
        for(NodeT node: path)
            if(node.getPageName().contains(str))
                return true;
        return false;
    }
}
