/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.upeu.fax;

import pe.edu.upeu.coon.ConnS;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import pe.edu.upeu.modelo.FaxTO;
import pe.edu.upeu.dao.FaxDao;
import pe.edu.upeu.dao.FaxDaoI;

/**
 *
 * @author guitar
 */
public class Controllergame {

    ConnS intance = ConnS.getInstance();
    Connection conexion = intance.getConnection();
    PreparedStatement ps;
    ResultSet rs;

    private ViewGame view;
    private ModelGame model;
    private JLabel[][] casillas;
    FaxDaoI dao = new FaxDao();

    
    public Controllergame(ViewGame view, ModelGame model) {
        this.view = view;
        this.model = model;
        casillas = view.getCasillas();
        agregarListeners();
        crearJugadores();
    }

    private void agregarListeners() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                agregarEventoMouse(i, j);

            }
        }
        JButton botonIniciar = view.getIniciar();
        botonIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.iniciarGame(casillas);
                FaxTO to = new FaxTO();
                to.setNombrePartida("Partida1");
                to.setNombreJugador1(view.nj1.getText().toString());
                to.setNombreJugador2(view.nj2.getText().toString());
                to.setGanador(to.nombreJugador1);
                to.setPunto(1);
                to.setEstado("Jugando");
                System.out.println(to.getNombrePartida());
                System.out.println(to.getNombreJugador1());
                System.out.println(to.getNombreJugador2());
                System.out.println(to.getGanador());
                System.out.println(to.getPunto());
                System.out.println(to.getEstado());


                dao.create(to);
                view.listarResultados();
            }
        });

        JButton botonAnular = view.getAnular();
        botonAnular.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.anularGame(casillas);
            }
        });

    }

    private void agregarEventoMouse(int i, int j) {
        JLabel casillaActual = casillas[i][j];
        casillaActual.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                model.marcarCasilla(i, j, casillas);
            }
        });
    }

    private void crearJugadores() {
        JLabel j1 = view.getVictoriasJ1();
        JLabel j2 = view.getVictoriasJ2();
        model.setJugadores(j1, j2);
    }

}
