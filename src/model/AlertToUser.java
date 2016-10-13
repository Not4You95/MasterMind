/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.lang.RuntimeException;
/**
 *
 * @author jonas
 */
public class AlertToUser extends RuntimeException {
    private String alertInformation;
    
    public AlertToUser(String info){
        super(info);
        alertInformation = info;
    }
    
    /**
     *
     * @return
     */
    @Override
    public String getMessage(){
        return alertInformation;
    }

    
    
    
    
    
}
