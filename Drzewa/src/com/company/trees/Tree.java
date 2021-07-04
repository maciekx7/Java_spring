package com.company.trees;
import com.company.trees.elements.Branch;
import com.company.trees.elements.Trunk;

import java.util.ArrayList;
import java.util.List;

public abstract class Tree {
    protected String name;
    protected Trunk trunk; //pień
    protected List<Branch> branches;


    public Tree(String treeName) {
        branches = new ArrayList<Branch>();
        trunk = new Trunk();
        name = treeName;
        System.out.println("Tree " +  name + " planted!");
    }

    public void growBranches(Integer numberOfBranches) {
        if(numberOfBranches == null || numberOfBranches<=0) { return; }
        if(branches == null) {
            branches = new ArrayList<Branch>();
        }
        for(int i=0; i<numberOfBranches; i++) {
            branches.add(new Branch());
        }
    }

    public boolean isTrunkGrown(){
        if(trunk == null) {
            return false;
        } else {
            return true;
        }
    }

    public String GetTreeName() {
        return name;
    }

    public int GetBranchesNumber() {
        if(branches == null) { return 0; }
        return branches.size();
    }


    public abstract void grow(Integer numberOfBranches);

    public abstract void present();



}
