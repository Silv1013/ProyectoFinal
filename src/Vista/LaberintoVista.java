package Vista;

import Modelo.LaberintoModelo;
import Controlador.LaberintoControlador;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// Clase principal que define la vista del laberinto
public class LaberintoVista extends JFrame {
    private int filas = 5; // Número inicial de filas del laberinto
    private int columnas = 5; // Número inicial de columnas del laberinto
    private JButton[][] botones; // Matriz de botones que representan las celdas del laberinto
    private boolean[][] laberinto; // Matriz que representa el estado del laberinto (bloqueado o libre)
    private LaberintoControlador controlador; // Controlador que maneja la lógica del laberinto
    private JPanel panelLaberinto; // Panel que contiene el laberinto
    private JPanel panelBotones; // Panel que contiene los botones y campos de texto
    private JTextField campoInicioX; // Campo de texto para la coordenada X de inicio
    private JTextField campoInicioY; // Campo de texto para la coordenada Y de inicio
    private JTextField campoFinX; // Campo de texto para la coordenada X de fin
    private JTextField campoFinY; // Campo de texto para la coordenada Y de fin
    private JTextField campoTiempo; // Campo de texto para mostrar el tiempo de ejecución

    // Constructor de la clase
    public LaberintoVista() {
        controlador = new LaberintoControlador(this); // Inicializa el controlador
        laberinto = new boolean[filas][columnas]; // Inicializa el laberinto con el tamaño predeterminado
        botones = new JButton[filas][columnas]; // Inicializa la matriz de botones

        setLayout(new BorderLayout()); // Establece el diseño de la ventana principal
        inicializarPanelLaberinto(); // Inicializa el panel del laberinto
        inicializarPanelBotones(); // Inicializa el panel de botones y campos de texto

        setTitle("Laberinto"); // Establece el título de la ventana
        setSize(800, 900); // Establece el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Establece la operación de cierre de la ventana
        setVisible(true); // Hace visible la ventana
    }

    // Inicializa el panel del laberinto
    private void inicializarPanelLaberinto() {
        panelLaberinto = new JPanel(new GridLayout(filas, columnas)); // Establece el diseño del panel del laberinto
        inicializarBotones(); // Inicializa los botones que representan las celdas del laberinto
        add(panelLaberinto, BorderLayout.CENTER); // Añade el panel del laberinto al centro de la ventana
    }

