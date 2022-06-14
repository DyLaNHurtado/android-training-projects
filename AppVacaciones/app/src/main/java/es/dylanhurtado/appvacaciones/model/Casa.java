package es.dylanhurtado.appvacaciones.model;

public class Casa {
    private int imagen;
    private String nombre, pais;
    private double precio;

    public Casa(int imagen, String nombre, String pais, double precio) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.pais = pais;
        this.precio = precio;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
