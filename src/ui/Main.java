package ui;


import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import exceptions.PasswordInvalidException;
import exceptions.UsernameInvalidException;
import model.ResidenceManagement;
import model.*;

public class Main {
    
    public static void main(String[] args) throws IOException, ArrayIndexOutOfBoundsException, UsernameInvalidException, PasswordInvalidException {

     ResidenceManagement rm=new ResidenceManagement(null,4,10,4);
        //Collections.sort(rm.getApartaments());
        //System.out.println(rm.getApartaments());
       Boolean x= rm.loginApartament("1002_2", "1234");
       System.out.println(x);
    }
        
    
}
