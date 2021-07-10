package Entidades;

public class Prestamo {
    private int id;
    private int id_cliente;
    private String fecha_inicio;
    private String fecha_fin;
    private int monto_total;
    private  String descripcion;


    public Prestamo() {

    }

    public Prestamo(int id, int id_cliente, String fecha_inicio, String fecha_fin, int monto_total, String descripcion) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.monto_total = monto_total;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public int getMonto_total() {
        return monto_total;
    }

    public void setMonto_total(int monto_total) {
        this.monto_total = monto_total;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "id=" + id +
                ", id_cliente=" + id_cliente +
                ", fecha_inicio='" + fecha_inicio + '\'' +
                ", fecha_fin='" + fecha_fin + '\'' +
                ", monto_total=" + monto_total +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
