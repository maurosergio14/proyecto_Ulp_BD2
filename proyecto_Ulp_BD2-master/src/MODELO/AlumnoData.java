/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import CONTROLADOR.Alumno;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Thomas
 */
class AlumnoData {

    private Connection conectar = null;

    public AlumnoData(Conexion conexion) throws SQLException {
        try {
            conectar = conexion.getConexion();
        } catch (SQLException ex) {
            System.out.println("Error al abrir al obtener la conexion");
        }
    }

    public void guardarAlumno(Alumno alumno) {

        try {
            String sql = "INSERT INTO alumno(nombre,apellido,mail,fecNac,cursando) VALUES(?, ?, ?, ?, ?);";
            PreparedStatement ps = conectar.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, alumno.getNombre());
            ps.setString(1, alumno.getApellido());
            ps.setString(1, alumno.getMail());
            ps.setDate(4, Date.valueOf(alumno.getFecNac()));
            ps.setBoolean(5, alumno.getCursando());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                alumno.setId(rs.getInt(1));
            } else {
                System.out.println("No se pudo obtener el id luego de insertar un alumno");
            }
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Error al inserda un alumno: " + ex.getMessage());
        }

    }

    public List<Alumno> obtenerAlumnos() {
        List<Alumno> alumnos = new ArrayList<Alumno>();

        try {

            String sql = "SELECT * FROM alumno;";

            PreparedStatement ps = conectar.prepareStatement(sql);

            ResultSet resultSet = ps.executeQuery();

            Alumno alumno;

            while (resultSet.next()) {
                alumno = new Alumno();
                alumno.setId(resultSet.getInt("id"));
                alumno.setNombre(resultSet.getString("nombre"));
                alumno.setApellido(resultSet.getString("apellido"));
                alumno.setMail(resultSet.getString("mail"));
                alumno.setFecNac(resultSet.getDate("fecNac").toLocalDate());
                alumno.setCursando(resultSet.getBoolean("cursando"));

                alumnos.add(alumno);
            }

            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error al obtener a los alumnos: " + ex.getMessage());
        }

        return alumnos;

    }

    public void borrarAlumno(int id) {

        try {
            String sql = "DELETE FROM alumno WHERE id=?;";
            PreparedStatement ps = conectar.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();

            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error al borrar un alumno: " + ex.getMessage());
        }

    }

    public void actualizarAlumno(Alumno alumno) {
        try {
            String sql = "UPDATE alumno SET nombre = ?, apellido= ?, mail= ?, fecNac=?, cursando=?, WHERE id=?;";

            PreparedStatement ps = conectar.prepareStatement(sql);
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getMail());
            ps.setDate(4, Date.valueOf(alumno.getFecNac()));
            ps.setBoolean(5, alumno.getCursando());
            ps.setInt(6, alumno.getId());
            ps.execute();

            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error al actualizar un alumno:" + ex.getMessage());
        }
    }

    public Alumno buscarAlumno(int id) {
        Alumno alumno = null;

        try {

            String sql = "SELECT * FROM alumno WHERE id=?;";

            PreparedStatement ps = conectar.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                alumno = new Alumno();
                alumno.setId(resultSet.getInt("id"));
                alumno.setNombre(resultSet.getString("nombre"));
                alumno.setApellido(resultSet.getString("apellido"));
                alumno.setMail(resultSet.getString("mail"));
                alumno.setFecNac(resultSet.getDate("fecNac").toLocalDate());
                alumno.setCursando(resultSet.getBoolean("cursando"));

            }
            ps.close();

        } catch (SQLException ex) {
            System.out.println("Error al buscar el id de alumno" + ex.getMessage());
        }
        return alumno;
    }
}
