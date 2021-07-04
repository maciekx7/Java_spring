package com.company.tests;

import com.company.trees.Conifer;
import com.company.trees.Tree;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TreeTest {
    Tree tree;


    @Test
    @DisplayName("Testing trunk existing")
    void TestTrunk() {
        assertTrue(tree.isTrunkGrown(), "Trunk shoud exists after tree plantation");
    }

}