package com.company.tests;

import com.company.trees.Conifer;
import com.company.trees.LeafyTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LeafyTreeTest extends TreeTest {
    private LeafyTree leafyTree;
    private String leafyTreeName = "Dąb Bartek";

    @BeforeEach
    public void setUp() throws Exception {
        tree = new LeafyTree(leafyTreeName);
    }

    @Test
    @DisplayName("Testing leafyTree name")
    void nameTesting() {
        assertEquals(leafyTreeName, tree.GetTreeName(),"Tree not set after initialization");
    }

    @Test
    @DisplayName("Grow testing")
    void growTesting() {
        LeafyTree leafyTree = (LeafyTree) tree;
        assertEquals(0, tree.GetBranchesNumber(), "Branches should be 0 before first growing");
        assertEquals(0,leafyTree.GetLeavesNumber(),"Leaves should be 0 before first growing");

        leafyTree.grow(2);
        assertEquals(2, tree.GetBranchesNumber(), "Branches should be 2 after first growing");
        assertEquals((2*2),leafyTree.GetLeavesNumber(),"Leaves should be 4 after first growing");

        leafyTree.grow(3);
        assertEquals((2+3), tree.GetBranchesNumber(), "Branches should be 2 after second growing");
        assertEquals((2*2+3*2),leafyTree.GetLeavesNumber(),"Leaves should be 4 after second growing");
    }

}
