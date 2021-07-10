package Api;

import Datos.DatabasemanagerCliente;
import Datos.DatabasemanagerPrestamo;
import Entidades.Cliente;
import Entidades.Prestamo;

import javax.xml.crypto.Data;
import java.util.List;

public class Service {

    DatabasemanagerCliente controladorCliente;
    DatabasemanagerPrestamo controladorPrestamo;
    public Service() {
        this.controladorCliente = new DatabasemanagerCliente();
        this.controladorPrestamo = new DatabasemanagerPrestamo();
    }


    public String postCliente(Cliente cliente){
        controladorCliente.abrirBaseDatos();
        int filasmodificads = controladorCliente.insertCliente(cliente);
        controladorCliente.cerrarBaseDatos();

        if(filasmodificads!=0){
            return "El cliente fue creado correctamente";
        }else{
            return "Se produjo un error al crear el cliente";
        }


    }
    public List<Cliente> getClientes(){
        controladorCliente.abrirBaseDatos();
        List<Cliente> clientes = controladorCliente.getClientes();
        controladorCliente.cerrarBaseDatos();
        return clientes;
    }

    public Cliente getCliente(int id){
        controladorCliente.abrirBaseDatos();
        Cliente cliente= controladorCliente.getCliente(id);
        controladorCliente.cerrarBaseDatos();
        return cliente;
    }

    public String patchCliente(Cliente cliente){
        controladorCliente.abrirBaseDatos();
        Cliente consulta = controladorCliente.getCliente(cliente.getId());
        controladorCliente.cerrarBaseDatos();

        if(consulta!= null){
            controladorCliente.abrirBaseDatos();
            int filasmodificadas = controladorCliente.updateCliente(cliente);
            controladorCliente.cerrarBaseDatos();


            if (filasmodificadas!=0){
                return "El cliente fue actualizado correctamente";
            }else{
                return "Se produjo un error al actualizar el cliente";

            }

        }else{
            return "El cliente no se encuentra en la base de datos";
        }
    }
    public String deleteCliente(int id){
        controladorCliente.abrirBaseDatos();
        Cliente consulta = controladorCliente.getCliente(id);
        controladorCliente.cerrarBaseDatos();

        if (consulta!=null){
            controladorCliente.abrirBaseDatos();
            int filasmodificadas = controladorCliente.deleteCliente(id);
            controladorCliente.cerrarBaseDatos();

            if(filasmodificadas!=0){
                return "El cliente fue creado correctamente";
            }else{
                return "Error al eliminar el cliente";

            }

        }else{
            return "El cliente no se encuentra en la base de datos";
        }

    }


    //Prestamo
    public String postPrestamo(Prestamo prestamo){
        controladorPrestamo.abrirBaseDatos();
        int filasmodificads = controladorPrestamo.insertPrestamo(prestamo);
        controladorPrestamo.cerrarBaseDatos();

        if(filasmodificads!=0){
            return "El prestamo fue creado correctamente";
        }else{
            return "Se produjo un error al crear el prestamo";
        }


    }
    public List<Prestamo> getPrestamos(){
        controladorPrestamo.abrirBaseDatos();
        List<Prestamo> prestamos =controladorPrestamo.getPrestamos();
        controladorPrestamo.cerrarBaseDatos();
        return prestamos;
    }

    public Prestamo getPrestamo(int id){
        controladorPrestamo.abrirBaseDatos();
        Prestamo prestamo = controladorPrestamo.getPrestamo(id);
        controladorPrestamo.cerrarBaseDatos();
        return prestamo;
    }

    public String patchPrestamo(Prestamo prestamo){
        controladorPrestamo.abrirBaseDatos();
        Prestamo consulta = controladorPrestamo.getPrestamo(prestamo.getId());
        controladorPrestamo.cerrarBaseDatos();

        if(consulta!= null){
            controladorPrestamo.abrirBaseDatos();
            int filasmodificadas = controladorPrestamo.updatePrestamo(prestamo);
            controladorPrestamo.cerrarBaseDatos();


            if (filasmodificadas!=0){
                return "El prestamo fue actualizado correctamente";
            }else{
                return "Se produjo un error al actualizar el prestamo";

            }

        }else{
            return "El usuario no se encuentra en la base de datos";
        }
    }
    public String deletePrestamo(int id){
        controladorPrestamo.abrirBaseDatos();
        Prestamo consulta = controladorPrestamo.getPrestamo(id);
        controladorPrestamo.cerrarBaseDatos();

        if (consulta!=null){
            controladorPrestamo.abrirBaseDatos();
            int filasmodificadas = controladorPrestamo.deletePrestamo(id);
            controladorPrestamo.cerrarBaseDatos();

            if(filasmodificadas!=0){
                return "El prestamo fue creado correctamente";
            }else{
                return "Error al eliminar el prestamo";

            }

        }else{
            return "El prestamo no se encuentra en la base de datos";
        }

    }
}
