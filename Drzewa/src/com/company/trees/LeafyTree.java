package com.company.trees;

import com.company.trees.elements.Leaf;
import com.company.trees.elements.Needle;

import java.util.ArrayList;
import java.util.List;

public class LeafyTree extends Tree {
    protected List<Leaf> leaves;

    public LeafyTree(String name) {
        super(name);
        System.out.println("I'm LeafyTree!");
        leaves = new ArrayList<Leaf>();
    }

    @Override
    public void grow(Integer numberOfBranches) {
        growBranches(numberOfBranches);
        if(numberOfBranches == null || numberOfBranches <=0) { return; }
        for(int i=0; i<numberOfBranches*2; i++) {
            leaves.add(new Leaf());
        }


    }

    @Override
    public void present() {
        System.out.println("--------------------");
        System.out.println("I'm LeafyTree " + name);
        System.out.println("Trunk: " + isTrunkGrown());
        System.out.println("Branches: " + branches.size());
        System.out.println("Leaves: " + leaves.size());
        System.out.println("--------------------");
    }

    public int GetLeavesNumber() {
        if(leaves == null) { return 0; }
        return leaves.size();
    }

}


