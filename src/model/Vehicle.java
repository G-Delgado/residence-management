package model;

public abstract class Vehicle {

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
        return "{" +
            " licensePlate='" + getLicensePlate() + "'" +
            "}";
    }

    public String toCSV(){
        return this.licensePlate+","+this.type;
    }


    public String getType() {
        return this.type;
    }

    
}
