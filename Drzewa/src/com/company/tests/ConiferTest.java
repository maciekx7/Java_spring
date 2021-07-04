package com.company.tests;

import com.company.trees.Conifer;
import com.company.trees.LeafyTree;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConiferTest extends TreeTest{
    private Conifer conifer;
    private String coniferName = "Sosna Alina";

    @BeforeEach
    public void setUp() throws Exception {
        tree = new Conifer(coniferName);
    }

    @Test
    @DisplayName("Testing leafyTree name")
    void nameTesting() {
        assertEquals(coniferName, tree.GetTreeName(),"Tree not set after initialization");
    }


    @Test
    @DisplayName("Grow testing")
    void growTesting() {
        Conifer conifer = (Conifer) tree;
        assertEquals(0, tree.GetBranchesNumber(), "Branches should be 0 before first growing");
        assertEquals(0,conifer.GetNeedlesNumber(),"Needles should be 0 before first growing");

        conifer.grow(2);
        assertEquals(2, tree.GetBranchesNumber(), "Branches should be 2 before first growing");
        assertEquals((2*2),conifer.GetNeedlesNumber(),"Needles should be 4 before first growing");

        conifer.grow(3);
        assertEquals((2+3), tree.GetBranchesNumber(), "Branches should be 2 before first growing");
        assertEquals((2*2+3*2),conifer.GetNeedlesNumber(),"Needles should be 4 before first growing");
    }
}