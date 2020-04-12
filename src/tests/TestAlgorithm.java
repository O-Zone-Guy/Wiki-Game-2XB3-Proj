package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import org.junit.Test;

import cas.xb3.proj.NodeT;
import cas.xb3.proj.PathT;
import cas.xb3.proj.alg.Algorithms;

public class TestAlgorithm {

    @Test
    public void testSort(){
        ArrayList<String> list = new ArrayList<>();
        ArrayList<String> list2 = new ArrayList<>(list);

        Algorithms.sort(list);

        assertEquals(list, list2);
    }

    @Test
    public void testSort2(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);
        list.add(5);
        list.add(7);
        list.add(2);
        list.add(8);

        ArrayList<Integer> list2 = new ArrayList<>(list);
        Algorithms.sort(list);
        Collections.sort(list2);

        assertEquals(list, list2);
    }

    @Test
    public void testSortF(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);
        list.add(5);
        list.add(7);
        list.add(2);
        list.add(8);

        ArrayList<Integer> list2 = new ArrayList<>(list);
        Algorithms.sort(list);

        assertNotSame(list2, list);
    }

    @Test
    public void testPathSearch(){
        NodeT n = new NodeT(0, "test", new HashSet<>());
        PathT p = new PathT();

        p.addPage(n);

        HashSet<PathT> pSet = new HashSet<>();

        pSet.add(p);

        assertEquals(0, Algorithms.searchPaths(pSet, "noTests").size());
    }

    @Test
    public void testPathSearch2(){
        NodeT n = new NodeT(0, "test", new HashSet<>());
        PathT p = new PathT();

        p.addPage(n);

        HashSet<PathT> pSet = new HashSet<>();

        pSet.add(p);

        assertEquals(1, Algorithms.searchPaths(pSet, "test").size());
    }

    @Test
    public void testPathSearch3(){
        NodeT n = new NodeT(0, "test", new HashSet<>());
        PathT p = new PathT();

        p.addPage(n);

        HashSet<PathT> pSet = new HashSet<>();

        pSet.add(p);

        assertEquals(1, Algorithms.searchPaths(pSet, "st").size());
    }
}
