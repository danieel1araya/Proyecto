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
                mensaje = "Usuario agregado";
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
    
    
    public void escribirJSON() {
        JSONArray jsonArray = new JSONArray();
       
        for (int i = 0; i < listaUsuarios.size(); i++) {
            JSONObject newObject = new JSONObject();

            newObject.put("id", listaUsuarios.get(i).getId());
            newObject.put("nombre", listaUsuarios.get(i).getNombre());
            newObject.put("contrasena", listaUsuarios.get(i).getContrasena());
            newObject.put("saldo", listaUsuarios.get(i).getSaldo());
            newObject.put("servicio", listaUsuarios.get(i).getServicio());
             
            jsonArray.add(newObject);

            try (FileWriter file = new FileWriter(ruta)) {
                file.write(jsonArray.toJSONString());
                file.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
                Servicio servicio = (Servicio) jsonObject.get("servicio");
                
                Usuario usuario = new Usuario(id,nombre,contrasena,saldo,servicio);
                listaU.add(usuario);
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return listaU;
    }
    
}
