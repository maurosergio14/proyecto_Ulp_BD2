/*
 */
package MODELO;

import CONTROLADOR.Alumno;
import CONTROLADOR.Conexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Sergio
 */
public class Alumno_bd {

    private Connection con;

    public Alumno_bd(Conexion conexion) {
        try {
            con = conexion.getConexion();
            JOptionPane.showMessageDialog(null, "conexion exitosa");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "conexion fallida" + e.getMessage());
        }
    }

    public void guardarAlumno(Alumno alumno) {
        try {
            String sql = "INSERT INTO alumno(nombre,fecNac,cursando) VALUES (?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, alumno.getNombre());
            ps.setDate(2, Date.valueOf(alumno.getFecNac()));
            ps.setBoolean(3, alumno.getCursando());

            ps.executeUpdate();//ejecuta sentencia Insert

            ResultSet rs = ps.getGeneratedKeys();//pide a sql le devuelva lista de clave
            if (rs.next()) {
                alumno.setId(rs.getInt(1));
            } else {
                JOptionPane.showMessageDialog(null, "no se pudo obtener id");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error al guardar alumno" + e.getMessage());
        }
    }

    public List<Alumno> obtenerAlumnos() {
        List<Alumno> alumnos = new ArrayList<Alumno>();
        try {
            String sql = "SELECT * FROM alumno;";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rset = ps.executeQuery();//metodo para hacer consultas en sql

            Alumno alumno;//declaro variable tipo alumno
            while (rset.next()) {
                alumno = new Alumno();
                alumno.setId(rset.getInt(1));//desde culumna de bs 
                alumno.setNombre(rset.getString(2));//columna donde esta guardado nombre en bd
                alumno.setFecNac(rset.getDate(3).toLocalDate());//lo mismo solo que lo transformo en localDate
                alumno.setCursando(rset.getBoolean(4));

                alumnos.add(alumno);//guardar en la lista
            }
            ps.close();//cerramos conexion
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al listar los alumnos");
        }
        return alumnos;
    }

    public Alumno BuscarAlumno(int id_alumno) {
        Alumno alumno = null;//inicializo en null
        try {
            String sql = "SELECT * FROM alumno WHERE idAlumno=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_alumno);
            ResultSet rset = ps.executeQuery();//simpre que se haga una consulta o select
            while (rset.next()) {
                alumno = new Alumno();
                alumno.setId(rset.getInt(1));
                alumno.setNombre(rset.getString(2));
                alumno.setFecNac(rset.getDate(3).toLocalDate());
                alumno.setCursando(rset.getBoolean(4));
            }
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al buscar alumnos");
        }
        return alumno;
    }

    public void actualizarAlumnmo(Alumno alumno) {
        try {
            String sql = "UPDATE alumno SET nombre=?,fecNac=?,cursando=? WHERE idAlumno=?;";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, alumno.getId());
            ps.setString(2, alumno.getNombre());
            ps.setDate(3, Date.valueOf(alumno.getFecNac()));
            ps.setBoolean(4, alumno.getCursando());
            ps.executeUpdate();//siempre que se haga un insert,delete,update
            ps.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al actualizar alumno");
        }
    }

    public void borrarAlumno(int id) {
        try {
            String sql = "DELETE FROM alumno WHERE id =?;";
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al borrar alumno");
        }

    }
}
