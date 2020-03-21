
import java.util.ArrayList;

public class PathT {
    private ArrayList<NodeT> path;

    public PathT(){
        path = new ArrayList<>();
    }

	/**
	 * @return the path
	 */
	public ArrayList<NodeT> getPath() {
		return path;
	}

    public void addPage(NodeT node){
        path.add(node);
    }

    public int length(){
        return path.size();
    }

    public boolean contains(String str){
        for(NodeT node: path)
            if(node.getPageName().contains(str))
                return true;
        return false;
    }
}
