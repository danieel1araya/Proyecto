/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class RegistroServicio {
    ArrayList<Servicio> listaServicios = new ArrayList<>();
    private String rutaArchivo = "servicios.csv";

    public RegistroServicio() {
        try {
            listaServicios = leerCSV(rutaArchivo);
        } catch (IOException ex) {
            System.err.println("Error al leer el archivo CSV: " + ex.getMessage());
        }
    }

    public String agregarServicio(Servicio servicio) throws IOException {
        String mensaje = "Error al agregar el servicio";
        if (servicio != null) {
            if (verificarServicio(servicio.getNumeroFactura())) {
                mensaje = "Ya existe una factura con ese número";
            } else {
                listaServicios.add(servicio);
                mensaje = "Se guardó correctamente la factura";
                escribirCSV(listaServicios, rutaArchivo);
            }
        } else {
            mensaje = "El servicio no puede ser nulo";
        }
        return mensaje;
    }

    public boolean verificarServicio(int numeroFactura) {
        for (Servicio servicio : listaServicios) {
            if (servicio.getNumeroFactura() == numeroFactura) {
                return true;
            }
        }
        return false;
    }

    public static void escribirCSV(ArrayList<Servicio> servicios, String rutaArchivo) throws IOException {
        try (CSVWriter writer = new CSVWriter(new FileWriter(rutaArchivo))) {
            StatefulBeanToCsv<Servicio> beanToCsv = new StatefulBeanToCsvBuilder<Servicio>(writer).build();
            try {
                beanToCsv.write(servicios);
            } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException ex) {
                System.err.println("Error al escribir el archivo CSV: " + ex.getMessage());
            }
        }
    }

    public static ArrayList<Servicio> leerCSV(String rutaArchivo) throws IOException {
        try (CSVReader reader = new CSVReader(new FileReader(rutaArchivo))) {
            CsvToBean<Servicio> csvToBean = new CsvToBeanBuilder<Servicio>(reader)
                    .withType(Servicio.class)
                    .build();
            return new ArrayList<>(csvToBean.parse());
        }
    }
}
