
package pe.edu.upeu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import pe.edu.upeu.coon.ConnS;
import pe.edu.upeu.modelo.ResultadoTO;


public class ResultadoDao implements ResultadoDaoI{
    ConnS intance=ConnS.getInstance();
    Connection conexion=intance.getConnection();
    PreparedStatement ps;
    ResultSet rs;
    
    @Override
    public List listarResultados() {
        List<ResultadoTO> lista=new ArrayList();
        String sql="select * from resultados";
        try {
            ps=conexion.prepareStatement(sql);
            rs=ps.executeQuery();
            while (rs.next()) {
               ResultadoTO to=new ResultadoTO();
               to.setIdResultado(rs.getInt("id_resultado"));
               to.setNombreJugador1(rs.getString("nombrejugador1"));
               to.setNombreJugador2(rs.getString("nombrejugador2"));
               to.setGanador(rs.getString("ganador"));
               to.setResult(rs.getDouble("result"));
               lista.add(to);
            }
            
        } catch (Exception e) {
        }
        return lista;
    }

    @Override
    public int crearResultado(ResultadoTO re) {
        int exec=0;
        int i=0;
        String sql="insert into resultados(nombrejugador1, nombrejugador2, ganador, result)"
                + " values(?,?,?,?)";
        try {
            ps=conexion.prepareStatement(sql);
            ps.setString(++i, re.getNombreJugador1());
            ps.setString(++i, re.getNombreJugador2());
            ps.setString(++i, re.getGanador());
            ps.setDouble(++i, re.getResult());
            exec=ps.executeUpdate();
        } catch (Exception e) {
        }
        return exec;
    }
    
}
