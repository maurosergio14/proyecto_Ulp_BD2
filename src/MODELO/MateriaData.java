package MODELO;

import CONTROLADOR.Conexion;
import controlador.Materia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MateriaData {

    private Connection connection = null;

    public MateriaData(Conexion conexion) {
        try {
            connection = conexion.getConexion();
        } catch (SQLException ex) {
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void guardarMateria(Materia materia) {
        try {
            String sql = "INSERT INTO materia (nombre) VALUES (?);";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombre());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                materia.setId(1);
            } else {
                System.out.println("Error no hay id ");
            }
            ps.close();;
        } catch (SQLException e) {

            System.out.println("Error " + e.getMessage());
        }
    }

    public List<Materia> obtenerMateria() {

        List<Materia> materias = new ArrayList<>();
        try {
            String sql = "SELEC * from materia";

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            Materia materia;
            while (rs.next()) {
                materia = new Materia();
                materia.setId(rs.getInt("id"));
                materia.setNombre(rs.getString("nombre"));
                materias.add(materia);

            }
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(MateriaData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return materias;
    }

    public Materia buscarMateria(int id) {
        Materia materia = null;
        try {

            String sql = "SELECT * FROM materia WHERE id =?;";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                materia = new Materia();
                materia.setId(rs.getInt("id"));
                materia.setNombre(rs.getString("nombre"));

            }
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error ...");
        }
        return materia;
    }

    public void borrarMateria(int id) {
        try {
            String sql = "DELETE FROM materia WHERE id =?;";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error ...");
        }
    }

    public void actulizarMateria(Materia materia) {
        try {
            String sql = "UPDATE materia SET nombre = ? WHERE id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getId());
            ps.executeUpdate();

            ps.close();
        } catch (SQLException e) {
            System.out.println("ERROR...");
        }
    }
}
