/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.RegistroServicio;
import Modelo.RegistroUsuario;
import Modelo.Usuario;
import Vista.FRMHome;
import Vista.FRMLogin;
import Vista.FRMRegistrarse;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManejadorMenu implements ActionListener{
    private FRMLogin login;
    private FRMRegistrarse frmRegistrarse;
    private FRMHome frmHome;
    private ManejadorHome manejador;
    private RegistroUsuario registro;
    private RegistroServicio registroS;
    private Usuario usuario;
    
    public ManejadorMenu(){
        this.registro = new RegistroUsuario();
        this.registroS = new RegistroServicio();
        this.frmRegistrarse = new FRMRegistrarse();
        this.frmHome = new FRMHome();
        this.login = new FRMLogin();
        this.login.setEscuchadores(this);
        this.login.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       switch(e.getActionCommand().toString()){
           case "Iniciar Sesión":
               if(this.login.verificarEspacios()){
                   this.login.mensaje("Por favor ingrese todos los datos");
               } else {
                   if(this.registro.buscarUsuario(this.login.getID())!=null){
                       usuario = this.registro.buscarUsuario(this.login.getID());
                       if(this.registro.verificarContrasena(this.login.getContrasena())){
                           this.manejador = new ManejadorHome(usuario,registro, registroS);
                           this.login.limpiar();
                           this.login.dispose();
                       } else {
                            this.login.mensaje("Contrasena incorrecta");
                       }
                   } else {
                        this.login.mensaje("No existe un usuario con ese id");
                   }
               }
           break;
           case "Registrarse":
              this.frmRegistrarse.setVisible(true);
           break;
       }
    }
}
