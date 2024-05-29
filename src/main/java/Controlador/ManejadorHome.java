/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.RegistroServicio;
import Modelo.RegistroUsuario;
import Modelo.Servicio;
import Modelo.Usuario;
import Vista.FRMHome;
import Vista.FRMInformacion;
import Vista.FRMModificarInformacion;
import Vista.FRMRegistroUsuario;
import Vista.FRMSaldo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class ManejadorHome implements ActionListener{
    private Usuario usuarioA;
    private Servicio servicio;
    private RegistroUsuario registro;
    private RegistroServicio registroC;
    private ManejadorServicios manejadorServicios;
    private ManejadorMenu login;
    private FRMHome home;
    private FRMSaldo saldo;
    private FRMInformacion frmInfo;
    private FRMModificarInformacion frmModificar;
    
    public ManejadorHome(Usuario usuario, RegistroUsuario registroU, RegistroServicio registroCC){
        this.usuarioA = usuario;
        this.registro = registroU;
        this.registroC = registroCC;
        this.frmInfo = new FRMInformacion();
        this.frmModificar = new FRMModificarInformacion(usuarioA, registro);
        this.home = new FRMHome();
        this.home.setNombre(usuario);
        this.home.setEscuchadores(this);
        this.home.setVisible(true);
    }
            
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand().toString()){
            case "Pagar servicio":
                this.manejadorServicios = new ManejadorServicios(usuarioA, registro, registroC);
                this.home.dispose();
            break;
            case "Agregar saldo":
               this.saldo = new FRMSaldo(usuarioA, registro, registroC);
               this.home.dispose();
               this.saldo.setSaldo();
            break;
            case "Consultar mis servicios":
                this.frmInfo.setDataTable(this.registro.getInfoServicios(usuarioA), Servicio.NOMBRES_SERVICIOS);
                this.frmInfo.setVisible(true);
            break;
            case "Consultar mi informacion":
                this.frmInfo.setDataTable(this.registro.getInfo(usuarioA), Usuario.NOMBRES_USUARIO);
                this.frmInfo.setVisible(true);
            break;
            case "Modificar mi informacion":
               this.frmModificar.setInformacion(usuarioA);
               this.frmModificar.setVisible( true);
            break;
            case "Cerrar Sesion":
                this.home.dispose();
                this.login = new ManejadorMenu();
            break;
        }
    }
}
