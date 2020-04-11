package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import cas.xb3.proj.NodeT;
import cas.xb3.proj.PathT;


public class TestPathT {

    private PathT path;

    @Before
    public void init(){
        path = new PathT();
    }

    @Test
    public void testGet(){
        assertEquals(new ArrayList<PathT>(), path.getPath());
    }

    @Test
    public void testAddNode(){
        NodeT n = new NodeT(0, "test", new HashSet<Integer>());
        ArrayList<NodeT> nodes = new ArrayList<>();
        nodes.add(n);

        path.addPage(n);
        assertEquals(nodes, path.getPath());
    }

    @Test
    public void testSetPath(){
        NodeT n = new NodeT(0, "test", new HashSet<Integer>());
        ArrayList<NodeT> nodes = new ArrayList<>();

        path.addPage(n);
        path.setPath(nodes);

        assertEquals(nodes, path.getPath());
    }

}
