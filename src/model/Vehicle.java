package model;

import java.io.Serializable;

public abstract class Vehicle implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String licensePlate;
    private String type;



    public Vehicle(String licensePlate,String type) {
        this.licensePlate = licensePlate;
        this.type=type;
    }

    public String getLicensePlate() {
        return this.licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }
    


    @Override
    public String toString() {
        /*return "{" +
            " licensePlate='" + getLicensePlate() + "'" +
            "}";*/
    	return getLicensePlate() + " - " + getType();
    }

    public String toCSV(){
        return this.licensePlate+","+this.type;
    }


    public String getType() {
        return this.type;
    }

    
}
