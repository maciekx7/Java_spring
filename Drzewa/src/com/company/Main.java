package com.company;

import com.company.trees.Conifer;
import com.company.trees.LeafyTree;
import com.company.trees.Tree;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Tree> trees = new ArrayList<Tree>();
        trees.add(new LeafyTree("Dąb Bartek"));
        trees.add(new Conifer("Sososna Alina"));
        trees.get(0).grow(null);
        trees.get(0).present();


    }
}
