/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import com.opencsv.bean.CsvBindByName;


public class Servicio {
    @CsvBindByName
    private String tipoServicio;
    @CsvBindByName
    private int numeroFactura;
    @CsvBindByName
    private double monto;
    @CsvBindByName
    private String fecha;

    public Servicio(String tipoServicio, int numeroFactura, double monto, String fecha) {
        this.tipoServicio = tipoServicio;
        this.numeroFactura = numeroFactura;
        this.monto = monto;
        this.fecha = fecha;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public int getNumeroFactura() {
        return numeroFactura;
    }

    public void setNumeroFactura(int numeroFactura) {
        this.numeroFactura = numeroFactura;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Servicio{" + "tipoServicio=" + tipoServicio + ", numeroFactura=" + numeroFactura + ", monto=" + monto + ", fecha=" + fecha + '}';
    }
    
    
    
}
