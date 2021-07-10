package Datos;

import Entidades.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabasemanagerCliente {

    private final String HOST = "localhost:3306";
    private final String DATABASE = "examenprogramado";
    private final String URL = "jdbc:mysql://" + HOST + "/" + DATABASE + "?autoReconnect=true&useSSL=false";
    private final String USER = "root";
    private final String PASS = "Jordi131220";
    private final String DRIVER = "com.mysql.cj.jdbc.Driver";
    Connection connection = null;
    Statement statements;
    ResultSet resultSet = null;

    public void abrirBaseDatos(){
        try{
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL,USER, PASS);
            statements = connection.createStatement();
        } catch (ClassNotFoundException classNotFoundException) {
            System.err.println("Error");
        }catch ( SQLException e){
            System.err.println("Error");
        }
    }

    public int insertCliente(Cliente cliente){
        int filasmodificadas = 0;
        try {
            String sql = "INSERT INTO cliente VALUES(?,?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            preparedStatement.setInt(1, cliente.getId());
            preparedStatement.setInt(2, cliente.getCedula());
            preparedStatement.setString(3, cliente.getNombre());
            preparedStatement.setString(4, cliente.getApellido());
            preparedStatement.setString(5, cliente.getDireccion());
            preparedStatement.setInt(6, cliente.getTelefono());
            preparedStatement.setString(7, cliente.getCorreo());
            filasmodificadas = preparedStatement.executeUpdate();

            preparedStatement.close();

        }catch (SQLException sqlException){
            System.err.println("Se produjo un error: Insertar");
            System.out.println(sqlException.getMessage());
        }

        return filasmodificadas;
    }

    public List<Cliente> getClientes(){
        List<Cliente> clientes = new ArrayList();

        try {
            String sql = "SELECT * FROM cliente";
            resultSet = statements.executeQuery(sql);

            while(resultSet.next()){
                Cliente cliente = new Cliente();

                cliente.setId(resultSet.getInt("id"));
                cliente.setCedula(resultSet.getInt("cedula"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellido(resultSet.getString("apellido"));
                cliente.setDireccion(resultSet.getString("direccion"));
                cliente.setTelefono(resultSet.getInt("telefono"));
                cliente.setCorreo(resultSet.getString("correo"));


                clientes.add(cliente);


            }

        } catch (SQLException sqlException) {
            System.err.println("Lista: Error al acceder a la base de datos");
            System.out.println(sqlException.getMessage());
        }
        return clientes;
    }

    public Cliente getCliente(int id){
        Cliente cliente = null;

        try {
            String sql = "SELECT * FROM cliente WHERE id= ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();


            while(resultSet.next()){

                cliente = new Cliente();

                cliente.setId(resultSet.getInt("id"));
                cliente.setCedula(resultSet.getInt("cedula"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setApellido(resultSet.getString("apellido"));
                cliente.setDireccion(resultSet.getString("direccion"));
                cliente.setTelefono(resultSet.getInt("telefono"));
                cliente.setCorreo(resultSet.getString("correo"));

            }

            preparedStatement.close();
        }catch (SQLException sqlException){
            System.err.println("Se produjo un error");
        }
        return cliente;
    }

    public int updateCliente(Cliente cliente){
        int filasmodificadas = 0;

        try {
            String sql = "UPDATE cliente SET cedula = ?, nombre = ?, apellido = ?, direccion = ?, telefono = ?, correo = ? WHERE id = ?";


            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, cliente.getCedula());
            preparedStatement.setString(2, cliente.getNombre());
            preparedStatement.setString(3, cliente.getApellido());
            preparedStatement.setString(4, cliente.getDireccion());
            preparedStatement.setInt(5, cliente.getTelefono());
            preparedStatement.setString(6, cliente.getCorreo());
            preparedStatement.setInt(7, cliente.getId());


            filasmodificadas = preparedStatement.executeUpdate();


            preparedStatement.close();

        }catch (SQLException sqlException){
            System.err.println(sqlException.getMessage());
        }

        return  filasmodificadas;


    }

    public int deleteCliente(int id){

        int filasmodificadas = 0;
        try {
            String sql = "DELETE FROM cliente WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id  );

            filasmodificadas = preparedStatement.executeUpdate();
            preparedStatement.close();

        }catch (SQLException sqlException){
            System.err.println(sqlException.getMessage());
        }

        return filasmodificadas;

    }

    public void cerrarBaseDatos(){
        try{
            connection.close();
            statements.close();
        } catch (SQLException throwables) {
            System.err.println("Error al cerrar Bases de Datos");
        }
    }



}