    // Inicializa los botones que representan las celdas del laberinto
    private void inicializarBotones() {
        panelLaberinto.removeAll(); // Elimina todos los componentes actuales del panel

        botones = new JButton[filas][columnas]; // Re-inicializa la matriz de botones
        laberinto = new boolean[filas][columnas]; // Re-inicializa el estado del laberinto

        // Crea los botones y los añade al panel
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                JButton boton = new JButton();
                boton.setBackground(Color.WHITE); // Establece el color de fondo inicial de los botones
                final int x = i;
                final int y = j;
                boton.addActionListener(e -> {
                    if (boton.getBackground() == Color.WHITE) {
                        boton.setBackground(Color.BLACK); // Marca la celda como bloqueada
                        laberinto[x][y] = true; // Actualiza el estado del laberinto
                    } else if (boton.getBackground() == Color.BLACK) {
                        boton.setBackground(Color.WHITE); // Desbloquea la celda
                        laberinto[x][y] = false; // Actualiza el estado del laberinto
                    }
                });
                botones[i][j] = boton; // Añade el botón a la matriz
                panelLaberinto.add(boton); // Añade el botón al panel
            }
        }
        panelLaberinto.revalidate(); // Vuelve a validar el panel
        panelLaberinto.repaint(); // Redibuja el panel
    }

    // Inicializa el panel de botones y campos de texto
    private void inicializarPanelBotones() {
        panelBotones = new JPanel();
        panelBotones.setLayout(new GridBagLayout()); // Establece el diseño del panel de botones
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5); // Establece el espacio entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Panel de resolución de laberinto
        JPanel panelResolucion = new JPanel();
        panelResolucion.setLayout(new GridLayout(1, 5, 5, 5)); // Establece el diseño del panel de resolución

        // Botones para resolver el laberinto con diferentes métodos
        JButton botonBFS = new JButton("Resolver con BFS");
        JButton botonDFS = new JButton("Resolver con DFS");
        JButton botonRecursivo = new JButton("Resolver con Recursivo");
        JButton botonCache = new JButton("Resolver con Cache");
        JButton botonLimpiar = new JButton("Limpiar Laberinto");

        // Añade acción a los botones
        botonBFS.addActionListener(e -> resolverLaberinto("BFS"));
        botonDFS.addActionListener(e -> resolverLaberinto("DFS"));
        botonRecursivo.addActionListener(e -> resolverLaberinto("Recursivo"));
        botonCache.addActionListener(e -> resolverLaberinto("Cache"));
        botonLimpiar.addActionListener(e -> limpiarLaberinto());

        panelResolucion.add(botonBFS); // Añade los botones al panel
        panelResolucion.add(botonDFS);
        panelResolucion.add(botonRecursivo);
        panelResolucion.add(botonCache);
        panelResolucion.add(botonLimpiar);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panelBotones.add(panelResolucion, gbc); // Añade el panel de resolución al panel principal

        // Panel de configuración del laberinto
        JPanel panelConfiguracion = new JPanel(new GridBagLayout());
        GridBagConstraints gbcConfig = new GridBagConstraints();
        gbcConfig.insets = new Insets(5, 5, 5, 5);
        gbcConfig.fill = GridBagConstraints.HORIZONTAL;

        // Campos y etiquetas para la configuración del laberinto
        JLabel lblFilas = new JLabel("Filas:");
        JTextField campoFilas = new JTextField(String.valueOf(filas), 5);
        JLabel lblColumnas = new JLabel("Columnas:");
        JTextField campoColumnas = new JTextField(String.valueOf(columnas), 5);
        JLabel lblInicioX = new JLabel("Inicio X:");
        campoInicioX = new JTextField("0", 5);
        JLabel lblInicioY = new JLabel("Inicio Y:");
        campoInicioY = new JTextField("0", 5);
        JLabel lblFinX = new JLabel("Fin X:");
        campoFinX = new JTextField(String.valueOf(filas - 1), 5);
        JLabel lblFinY = new JLabel("Fin Y:");
        campoFinY = new JTextField(String.valueOf(columnas - 1), 5);

        // Añade las etiquetas y campos al panel de configuración
        gbcConfig.gridx = 0;
        gbcConfig.gridy = 0;
        panelConfiguracion.add(lblFilas, gbcConfig);
        gbcConfig.gridx = 1;
        panelConfiguracion.add(campoFilas, gbcConfig);
        gbcConfig.gridx = 0;
        gbcConfig.gridy = 1;
        panelConfiguracion.add(lblColumnas, gbcConfig);
        gbcConfig.gridx = 1;
        panelConfiguracion.add(campoColumnas, gbcConfig);
        gbcConfig.gridx = 0;
        gbcConfig.gridy = 2;
        panelConfiguracion.add(lblInicioX, gbcConfig);
        gbcConfig.gridx = 1;
        panelConfiguracion.add(campoInicioX, gbcConfig);
        gbcConfig.gridx = 0;
        gbcConfig.gridy = 3;
        panelConfiguracion.add(lblInicioY, gbcConfig);
        gbcConfig.gridx = 1;
        panelConfiguracion.add(campoInicioY, gbcConfig);
        gbcConfig.gridx = 0;
        gbcConfig.gridy = 4;
        panelConfiguracion.add(lblFinX, gbcConfig);
        gbcConfig.gridx = 1;
        panelConfiguracion.add(campoFinX, gbcConfig);
        gbcConfig.gridx = 0;
        gbcConfig.gridy = 5;
        panelConfiguracion.add(lblFinY, gbcConfig);
        gbcConfig.gridx = 1;
        panelConfiguracion.add(campoFinY, gbcConfig);

        // Botón para actualizar el tamaño del laberinto
        JButton botonActualizar = new JButton("Actualizar tamaño");
        botonActualizar.addActionListener(e -> {
            try {
                int nuevasFilas = Integer.parseInt(campoFilas.getText()); // Obtiene el número de filas
                int nuevasColumnas = Integer.parseInt(campoColumnas.getText()); // Obtiene el número de columnas
                if (nuevasFilas > 0 && nuevasColumnas > 0) {
                    filas = nuevasFilas;
                    columnas = nuevasColumnas;
                    panelLaberinto.setLayout(new GridLayout(filas, columnas)); // Actualiza el diseño del panel del laberinto
                    inicializarBotones(); // Re-inicializa los botones con el nuevo tamaño
                } else {
                    JOptionPane.showMessageDialog(this, "El tamaño debe ser mayor que 0."); // Mensaje de error si el tamaño no es válido
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese números válidos."); // Mensaje de error si la entrada no es válida
            }
        });

        gbcConfig.gridx = 0;
        gbcConfig.gridy = 6;
        gbcConfig.gridwidth = 2;
        panelConfiguracion.add(botonActualizar, gbcConfig); // Añade el botón de actualizar al panel de configuración

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panelBotones.add(panelConfiguracion, gbc); // Añade el panel de configuración al panel principal

        // Panel de tiempo de ejecución
        JPanel panelTiempo = new JPanel(new GridBagLayout());
        GridBagConstraints gbcTiempo = new GridBagConstraints();
        gbcTiempo.insets = new Insets(5, 5, 5, 5);
        gbcTiempo.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTiempo = new JLabel("Tiempo:"); // Etiqueta para el tiempo de ejecución
        campoTiempo = new JTextField(15); // Campo de texto para mostrar el tiempo de ejecución
        campoTiempo.setEditable(false); // Hace el campo de texto no editable

        gbcTiempo.gridx = 0;
        gbcTiempo.gridy = 0;
        panelTiempo.add(lblTiempo, gbcTiempo); // Añade la etiqueta al panel de tiempo
        gbcTiempo.gridx = 1;
        panelTiempo.add(campoTiempo, gbcTiempo); // Añade el campo de texto al panel de tiempo

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panelBotones.add(panelTiempo, gbc); // Añade el panel de tiempo al panel principal

        add(panelBotones, BorderLayout.SOUTH); // Añade el panel de botones al sur de la ventana
    }

    // Método para resolver el laberinto usando el método especificado
    private void resolverLaberinto(String metodo) {
        long startTime = System.nanoTime(); // Inicia la medición del tiempo

        boolean[][] laberintoBool = controlador.convertirLaberinto(laberinto); // Convierte la matriz de laberinto a un formato compatible
        List<LaberintoModelo> camino = null; // Lista para almacenar el camino encontrado

        // Obtiene las coordenadas de inicio y fin del laberinto
        int inicioX = Integer.parseInt(campoInicioX.getText());
        int inicioY = Integer.parseInt(campoInicioY.getText());
        int finX = Integer.parseInt(campoFinX.getText());
        int finY = Integer.parseInt(campoFinY.getText());

        // Ejecuta el método de resolución correspondiente
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
            pintarCaminoVerde(camino); // Pinta el camino en verde
            pintarCaminoAzul(camino); // Pinta el camino en azul
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró un camino."); // Mensaje si no se encuentra un camino
        }
    }

    // Método para pintar el camino en verde
    private void pintarCaminoVerde(List<LaberintoModelo> camino) {
        for (LaberintoModelo nodo : camino) {
            int x = nodo.getInicioX();
            int y = nodo.getInicioY();
            if (botones[x][y].getBackground() == Color.WHITE) { // Solo pinta si la celda es libre
                botones[x][y].setBackground(Color.GREEN); // Pinta la celda en verde
            }
        }
    }

    // Método para pintar el camino en azul con un retraso
    private void pintarCaminoAzul(List<LaberintoModelo> camino) {
        new Thread(() -> {
            for (LaberintoModelo nodo : camino) {
                SwingUtilities.invokeLater(() -> {
                    int x = nodo.getInicioX();
                    int y = nodo.getInicioY();
                    if (botones[x][y].getBackground() == Color.GREEN) { // Solo pinta si la celda ya está en verde
                        botones[x][y].setBackground(Color.BLUE); // Pinta la celda en azul
                    }
                });
                try {
                    Thread.sleep(300); // Retardo de 300 milisegundos
                } catch (InterruptedException e) {
                    e.printStackTrace(); // Manejo de excepciones
                }
            }
        }).start();
    }

    // Método para limpiar el laberinto, restaurando todas las celdas a su estado original
    private void limpiarLaberinto() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (!laberinto[i][j]) { // Solo limpia si no es un bloque
                    laberinto[i][j] = false; // Actualiza el estado del laberinto
                    botones[i][j].setBackground(Color.WHITE); // Restaura el color de fondo del botón
                }
            }
        }
    }

    // Método para actualizar la vista del laberinto durante la resolución
    public void actualizarVista(LaberintoModelo nodo) {
        SwingUtilities.invokeLater(() -> {
            int x = nodo.getInicioX();
            int y = nodo.getInicioY();
            if (botones[x][y].getBackground() == Color.WHITE) { // Solo pinta si la celda es libre
                botones[x][y].setBackground(Color.GREEN); // Pinta la celda en verde
            }
            try {
                Thread.sleep(10); // Retardo de 10 milisegundos
            } catch (InterruptedException e) {
                e.printStackTrace(); // Manejo de excepciones
            }
        });
    }
}
