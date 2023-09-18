package uga.cs4370.mydb;

/**
 * Represents a cell in a row of a relation.
 */
public class Cell {
    
    private Object data;
    private Type type;

    /**
     * Initialize a cell with an integer.
     */
    public Cell(int data) {
        this.data = data;
        this.type = Type.INTEGER;
    }

    /**
     * Initialize a cell with a double.
     */
    public Cell(double data) {
        this.data = data;
        this.type = Type.DOUBLE;
    }

    /**
     * Initialize a cell with a String.
     */
    public Cell(String data) {
        this.data = data;
        this.type = Type.STRING;
    }

    /**
     * Returns the cell value as an integer.
     */
    public int getAsInt() {
        if (type != Type.INTEGER) {
            throw new RuntimeException("Illegal cell type conversion.");
        }
        return (int) data;
    }

    /*
     * Returns the cell value as a double.
     */
    public double getAsDouble() {
        if (type != Type.DOUBLE) {
            throw new RuntimeException("Illegal cell type conversion.");
        }
        return (double) data;
    }

    /**
     * Returns the cell value as a String.
     */
    public String getAsString() {
        if (type != Type.STRING) {
            throw new RuntimeException("Illegal cell type conversion.");
        }
        return (String) data;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Cell that = (Cell)obj;
        return type == that.type && data.equals(that.data);
    }

    @Override
    public int hashCode() {
        return (data.toString() + ':' + type.name()).hashCode();
    }

    @Override
    public String toString() {
        return data.toString();
    }

}
