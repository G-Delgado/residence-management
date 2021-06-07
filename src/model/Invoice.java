
package model;

import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;


public class Invoice implements JRDataSource{

    private final List<Debt> debt;
    private int index; 
    private final String apto;
    
    public Invoice(Apartament apartament){
        index = -1;
        this.apto=apartament.getUsername();
        this.debt=apartament.getDebt();
    }
    
    @Override
    public boolean next() throws JRException {
        index++;
        return (index < debt.size());
    }

    @Override
    public Object getFieldValue(JRField jrf) throws JRException {
        
        Object value = null;
        
        String fieldName = jrf.getName();
        
        switch(fieldName){
            
            
            case "description":
                value = debt.get(index).getDescription();                
            break;
            
            case "apartament":
                value = apto;                
            break;
            
            case "total":
                value = debt.get(index).getPrice();              
            break;
            
        }
        
        return value;
    
    }

    
}
