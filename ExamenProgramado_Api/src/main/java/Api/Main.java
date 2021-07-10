package Api;

import Entidades.Cliente;
import Entidades.Prestamo;

import static spark.Spark.*;

public class Main {
    @SuppressWarnings("ThrowableResultOfMethodCallIgnored")
    public static void main(String[] args) {

        port(8080);

        Service service = new Service();

        //Post Cliente - Insertar
        post("/api/cliente", (request, response) -> {

            String id = request.queryParams("id");
            String cedula = request.queryParams("cedula");
            String nombre = request.queryParams("nombre");
            String apellido = request.queryParams("apellido");
            String direccion = request.queryParams("direccion");
            String telefono = request.queryParams("telefono");
            String correo = request.queryParams("correo");
            try{
                if(nombre != null && id !=null && cedula != null && apellido != null && direccion !=null && telefono != null && correo != null){
                    Cliente cliente = new Cliente(Integer.parseInt(id), Integer.parseInt(cedula), nombre, apellido, direccion, Integer.parseInt(telefono), correo);


                    return  service.postCliente(cliente);
                }else{
                    return "Datos incompletos";
                }


            }catch (NumberFormatException numberFormatException){
                return "Formato invalido";
            }

        }, new JsonUtil());


        //Devuelve los clientes

        get("/api/cliente", (request, response) -> {
            return service.getClientes();
        }, new JsonUtil());


        //Devuelve cliente por ID
        get("/api/cliente/:id", (request, response) -> {
            String id = request.params(":id");

            try {
                return service.getCliente(Integer.parseInt(id));
            }catch (NumberFormatException numberFormatException){
                return "idno válido";
            }
        }, new JsonUtil());

        //Actualizar

        patch("/api/cliente/:id", (request, response) -> {


            String cedula = request.queryParams("cedula");
            String nombre = request.queryParams("nombre");
            String apellido = request.queryParams("apellido");
            String direccion = request.queryParams("direccion");
            String telefono = request.queryParams("telefono");
            String correo = request.queryParams("correo");


            try {
                if(nombre != null && cedula != null && apellido != null && direccion !=null && telefono != null && correo != null){
                    Cliente cliente = new Cliente(Integer.parseInt(cedula), nombre , apellido, direccion, Integer.parseInt(telefono), correo);
                    return service.patchCliente(cliente);
                }else{
                    return "Datos incompletos";
                }
            }catch (NumberFormatException numberFormatException){
                return "Formato inválido";
            }
        }, new JsonUtil());


        //----------------------------------------PRESTAMOOOO----------------------------------------//

        post("/api/prestamo", (request, response) -> {
            String id= request.queryParams("id");
            String id_cliente = request.queryParams("id_cliente");
            String fecha_inicio = request.queryParams("fecha_inicio");
            String fecha_fin = request.queryParams("fecha_fin");
            String monto_total = request.queryParams("monto_total");
            String descripcion = request.queryParams("descripcion");


            try{
                if(id != null && id_cliente !=null && fecha_inicio != null && fecha_fin != null && monto_total !=null && descripcion != null){
                    Prestamo prestamo = new Prestamo(Integer.parseInt(id),Integer.parseInt(id_cliente), fecha_inicio,fecha_fin,Integer.parseInt(monto_total),descripcion);
                    return  service.postPrestamo(prestamo);
                }else{
                    return "Datos incompletos";
                }


            }catch (NumberFormatException numberFormatException){
                return "Formato invalido";
            }

        }, new JsonUtil());


        //Devuelve los prestamos

        get("/api/prestamo", (request, response) -> {
            return service.getPrestamos();
        }, new JsonUtil());


        //Devuelve prestamo por ID
        get("/api/prestamo/:id", (request, response) -> {
            String id = request.params(":id");

            try {
                return service.getPrestamo(Integer.parseInt(id));
            }catch (NumberFormatException numberFormatException){
                return "id no válido";
            }
        }, new JsonUtil());

        //Actualizar

        patch("/api/prestamo/:id", (request, response) -> {

            String id= request.params(":id");
            String id_cliente = request.queryParams("id_cliente");
            String fecha_inicio = request.queryParams("fecha_inicio");
            String fecha_fin = request.queryParams("fecha_fin");
            String monto_total = request.queryParams("monto_total");
            String descripcion = request.queryParams("descripcion");


            try{
                if(id != null && id_cliente !=null && fecha_inicio != null && fecha_fin != null && monto_total !=null && descripcion != null){
                    Prestamo prestamo = new Prestamo(Integer.parseInt(id),Integer.parseInt(id_cliente), fecha_inicio,fecha_fin,Integer.parseInt(monto_total),descripcion);
                    return  service.patchPrestamo(prestamo);
                }else{
                    return "Datos incompletos";
                }


            }catch (NumberFormatException numberFormatException){
                return "Formato invalido";
            }

        }, new JsonUtil());



        options("/*", (request, response) ->{
            String accesControlRequestHeaders = request.headers("Access-Control-Request-Headers");

            if (accesControlRequestHeaders!=null){
                response.header("Access-Control-Allow-Headers", accesControlRequestHeaders);
            }

            String accesControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accesControlRequestMethod!=null){
                response.header("Access-Control-Request-Methods", accesControlRequestMethod);


            }
            return "OK";
        });



        before((request, response) -> {
            response.type("application/json");
            response.header("Access-Control-Allow-Origin", "*");
        });
    }
}
