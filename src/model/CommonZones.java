package model;

public class CommonZones {

    private String name;
    private int capacity;
    //Horarios


    public CommonZones(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public CommonZones name(String name) {
        setName(name);
        return this;
    }

    public CommonZones capacity(int capacity) {
        setCapacity(capacity);
        return this;
    }


    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", capacity='" + getCapacity() + "'" +
            "}";
    }

    
}
