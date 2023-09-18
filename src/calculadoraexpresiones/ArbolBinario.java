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

    private String mostrarPreOrden(Nodo n) {
        if (n != null) {
            return n.getValor() + " "
                    + mostrarPostOrden(n.izquierdo) + " "
                    + mostrarPostOrden(n.derecho);
        }
        return "";
    }

    public String mostrarPreOrden() {
        return mostrarPreOrden(raiz);
    }

    private double getValorVariable(String variable) {
        double valor = 0;
        if (this.variables.size() > 0) {
            int p = this.variables.indexOf(variable);
            if (p >= 0 && p < valores.size()) {
                valor = this.valores.get(p);
            }
        }
        return valor;
    }

    private double getValorNodo(Nodo n) {
        return n.getTipo() == TipoOperando.CONSTANTE ? n.getValorNumerico() : getValorVariable(n.getValor());
    }

    private double ejecutarNodo(Nodo n) {
        if (n.izquierdo == null && n.derecho == null) {
            return getValorNodo(n);
        } else {
            double operando1 = ejecutarNodo(n.izquierdo);
            double operando2 = ejecutarNodo(n.derecho);
            switch (n.getValor()) {
                case "+":
                    return operando1 + operando2;
                case "-":
                    return operando1 - operando2;
                case "*":
                    return operando1 * operando2;
                case "/":
                    return operando2 != 0 ? operando1 / operando2 : 0;
                case "%":
                    return operando2 != 0 ? operando1 % operando2 : 0;
                case "^":
                    return Math.pow(operando1, operando2);
            }
        }
        return 0;
    }

    public double ejecutar(List<String> variables, List<Double> valores) {
        this.variables = variables;
        this.valores = valores;
        return ejecutarNodo(raiz);
    }

}
