/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analizador;

import java.util.Collections;
import java.util.LinkedList;
import juego.Coordenada;

/**
 *
 * @author Robert Hernandez
 */
public class prueva {

    public static void main(String[] args) {
        LinkedList<Integer> lista = new LinkedList<>();
        
        Collections.addAll(lista, 1,2,3);
        
        for(int n : lista){
            System.out.println(n);
        }

    }
}
