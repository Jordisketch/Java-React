package Datos;


import Entidades.Prestamo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabasemanagerPrestamo {


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

    public int insertPrestamo(Prestamo prestamo){
        int filasmodificadas = 0;
        try {
            String sql = "INSERT INTO prestamo VALUES(?,?,?,?,?,?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, prestamo.getId());
            preparedStatement.setInt(2, prestamo.getId_cliente());
            preparedStatement.setString(3, prestamo.getFecha_inicio());
            preparedStatement.setString(4, prestamo.getFecha_fin());
            preparedStatement.setInt(5, prestamo.getMonto_total());
            preparedStatement.setString(6, prestamo.getDescripcion());

            filasmodificadas = preparedStatement.executeUpdate();

            preparedStatement.close();

        }catch (SQLException sqlException){
            System.err.println("Se produjo un error: Insertar");
            System.out.println(sqlException.getMessage());
        }

        return filasmodificadas;
    }

    public List<Prestamo> getPrestamos(){
        List<Prestamo> prestamos = new ArrayList();

        try {
            String sql = "SELECT * FROM prestamo";
            resultSet = statements.executeQuery(sql);

            while(resultSet.next()){
                 Prestamo prestamo = new Prestamo();

                prestamo.setId(resultSet.getInt("id"));
                prestamo.setId_cliente(resultSet.getInt("id_cliente"));
                prestamo.setFecha_inicio(resultSet.getString("fecha_inicio"));
                prestamo.setFecha_fin(resultSet.getString("fecha_fin"));
                prestamo.setMonto_total(resultSet.getInt("monto_total"));
                prestamo.setDescripcion(resultSet.getString("descripcion"));


                prestamos.add(prestamo);


            }

        } catch (SQLException sqlException) {
            System.err.println("Lista: Error al acceder a la base de datos");
            System.out.println(sqlException.getMessage());
        }
        return prestamos;
    }

    public Prestamo getPrestamo(int id){
        Prestamo prestamo = null;

        try {
            String sql = "SELECT * FROM prestamo WHERE id= ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            resultSet = preparedStatement.executeQuery();


            while(resultSet.next()){

                prestamo = new Prestamo();


                prestamo.setId(resultSet.getInt("id"));
                prestamo.setId_cliente(resultSet.getInt("id_cliente"));
                prestamo.setFecha_inicio(resultSet.getString("fecha_inicio"));
                prestamo.setFecha_fin(resultSet.getString("fecha_fin"));
                prestamo.setMonto_total(resultSet.getInt("monto_total"));
                prestamo.setDescripcion(resultSet.getString("descripcion"));


            }

            preparedStatement.close();
        }catch (SQLException sqlException){
            System.err.println("Se produjo un error");
        }
        return prestamo;
    }

    public int updatePrestamo(Prestamo prestamo){
        int filasmodificadas = 0;

        try {
            String sql = "UPDATE prestamo  id_cliente = ?, fecha_inicio = ?, fecha_fin = ?, monto_total = ?, descripcion = ? WHERE id = ?";


            PreparedStatement preparedStatement = connection.prepareStatement(sql);


            preparedStatement.setInt(1, prestamo.getId_cliente());
            preparedStatement.setString(2, prestamo.getFecha_inicio());
            preparedStatement.setString(3, prestamo.getFecha_fin());
            preparedStatement.setInt(4, prestamo.getMonto_total());
            preparedStatement.setString(5, prestamo.getDescripcion());
            preparedStatement.setInt(6, prestamo.getId());
            ;


            filasmodificadas = preparedStatement.executeUpdate();


            preparedStatement.close();

        }catch (SQLException sqlException){
            System.err.println(sqlException.getMessage());
        }

        return  filasmodificadas;


    }

    public int deletePrestamo(int id){

        int filasmodificadas = 0;
        try {
            String sql = "DELETE FROM prestamo WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, id );

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
