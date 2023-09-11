package calculadoraexpresiones;

import java.util.List;

public class ArbolBinario {

    Nodo raiz;

    private List<String> variables;
    private List<Double> valores;

    public ArbolBinario() {
        raiz = null;
    }

    public ArbolBinario(Nodo n) {
        raiz = n;
    }

    private String mostrarInOrden(Nodo n) {
        if (n != null) {
            return mostrarInOrden(n.izquierdo) + " "
                    + n.getValor() + " "
                    + mostrarInOrden(n.derecho);
        }
        return "";
    }

    public String mostrarInOrden() {
        return mostrarInOrden(raiz);
    }

    private String mostrarPostOrden(Nodo n) {
        if (n != null) {
            return mostrarPostOrden(n.izquierdo) + " "
                    + mostrarPostOrden(n.derecho) + " "
                    + n.getValor();
        }
        return "";
    }

    public String mostrarPostOrden() {
        return mostrarPostOrden(raiz);
    }

}
