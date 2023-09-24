package uga.cs4370.mydb;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class RelationImp implements Relation {

    public String name;
    public int size;
    public HashMap<String, List<Cell>> table;

    public RelationImp() {
        table = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public List<List<Cell>> getRows() {
        List<List<Cell>> deepCopy = new ArrayList<List<Cell>>();
        for (List<Cell> list : table.values()) {
            deepCopy.add(list);
        }
        return deepCopy;

    }

    public List<Type> getTypes() {
        return null;
    }

    public List<String> getAttrs() {
        List<String> list = new ArrayList<>();
        for (String key : table.keySet()) {
            list.add(key);
        }
        return list;
    }

    public boolean hasAttr(String attr) {
        return table.containsKey(attr);
    }

    public int getAttrIndex(String attr) {
        int i = 0;
        for (String key : table.keySet()) {
            if (attr.equals(key))
                return i;
            else
                i++;
        }
        throw new IllegalArgumentException("no attr");
    }

    public void insert(Cell... cells) {

    }

    public void insert(List<Cell> cells) {

    }

    public void print() {

    }
}
