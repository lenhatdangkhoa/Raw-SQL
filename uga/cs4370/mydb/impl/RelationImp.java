package uga.cs4370.mydb.impl;

import uga.cs4370.mydb.Relation;
import uga.cs4370.mydb.Type;
import uga.cs4370.mydb.Cell;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.HashMap;

public class RelationImp implements Relation {

    public String name;
    public int size = 0;
    public LinkedHashMap<String, HashMap<Type, List<Cell>>> table;

    public RelationImp() {
        table = new LinkedHashMap<>();
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Set name of the relation.
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getSize() {

        return size;
    }

    @Override
    public List<List<Cell>> getRows() {
        List<List<Cell>> deepCopy = new ArrayList<List<Cell>>();
        for (HashMap<Type, List<Cell>> map : table.values()) {
            for (List<Cell> cells : map.values()) {
                deepCopy.add(cells);
            }
        }
        return deepCopy;

    }

    @Override
    public List<Type> getTypes() {
        List<Type> types = new ArrayList<>();
        for (HashMap<Type, List<Cell>> map : table.values()) {
            for (Type type : map.keySet()) {
                types.add(type);
            }
        }
        return types;
    }

    @Override
    public List<String> getAttrs() {
        List<String> list = new ArrayList<>();
        for (String key : table.keySet()) {
            list.add(key);
        }
        return list;
    }

    @Override
    public boolean hasAttr(String attr) {
        return table.containsKey(attr);
    }

    @Override
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

    @Override
    public void insert(Cell... cells) {
        List<Cell> list = new ArrayList<>();
        for (Cell cell : cells) {
            list.add(cell);
        }
        this.insert(list);
    }

    @Override
    public void insert(List<Cell> cells) {
        List<Type> types = this.getTypes();
        if (cells.size() != types.size())
            throw new IllegalArgumentException("Size of cells does not match size of attributes");
        int i = 0;
        int tempInt = 0;
        double tempDouble = 0.0;
        String tempString = "";
        for (Cell cell : cells) {
            try {
                tempInt = cell.getAsInt();

                if (types.get(i) == Type.INTEGER) {
                    i++;
                    continue;
                } else {
                    throw new IllegalArgumentException("Wrong format, not of type " + types.get(i));
                }

            } catch (RuntimeException ex) {
                try {
                    tempDouble = cell.getAsDouble();
                    System.out.println("here2");

                    if (types.get(i) == Type.DOUBLE) {
                        i++;
                        continue;
                    } else {
                        throw new IllegalArgumentException("Wrong format 2");
                    }
                } catch (RuntimeException ex2) {
                    try {
                        tempString = cell.getAsString();
                        if (types.get(i) == Type.STRING) {
                            i++;
                            continue;
                        } else {
                            return;
                        }
                    } catch (RuntimeException ex3) {
                        throw new IllegalArgumentException("Types do not match");
                    }
                }
            }

        }
        int j = 0;
        for (String key : table.keySet()) {
            HashMap<Type, List<Cell>> temp = table.get(key);
            for (Type type : temp.keySet()) {
                List<Cell> tempList = temp.get(type);
                tempList.add(cells.get(j));
                temp.put(type, tempList);
                j++;
            }
            table.put(key, temp);
        }
        for (HashMap<Type, List<Cell>> map : table.values()) {
            for (Type type : map.keySet()) {
                this.size = map.get(type).size();
                break;
            }
        }

    }

    @Override
    public void print() {
        System.out.print("|");
        int length = 0;
        for (String key : table.keySet()) {
            System.out.print(key + "    |");
            length += key.length() + 5;
        }
        System.out.println();
        for (int i = 0; i <= length; i++) {
            System.out.print("_");
        }
        List<Type> types = getTypes();
        List<String> keys = new ArrayList<>(table.keySet());
        int j = 0;
        System.out.println();
        while (j < table.get(keys.get(0)).get(types.get(0)).size()) {
            System.out.print("|");
            for (int x = 0; x < types.size() - 1; x++) {
                System.out.print(table.get(keys.get(x)).get(types.get(x)).get(j) + "\t|");
                // System.out.print(table.get(keys.get(i++)).get(types.get(i++)).get(j));
            }
            System.out.print(table.get(keys.get(types.size() - 1)).get(types.get(types.size() - 1)).get(j));
            System.out.println();
            for (int i = 0; i <= length; i++) {
                System.out.print("-");
            }
            System.out.println();

            j++;
        }
        System.out.println();
    }
}
