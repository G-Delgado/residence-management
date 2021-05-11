package ui;


import java.io.IOException;

import model.ResidenceManagement;

public class Main {
    
    public static void main(String[] args) throws IOException {

     ResidenceManagement rm=new ResidenceManagement(null,4,10,4);
     rm.getAdmin();

     rm.importDataResidents("Test");
   

    }
        
    
}
