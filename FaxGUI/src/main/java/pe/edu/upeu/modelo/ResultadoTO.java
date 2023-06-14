
package pe.edu.upeu.modelo;

import lombok.Data;

@Data
public class ResultadoTO {
    public int idResultado,punto;
    public String nombrePartida,nombreJugador1,nombreJugador2,ganador,estado;
    public double result;
}
    