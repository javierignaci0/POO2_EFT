package model;

import java.util.Date;

public class Prestamo {
    private int id;
    private int idEstudiante;
    private int idLibro;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private boolean devuelto;
    
    public Prestamo(int id, int idEstudiante, int idLibro, Date fechaPrestamo, Date fechaDevolucion, boolean devuelto) {
        this.id = id;
        this.idEstudiante = idEstudiante;
        this.idLibro = idLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.devuelto = devuelto;
    }
    
    public Prestamo(int idEstudiante, int idLibro, Date fechaPrestamo, Date fechaDevolucion, boolean devuelto) {
        this.idEstudiante = idEstudiante;
        this.idLibro = idLibro;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucion = fechaDevolucion;
        this.devuelto = devuelto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getidEstudiante() {
        return idEstudiante;
    }

    public void setidEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public int getidLibro() {
        return idLibro;
    }

    public void setidLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public Date getfechaPrestamo() {
        return fechaPrestamo;
    }

    public void setfechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getfechaDevolucion() {
        return fechaDevolucion;
    }

    public void setfechaDevolucion(Date fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public boolean isDevuelto() {
        return devuelto;
    }

    public void setDevuelto(boolean devuelto) {
        this.devuelto = devuelto;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "id=" + id +
                ", idEstudiante=" + idEstudiante +
                ", idLibro=" + idLibro +
                ", fechaPrestamo=" + fechaPrestamo +
                ", fechaDevolucion=" + fechaDevolucion +
                ", devuelto=" + devuelto +
                '}';
    }
}
