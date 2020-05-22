/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto.test;

import com.miguel.proyectojava.controller.createPlayerController;
import com.miguel.proyectojava.controller.menuController;
import com.miguel.proyectojava.controller.startController;
import com.miguel.proyectojava.model.Player;
import org.apache.commons.codec.digest.DigestUtils;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author migue
 */
public class test {
    Player p = new Player("pepe", "pepito", "123@gmail.com", "pepitop", "pepito");
    Player p2 = new Player("pepe", "3213213", "123@gmail.com", "pepito33333123p", "pe23213pito");
    Player p3 = new Player("pepe", "3213213", "123@gmail.com", "123", "1234");
    menuController m = new menuController();


    @Test
    public void testlogin() {
        startController s = new startController();  
        assertTrue(s.login("luque", DigestUtils.sha512Hex("luque")) == true);
        
        assertTrue(s.login("luque", DigestUtils.sha512Hex("djshdjksdhkjsadkasd")) == false);
    }
    
    @Test
    public void testsignup(){
        createPlayerController c = new createPlayerController();
        //true porque no existe en la base de datos este usuario
        assertTrue(c.signup(p.getName(), p .getLastname(), p.getEmail(), p.getUsername(), p.getPassword()) == true);
        
        //false porque ya lo he creado antes
        assertTrue(c.signup("pepe", "pepito", "123@gmail.com", "pepito", DigestUtils.sha512Hex("pepitop")) == false);
        
        //false porque no cumple con la validacion del email
        assertTrue(c.signup("pepe", "pepito", "123@", "pepito123", DigestUtils.sha512Hex("1234")) == false);
        
        //true porque no existe en la base de datos este usuario
        assertTrue(c.signup(p3.getName(), p3.getLastname(), p3.getEmail(), p3.getUsername(), p3.getPassword()) == true);

        
    }
     @Test
    public void testremovePlayer(){
        
        assertTrue(m.deletePlayer(p) == true);
        assertTrue(m.deletePlayer(p2) == false);
    }
     @Test
    public void testchangepassword(){
        m.changePassword(p3.getUsername(),"nuevacontraseña");
    }

}
