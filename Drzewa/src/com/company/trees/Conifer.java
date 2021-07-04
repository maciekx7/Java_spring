package com.company.trees;

import com.company.trees.elements.Needle;

import java.util.ArrayList;
import java.util.List;

//Drzewo iglaste
public class Conifer extends Tree{
    protected List<Needle> needles;

    public Conifer(String name) {
        super(name);
        needles = new ArrayList<Needle>();
    }

    @Override
    public void grow(Integer numberOfBranches) {
        growBranches(numberOfBranches);
        if(numberOfBranches == null || numberOfBranches <=0) { return; }
        for(int i=0; i<numberOfBranches*2; i++) {
            needles.add(new Needle());
        }
    }

    @Override
    public void present() {
        System.out.println("--------------------");
        System.out.println("I'm Confier " + name);
        System.out.println("Trunk: " + isTrunkGrown());
        System.out.println("Branches: " + branches.size());
        System.out.println("Needles: " + needles.size());
        System.out.println("--------------------");
    }

    public int GetNeedlesNumber() {
        if(needles == null) { return 0; }
        return needles.size();
    }
}
