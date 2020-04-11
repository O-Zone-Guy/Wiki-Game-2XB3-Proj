package tests;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import cas.xb3.proj.NodeT;

public class TestNodeT {
    private NodeT n1;

    @Before
    public void init(){
        HashSet<Integer> set = new HashSet<>();
        n1 = new NodeT(0, "node1", set);
    }

    @Test
    public void testGetPageNumber(){
        assertEquals(0, n1.getPageNumber());
    }

    @Test
    public void testPageName(){
        assertEquals("node1", n1.getPageName());
    }

    @Test
    public void testGetNeighbours(){
        assertEquals(new HashSet<Integer>(), n1.getNeighbours());
    }

    @Test
    public void testHashCode(){
        assertEquals(0, n1.hashCode());
    }
}
