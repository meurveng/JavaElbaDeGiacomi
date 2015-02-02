/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import javafx.scene.layout.BorderPane;
/**
 *
 * @author meurveng
 */
public class BorderPaneApartment extends BorderPane{
    private String code;
    
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code=code;
    }
}
