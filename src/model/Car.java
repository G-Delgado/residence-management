package model;

public class Car {

    private String licensePlate;



    public Car(String licensePlate) {
        this.licensePlate = licensePlate;
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
        return this.licensePlate;
    }


    
}
