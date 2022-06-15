package es.dylanhurtado.examenfinal;


import es.dylanhurtado.examenfinal.model.Category;
import es.dylanhurtado.examenfinal.model.Receta;

// Clase util que almacena ciertos datos.
// Es parecida a la clase abstracta de la BBDD.
public class ElementoSelecionado {

    // Creamos un objeto static de la clase + constructor vacio
    private static ElementoSelecionado elementoSeleccionado;
    private ElementoSelecionado(){};

    // Creamos metodo para obtener la instancia
    public static ElementoSelecionado getInstance(){
        if(elementoSeleccionado == null)
            elementoSeleccionado = new ElementoSelecionado();
        return elementoSeleccionado;
    }

    // Creamos las entidades donde se almacenaran de forma temporal
    private Category categoria = null;
    private Receta receta = null;

    // GET & SET
    public Category getCategoria() {
        return categoria;
    }

    public void setCategoria(Category categoria) {
        this.categoria = categoria;
    }

    public Receta getReceta() {
        return receta;
    }

    public void setReceta(Receta receta) {
        this.receta = receta;
    }
}
