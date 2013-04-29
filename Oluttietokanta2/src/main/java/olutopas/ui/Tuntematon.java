/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package olutopas.ui;

import java.util.Scanner;

/**
 *
 * @author kxkyllon
 */
public class Tuntematon implements Komento {

    private Scanner scanner;

    Tuntematon(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void suorita() {
        System.out.println("tuntematon komento");
    }
}
