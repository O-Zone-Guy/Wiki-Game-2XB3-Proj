
import java.util.HashSet;

public class NodeT {
    private int id;
    private String name;
    private HashSet<Integer> neighbours;

    public NodeT(int id, String name, HashSet<Integer> neihbours){

        this.id = id;
        this.name = name;
        this.neighbours = neighbours;

    }

	/**
	 * @return the id
	 */
	public int gePageNumber() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getPageName() {
		return name;
	}


	/**
	 * @return the neighbours
	 */
	public HashSet<Integer> getNeighbours() {
		return neighbours;
	}

}
