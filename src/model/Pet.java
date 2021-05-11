package model;

import java.io.Serializable;

public class Pet implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1;
	private String name;
    private TypePet type;



    public Pet(String name, TypePet type) {
        this.name = name;
        this.type = type;
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

    
}
