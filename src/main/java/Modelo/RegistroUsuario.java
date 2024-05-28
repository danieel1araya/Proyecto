/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class RegistroUsuario {
    private ArrayList<Usuario> listaUsuarios;
    private String mensaje="";
    private String ruta = "usuarios.json";
    
    public RegistroUsuario(){
        listaUsuarios = leerJSON();
    }
    
    public String agregarUsuario(Usuario usuario) {
        if (usuario != null) {
            if (buscarUsuario(usuario.getId()) == null) {      
                this.listaUsuarios.add(usuario);
                escribirJSON();
                mensaje = "Usuario agregado\nReinicie el programa";
            } else {
                mensaje = "El usuario ya existe";
            }
        } else {
            mensaje = "Error";
        }
        return mensaje;
    }
  
    public Usuario buscarUsuario(String id) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getId().equalsIgnoreCase(id)) {
                Usuario usuario = listaUsuarios.get(i);
                return usuario;
            }
        }
        return null;
    }
    
    public boolean verificarContrasena(String contrasena) {
        for (int i = 0; i < listaUsuarios.size(); i++) {
            if (listaUsuarios.get(i).getContrasena().equalsIgnoreCase(contrasena)) {
                return true;
            }
        }
        return false;
    }
    

    public String borrarUsuario(Usuario usuario) {
        if (buscarUsuario(usuario.getId()) == null) {
            this.listaUsuarios.remove(buscarUsuario(usuario.getId()));
            escribirJSON();
            mensaje = "El usuario ha sido removido";
        } else {
            mensaje = "El usuario no se encuentra registrado";
        }
        return mensaje;
    }
    
    public void editarUsuario(Usuario usuario) {
        borrarUsuario(usuario);
        agregarUsuario(usuario);
        escribirJSON();
    }
    
    public String[][] getInfo(Usuario usuario){
       String[][] matrizInfo = new String[1][Usuario.NOMBRES_USUARIO.length];
          matrizInfo[0][0] = usuario.getId();
          matrizInfo[0][1] = usuario.getNombre();
          matrizInfo[0][2] = usuario.getContrasena();
          matrizInfo[0][3] = usuario.getSaldo()+"";
       return matrizInfo;
    }
    
    public String[][] getInfoServicios(Usuario usuario){
       ArrayList<Servicio> listaServicios = usuario.getServicios();
       String[][] matrizInfo = new String[listaServicios.size()][Servicio.NOMBRES_SERVICIOS.length];
       
       for(int x = 0; x<matrizInfo.length;x++){
           matrizInfo[x][0] = listaServicios.get(x).getTipoServicio();
           matrizInfo[x][1] = ""+listaServicios.get(x).getNumeroFactura();
           matrizInfo[x][2] = ""+listaServicios.get(x).getMonto();
           matrizInfo[x][3] = listaServicios.get(x).getFecha();
       }
       return matrizInfo;
    }
    
    
    public void escribirJSON() {
        JSONArray jsonArray = new JSONArray();
       
        for (Usuario usuario : listaUsuarios) {
        JSONObject newUserObject = new JSONObject();
        newUserObject.put("id", usuario.getId());
        newUserObject.put("nombre", usuario.getNombre());
        newUserObject.put("contrasena", usuario.getContrasena());
        newUserObject.put("saldo", usuario.getSaldo());

        JSONArray serviciosArray = new JSONArray();
        for (Servicio servicio : usuario.getServicios()) {
            JSONObject servicioObject = new JSONObject();
            servicioObject.put("tipoServicio", servicio.getTipoServicio());
            servicioObject.put("numeroFactura", servicio.getNumeroFactura());
            servicioObject.put("monto", servicio.getMonto());
            servicioObject.put("fecha", servicio.getFecha());
            serviciosArray.add(servicioObject);
        }
        newUserObject.put("servicios", serviciosArray);

        jsonArray.add(newUserObject);
    }

            try (FileWriter file = new FileWriter(ruta)) {
                file.write(jsonArray.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        
    }

  public ArrayList<Usuario> leerJSON() {
    ArrayList<Usuario> listaU = new ArrayList<>();
    JSONParser parser = new JSONParser();

    try (FileReader reader = new FileReader(ruta)) {
        Object obj = parser.parse(reader);
        JSONArray jsonArray = (JSONArray) obj;

        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            String id = (String) jsonObject.get("id");
            String nombre = (String) jsonObject.get("nombre");
            String contrasena = (String) jsonObject.get("contrasena");
            double saldo = (Double) jsonObject.get("saldo");

            JSONArray serviciosArray = (JSONArray) jsonObject.get("servicios");
            ArrayList<Servicio> servicios = new ArrayList<>();
            if (serviciosArray != null) {
                for (Object servObj : serviciosArray) {
                    JSONObject servicioObject = (JSONObject) servObj;
                    String tipoServicio = (String) servicioObject.get("tipoServicio");
                    int numeroFactura = ((Long) servicioObject.get("numeroFactura")).intValue();
                    double monto = (Double) servicioObject.get("monto");
                    String fecha = (String) servicioObject.get("fecha");
                    Servicio servicio = new Servicio(tipoServicio, numeroFactura, monto, fecha);
                    servicios.add(servicio);
                }
            }

            Usuario usuario = new Usuario(id, nombre, contrasena, saldo);
            usuario.getServicios().addAll(servicios);
            listaU.add(usuario);
        }
    } catch (IOException | ParseException e) {
        e.printStackTrace();
    }

    return listaU;
}
    
}
