package uga.cs4370.mydb;

import java.util.ArrayList;
import java.util.List;

public class RelationImp implements Relation {

    String name;
    int size;
    List<List<Cell>> table;

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public List<List<Cell>> getRows() {
        List<List<Cell>> deepCopy = new ArrayList<List<Cell>>();
        for (int i = 1; i < table.size(); i++) {
            List<Cell> row = table.get(i);
            deepCopy.add(row);
        }
        return deepCopy;

    }

    public List<Type> getTypes() {
        List<Type> types = new ArrayList<>();
        for (int i = 0; i < table.get(0).size(); i++) {
            types.add(table.get(0).get(i).)
        }
    }
}
