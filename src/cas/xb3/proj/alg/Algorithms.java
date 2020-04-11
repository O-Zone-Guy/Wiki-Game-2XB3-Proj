package cas.xb3.proj.alg;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import cas.xb3.proj.NodeT;
import cas.xb3.proj.PathT;

/**
 @brief A class responsible for providing the different algorithm implementations used in the project
 All methods should be static.
 */
public class Algorithms{


    /**
     @brief finds the set of shortest path between src and dest.

     @param src The number of the source node.
     @param dest the number of the destination node.

     @return The set of shortest paths.
      * @throws SQLException
     */
    public static Set<PathT> getPaths(int src, int dest) throws SQLException{
        Set<PathT>              paths = new HashSet<>(); // the set of paths to be returned.

        if (src == dest){
            PathT path = new PathT();
            NodeT node = SQLHandler.getNode(src);
            path.addPage(node);
            paths.add(path);
            return paths;
        }

        HashMap<NodeT, NodeT>   prev  = new HashMap<>(); // stores the previous node in the shortest path
        HashMap<NodeT, Integer> dist  = new HashMap<>(); // the distance from the source node

        Queue<NodeT> toVisit = new LinkedList<>(); // a queue of the nodes to be visited.

        NodeT srcN;
        try{
            srcN = SQLHandler.getNode(src);
        } catch (SQLException e){
            throw new SQLException("Source node not found");
        }

        prev.put(srcN, srcN);
        dist.put(srcN,  0);

        toVisit.add(srcN);

        int foundLevel = Integer.MAX_VALUE; // keeps track at which level the destination was found.

        while (!toVisit.isEmpty()){

            NodeT v = toVisit.poll(); // get node to process.

            // don't bother checking neighbours if dest is found on a lower level.
            if (foundLevel < dist.get(v))
                continue;

            if (dist.get(v) > 10)
            	break;
            // Iterate over neighbours
            for (int w: v.getNeighbours()){
                NodeT nodeW = SQLHandler.getNode(w); // get node for each neighbour
                if (w == dest){ // found the destination.
                    if (dist.get(v) > foundLevel) // current distance is longer than the one it was found at, skip.
                        break;
                    foundLevel = dist.get(v);
                    prev.put(nodeW, v);
                    paths.add(getPath(dest, prev)); // get path and add it to set.
                }
                if (foundLevel <= dist.get(v)) // if we already found the dest, don't add anything to queue
                    continue;
                // adding node to queue
                // check if we found a shorter path the node
                if (dist.get(nodeW) != null){
                    int temp = dist.get(v) + 1;
                    if (temp >= dist.get(nodeW))
                        continue;
                }

                // if not, add it to the queue and set prev and dist maps.
                toVisit.add(nodeW);
                dist.put(nodeW, dist.get(v) + 1);
                prev.put(nodeW, v);
            }
        }

        return paths;
    }

    // A function to get the path from a prev hashmap
    private static PathT getPath(int dest, Map<NodeT, NodeT> prev) throws SQLException {
        ArrayList<NodeT> pathList = new ArrayList<>();

        NodeT n = SQLHandler.getNode(dest);
        while (!n.equals(prev.get(n))){
            pathList.add(0, n);
            n = prev.get(n);
        }

        PathT path = new PathT();
        path.setPath(pathList);

        return path;

    }

    /**
     @brief Sorts a set of paths based on the names of the pages in the path.
     @param paths ∶ Collection<PathT> The set of paths to sort.
     @return ArrayList<PathT> paths The sorted paths based on the names of pages in each path.
     */
    public static ArrayList<PathT> sortPaths(Collection<PathT> paths){
        ArrayList<PathT> sortedPaths = new ArrayList<PathT>(paths);
        ArrayList<String> pathStrings = new ArrayList<>();

        // Turn the paths into string.
        for(PathT path: sortedPaths)
            pathStrings.add(pathToString(path));

        // selection sort
        // small list, no need for complicated sorting methods
        for (int i = 0; i< sortedPaths.size(); i++){
            int min = i;
            for (int j = i + 1; j < sortedPaths.size(); j++)
                if(pathStrings.get(j).compareTo(pathStrings.get(min)) < 0)
                    min = j;
            PathT tempP = sortedPaths.get(min);
            String tempS = pathStrings.get(min);

            sortedPaths.set(min, sortedPaths.get(i));
            pathStrings.set(min, pathStrings.get(i));
            sortedPaths.set(i, tempP);
            pathStrings.set(i, tempS);
        }

        return sortedPaths;
    }

    /**
     @brief Search the paths for a string.
     @details Keeps the paths that contain nodes that contain the given string in its name.
     @param paths The collection of paths to search.
     @param string The string to search for.
     @return Paths that contain the given string.
     */
    public static Set<PathT> searchPaths(Set<PathT> paths, String string){
        HashSet<PathT> filteredPaths = new HashSet<>(paths);
        filteredPaths.removeIf(p -> !p.contains(string));
        return filteredPaths;
    }

    private static String pathToString(PathT path){
        String pathString = "";

        for(NodeT node:path.getPath())
            pathString += node.getPageName();

        return pathString;
    }

    /**
       @brief Sorts a set of Comparable
       @param list The list of items to sort.
    */
    public static <T extends Comparable<T>> void sort(ArrayList<T> list){

        for (int i = 0; i< list.size(); i++){
            int min = i;
            for (int j = i + 1; j < list.size(); j++)
                if(list.get(j).compareTo(list.get(min)) < 0)
                    min = j;
            T temp = list.get(min);

            list.set(min, list.get(i));
            list.set(i, temp);
        }

    }
}
