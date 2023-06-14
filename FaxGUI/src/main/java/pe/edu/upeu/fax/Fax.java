/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package pe.edu.upeu.fax;

import pe.edu.upeu.modelo.FaxTO;

/**
 *
 * @author guitar
 */
public class Fax {
    public static void main(String args[]){
        FaxTO vista= new FaxTO();
        ModelGame model = new ModelGame();
        ViewGame view = new ViewGame();
        Controllergame controlador = new Controllergame(view, model);
        view.setLocationRelativeTo(null);
        view.setVisible(true);
        
    }
}