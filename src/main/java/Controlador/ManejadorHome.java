/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.RegistroUsuario;
import Modelo.Servicio;
import Modelo.Usuario;
import Vista.FRMHome;
import Vista.FRMInformacion;
import Vista.FRMRegistroUsuario;
import Vista.FRMSaldo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ManejadorHome implements ActionListener{
    private Usuario usuarioA;
    private Servicio servicio;
    private RegistroUsuario registro;
    private ManejadorServicios manejadorServicios;
    private FRMHome home;
    private FRMSaldo saldo;
    private FRMInformacion frmInfo;
    
    public ManejadorHome(Usuario usuario, RegistroUsuario registroU){
        this.usuarioA = usuario;
        this.registro = registroU;
        this.frmInfo = new FRMInformacion();
        this.home = new FRMHome();
        this.home.setNombre(usuario);
        this.home.setEscuchadores(this);
        this.home.setVisible(true);
    }
            
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand().toString()){
            case "Pagar servicio":
                this.manejadorServicios = new ManejadorServicios(usuarioA);
            break;
            case "Agregar saldo":
               this.saldo = new FRMSaldo(usuarioA, registro);
               this.saldo.setSaldo();
            break;
            case "Consultar mis aervicios":
               
            break;
            case "Consultar mi informacion":
                this.frmInfo.setDataTable(this.registro.getInfo(usuarioA), Usuario.NOMBRES_USUARIO);
                this.frmInfo.setVisible(true);
            break;
            case "Modificar mi informacion":
               
            break;
            case "Cerrar Sesion":
                this.home.dispose();
            break;
        }
    }
}
