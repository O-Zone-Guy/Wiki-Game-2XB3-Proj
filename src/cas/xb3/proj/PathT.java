package cas.xb3.proj;

/**
   @brief A module that provides an ADT to represent a path from one "node" to another.
   @author Omar Alkersh
   @file PathT.java
   @date 2020-03-20
 */

import java.util.ArrayList;
import java.util.List;

/**
   @brief A class to represent a path from one node to another.
 */
public class PathT {
    private List<NodeT> path;

    /**
       @brief The constructor for PathT.
    */
    public PathT(){
        path = new ArrayList<>();
    }

    /**
     * @return the path
     */
    public List<NodeT> getPath() {
        return path;
    }

    /**
       @brief Add a page to the end of the path.
       @param node: NodeT The node to add to the path.
     */
    public void addPage(NodeT node){
        path.add(node);
    }

    public void setPath(List<NodeT> p){
        this.path = p;
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
       @param str: String The string to search for.
       @return True if any node contains the string.
     */
    public boolean contains(String str){
        for(NodeT node: path)
            if(node.getPageName().contains(str))
                return true;
        return false;
    }


    /**
       @brief Checks if two PathT objects are equal.
       @param The object to compare to.
       @return Whether the two objects are equal.
     */
    public boolean equals(Object other){
        if (this == other)
            return true;
        if (! (other instanceof PathT))
            return false;
        PathT path2 = (PathT) other;
        return this.path.equals(path2.getPath());
    }

    /**
       @brief The hash function for PathT
       @return The hash of PathT
     */
    public int hashCode(){
        int hash = 0;
        for(int i = 0; i < path.size(); i++){
            hash+=path.get(i).hashCode()*primeNumber(i+1);
        }
        return hash;
    }

    private Integer primeNumber(int n){
        int num=1, count=0, i;
        while (count < n){
            num++;
            for (i = 2; i <= num; i++)
                if (num % i == 0)
                    break;
            if (i == num)
                count = count+1;
        }
        return num;
    }
}
