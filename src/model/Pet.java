package model;

import java.io.Serializable;

public class Pet implements Serializable, Comparable<Pet>{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1;
	private String name;
    private TypePet type;



    public Pet(String name, String type) {
        this.name = name;
        this.type = TypePet.valueOf(type);
    }



    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypePet getType() {
        return this.type;
    }

    public void setType(TypePet type) {
        this.type = type;
    }
    
    @Override
    public String toString() {
    	return getName() + " - " + getType();
    }



	@Override
	public int compareTo(Pet p) {
		return getName().compareTo(p.getName());
	}

    
}
