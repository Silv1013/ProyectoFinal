package Vista;

import Modelo.LaberintoModelo;
import Controlador.LaberintoControlador;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LaberintoVista extends JFrame {
    private int filas = 5;
    private int columnas = 5;
    private JButton[][] botones;
    private boolean[][] laberinto;
    private LaberintoControlador controlador;
    private JPanel panelLaberinto;
    private JPanel panelBotones;
    private JTextField campoInicioX;
    private JTextField campoInicioY;
    private JTextField campoFinX;
    private JTextField campoFinY;
    private JTextField campoTiempo;

    public LaberintoVista() {
        controlador = new LaberintoControlador(this);
        laberinto = new boolean[filas][columnas];
        botones = new JButton[filas][columnas];

        setLayout(new BorderLayout());
        inicializarPanelLaberinto();
        inicializarPanelBotones();

        setTitle("Laberinto");
        setSize(800, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void inicializarPanelLaberinto() {
        panelLaberinto = new JPanel(new GridLayout(filas, columnas));
        inicializarBotones();
        add(panelLaberinto, BorderLayout.CENTER);
    }

    private void inicializarBotones() {
        panelLaberinto.removeAll();
        botones = new JButton[filas][columnas];
        laberinto = new boolean[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                JButton boton = new JButton();
                boton.setBackground(Color.WHITE);
                final int x = i;
                final int y = j;
                boton.addActionListener(e -> {
                    if (boton.getBackground() == Color.WHITE) {
                        boton.setBackground(Color.BLACK);
                        laberinto[x][y] = true; // Marca como bloqueado
                    } else if (boton.getBackground() == Color.BLACK) {
                        boton.setBackground(Color.WHITE);
                        laberinto[x][y] = false; // Desbloquea la celda
                    }
                });
                botones[i][j] = boton;
                panelLaberinto.add(boton);
            }
        }
        panelLaberinto.revalidate();
        panelLaberinto.repaint();
    }

    private void inicializarPanelBotones() {
        panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 1));

        JPanel panelResolucion = new JPanel();
        JButton botonBFS = new JButton("Resolver con BFS");
        JButton botonDFS = new JButton("Resolver con DFS");
        JButton botonRecursivo = new JButton("Resolver con Recursivo");
        JButton botonCache = new JButton("Resolver con Cache");
        JButton botonLimpiar = new JButton("Limpiar Laberinto");
        
   

        
        botonBFS.addActionListener(e -> resolverLaberinto("BFS"));
        botonDFS.addActionListener(e -> resolverLaberinto("DFS"));
        botonRecursivo.addActionListener(e -> resolverLaberinto("Recursivo"));
        botonCache.addActionListener(e -> resolverLaberinto("Cache"));
        botonLimpiar.addActionListener(e -> limpiarLaberinto());
        
        panelResolucion.add(botonBFS);
        panelResolucion.add(botonDFS);
        panelResolucion.add(botonRecursivo);
        panelResolucion.add(botonCache);
        panelResolucion.add(botonLimpiar);

        
        JPanel panelConfiguracion = new JPanel();
        panelConfiguracion.setLayout(new GridLayout(6, 2));

        panelConfiguracion.add(new JLabel("Filas:"));
        JTextField campoFilas = new JTextField(String.valueOf(filas));
        panelConfiguracion.add(campoFilas);

        panelConfiguracion.add(new JLabel("Columnas:"));
        JTextField campoColumnas = new JTextField(String.valueOf(columnas));
        panelConfiguracion.add(campoColumnas);

        panelConfiguracion.add(new JLabel("Inicio X:"));
        campoInicioX = new JTextField("0");
        panelConfiguracion.add(campoInicioX);

        panelConfiguracion.add(new JLabel("Inicio Y:"));
        campoInicioY = new JTextField("0");
        panelConfiguracion.add(campoInicioY);

        panelConfiguracion.add(new JLabel("Fin X:"));
        campoFinX = new JTextField(String.valueOf(filas - 1));
        panelConfiguracion.add(campoFinX);

        panelConfiguracion.add(new JLabel("Fin Y:"));
        campoFinY = new JTextField(String.valueOf(columnas - 1));
        panelConfiguracion.add(campoFinY);

        JButton botonActualizar = new JButton("Actualizar tamaño");

        botonActualizar.addActionListener(e -> {
            try {
                int nuevasFilas = Integer.parseInt(campoFilas.getText());
                int nuevasColumnas = Integer.parseInt(campoColumnas.getText());
                if (nuevasFilas > 0 && nuevasColumnas > 0) {
                    filas = nuevasFilas;
                    columnas = nuevasColumnas;
                    panelLaberinto.setLayout(new GridLayout(filas, columnas));
                    inicializarBotones();
                } else {
                    JOptionPane.showMessageDialog(this, "El tamaño debe ser mayor que 0.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese números válidos.");
            }
        });

        panelConfiguracion.add(botonActualizar);
        JPanel panelTiempo = new JPanel();
        panelTiempo.add(new JLabel("Tiempo:"));
        campoTiempo = new JTextField();
        campoTiempo.setEditable(false); // Campo no editable
        campoTiempo.setPreferredSize(new Dimension(150, 30));
        panelTiempo.add(campoTiempo);

        panelBotones.add(panelResolucion);
        panelBotones.add(panelConfiguracion);
        panelBotones.add(panelTiempo);

        add(panelBotones, BorderLayout.SOUTH);
    }

    private void resolverLaberinto(String metodo) {
        long startTime = System.nanoTime(); // Inicia la medición del tiempo


        boolean[][] laberintoBool = controlador.convertirLaberinto(laberinto);
        List<LaberintoModelo> camino = null;

        int inicioX = Integer.parseInt(campoInicioX.getText());
        int inicioY = Integer.parseInt(campoInicioY.getText());
        int finX = Integer.parseInt(campoFinX.getText());
        int finY = Integer.parseInt(campoFinY.getText());

        switch (metodo) {
            case "BFS":
                camino = controlador.getPathBFS(laberintoBool, inicioX, inicioY, finX, finY);
                break;
            case "DFS":
                camino = controlador.getPathDFS(laberintoBool, inicioX, inicioY, finX, finY);
                break;
            case "Recursivo":
                camino = controlador.getPathRecursivo(laberintoBool, inicioX, inicioY, finX, finY);
                break;
            case "Cache":
                camino = controlador.getPathCache(laberintoBool, inicioX, inicioY, finX, finY);
                break;
        }
        long endTime = System.nanoTime(); // Finaliza la medición del tiempo
        double elapsedTime = (endTime - startTime) / 1_000_000_000.0; // Calcula el tiempo en segundos

        campoTiempo.setText(String.format("%.4f segundos", elapsedTime)); // Muestra el tiempo en el campo de texto

        if (camino != null) {
            pintarCamino(camino);
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró un camino.");
        }
    }

    private void limpiarLaberinto() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (!laberinto[i][j]) { // Solo limpia si no es un bloque
                    laberinto[i][j] = false;
                    botones[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }

    public void actualizarVista(LaberintoModelo nodo) {
        SwingUtilities.invokeLater(() -> {
            int x = nodo.getInicioX();
            int y = nodo.getInicioY();
            if (botones[x][y].getBackground() == Color.WHITE) { // Solo pinta si no es un bloque
                botones[x][y].setBackground(Color.GREEN);
            }
            try {
                Thread.sleep(10); // Retardo de 10 milisegundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private void pintarCamino(List<LaberintoModelo> camino) {
        for (LaberintoModelo nodo : camino) {
            int x = nodo.getInicioX();
            int y = nodo.getInicioY();
            if (botones[x][y].getBackground() == Color.WHITE) { // Solo pinta si no es un bloque
                botones[x][y].setBackground(Color.BLUE);
            }
        }
    }
    
    
}
