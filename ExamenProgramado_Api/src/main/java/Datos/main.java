package Datos;

import Entidades.Cliente;
import Entidades.Prestamo;

import java.util.List;

public class main {

    public static void main(String[] args) {
        DatabasemanagerCliente databasemanagerCliente = new DatabasemanagerCliente();


        //Cliente
        //databasemanagerCliente.abrirBaseDatos();
       // Cliente cliente = new Cliente(0,208282,"Roberto", "Alo", "Palmare", 5052528, "asdasdasdasda");
        //databasemanagerCliente.insertCliente(cliente);
        //List<Cliente> list = databasemanagerCliente.getClientes();

        //databasemanagerCliente.cerrarBaseDatos();

        //for(Cliente cliente :list ){
          //  System.out.println(cliente.toString());
        //}


        //Prestamos
        DatabasemanagerPrestamo databasemanagerPrestamo = new DatabasemanagerPrestamo();
        databasemanagerPrestamo.abrirBaseDatos();

        Prestamo prestamo = new Prestamo(5,1,"Noviembre", "Enero",20000, "Drogas");

        databasemanagerPrestamo.insertPrestamo(prestamo);

        databasemanagerPrestamo.cerrarBaseDatos();



    }
}
