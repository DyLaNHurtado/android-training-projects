package es.dylanhurtado.appvacaciones.model;

public class Ubicacion {
    private int imagen, codigoId;
    private String nombre, pais;

    public Ubicacion(int imagen, String nombre, String pais, int codigoId) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.pais = pais;
        this.codigoId = codigoId;
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

    public int getCodigoId() {
        return codigoId;
    }

    public void setCodigoId(int codigoId) {
        this.codigoId = codigoId;
    }
}
