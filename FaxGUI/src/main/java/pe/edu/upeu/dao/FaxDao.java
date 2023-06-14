package pe.edu.upeu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pe.edu.upeu.coon.ConnS;
import pe.edu.upeu.modelo.FaxTO;
/**
 *
 * @author guitar
 */

public class FaxDao implements FaxDaoI {

    ConnS intance = ConnS.getInstance();
    Connection conexion = intance.getConnection();
    PreparedStatement ps;
    ResultSet rs;

    @Override
    public List listarResultados() {
        List<FaxTO> lista = new ArrayList();
        String sql = "select * from resultados";
        try {
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                FaxTO to = new FaxTO();
                to.setIdResultado(rs.getInt("id_resultado"));
                to.setNombrePartida(rs.getString("nombre_partida"));
                to.setNombreJugador1(rs.getString("nombre_jugador1"));
                to.setNombreJugador2(rs.getString("nombre_jugador2"));
                to.setGanador(rs.getString("ganador"));
                to.setPunto(rs.getInt("punto"));
                to.setEstado(rs.getString("estado"));
                lista.add(to);
            }

        } catch (Exception e) {
        }
        return lista;
    }

    
    @Override
    public int create(FaxTO re) {
        int exec = 0;
        int i = 0;
        String sql = "Insert INTO resultados(nombre_partida, nombre_jugador1, nombre_jugador2, ganador, punto, estado)"
                + "VALUES(?, ?, ?,?, ?,?)";
        try {
            ps = conexion.prepareStatement(sql);
            ps.setString(++i, re.getNombrePartida());
            ps.setString(++i, re.getNombreJugador1());
            ps.setString(++i, re.getNombreJugador2());
            ps.setString(++i, re.getGanador());
            ps.setInt(++i, re.getPunto());
            ps.setString(++i, re.getEstado());
            exec = ps.executeUpdate();
        } catch (Exception e) {
        }
        return exec;
    }
  
}
