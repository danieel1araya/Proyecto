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
import Vista.FRMRegistroServicios;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danie
 */
public class ManejadorServicios implements ActionListener{
    private RegistroServicio registro;
    private RegistroUsuario registroU;
    private Servicio servicio;
    private FRMRegistroServicios frmServicio;
    private ManejadorHome manejador;
    private int monto = 0;
    private Usuario usuarioI;

    public ManejadorServicios(Usuario usuario,  RegistroUsuario registro, RegistroServicio registroC) {
        this.usuarioI = usuario;
        this.registro = registroC;
        this.registroU = registro;
        this.frmServicio = new FRMRegistroServicios();
        this.frmServicio.setEscuchadores(this);
        this.frmServicio.inicializarCampos();
        this.frmServicio.setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand().toString()){
            case "Pagar":
                 if(usuarioI.getSaldo()>=this.frmServicio.getMonto()){ 
                      servicio = this.frmServicio.getServicio();
                       try {
                         this.frmServicio.mensaje(this.registro.agregarServicio(servicio));
                         usuarioI.setSaldo(usuarioI.getSaldo()-this.frmServicio.getMonto());
                         usuarioI.addServicio(servicio);
                         this.registroU.editarUsuario(usuarioI);
                         this.frmServicio.dispose();
                         this.manejador = new ManejadorHome(usuarioI,registroU, registro);
                        } catch (IOException ex) {
                            Logger.getLogger(ManejadorServicios.class.getName()).log(Level.SEVERE, null, ex);
                        }
                 } else {
                     this.frmServicio.mensaje("Saldo insuficiente");
                     this.frmServicio.dispose();
                 }
            break;
            case "comboBoxChanged":
                switch(frmServicio.getSelectedItem()){
                    case "Electricidad":
                        monto = 15000;
                        this.frmServicio.setMonto(monto);
                    break;
                    case "Television":
                        monto = 22500;
                        this.frmServicio.setMonto(monto);
                    break;
                    case "Agua":
                        monto = 10000;
                        this.frmServicio.setMonto(monto);
                    break;
                    case "Internet":
                        monto = 30000;
                        this.frmServicio.setMonto(monto);
                    break;
                }
            break;
        }
        
    }
    
    
}
