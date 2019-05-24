/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Scanner;
import menus.HeadmasterMenu;
import utils.InputUtils;
import utils.Utils;

/**
 *
 * @author Los_e
 */
public class School {

    Scanner sc = new Scanner(System.in);
    private User loggedinuser;

    public User getLoggedinuser() {
        return loggedinuser;
    }

    public void setLoggedinuser(User loggedinuser) {
        this.loggedinuser = loggedinuser;
    }

    public void initiate() {

        System.out.println("Welcome");

        while (loggedinuser == null) {

            System.out.println("Please log in.");
            setLoggedinuser(Utils.logIn(sc));
            System.out.println(loggedinuser.toString());

        }

        System.out.println("Welcome " + loggedinuser.getUsername() + "!");
        
        if (loggedinuser.getRole().equals("headmaster")) {HeadmasterMenu.headmasterMainMenu(sc);}
        

    }
}
