package clasegenericidad;

public class DatosAdicionales {
    private String eps;
    private String fecha;

    public DatosAdicionales(String eps, String fecha) {
        this.eps = eps;
        this.fecha = fecha;
    }

    public String getEps() {
        return eps;
    }

    public String getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "DatosAdicionales{" +
                "eps='" + eps + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }
}