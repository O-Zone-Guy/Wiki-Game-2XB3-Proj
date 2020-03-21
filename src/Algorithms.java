
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
   @brief A class responsible for providing the different algorithm implementations used in the project
   All methods should be static.
 */
public class Algorithms{

    private Algorithms(){

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
    public static Collection<PathT> searchPaths(Collection<PathT> paths, String string){
        HashSet<PathT> filteredPaths = new HashSet<>(paths);
        for(PathT path: filteredPaths)
            if(!path.contains(string))
                filteredPaths.remove(path);

        return filteredPaths;
    }

    private static String pathToString(PathT path){
        String pathString = "";

        for(NodeT node:path.getPath())
            pathString += node.getPageName();

        return pathString;
    }

}
