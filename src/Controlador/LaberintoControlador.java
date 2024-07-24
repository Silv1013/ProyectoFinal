package Controlador;

import Modelo.LaberintoModelo;
import Vista.LaberintoVista;

import java.util.*;

public class LaberintoControlador {
    private LaberintoVista vista; // Referencia a la vista que se utiliza para actualizar la visualización del laberinto

    // Constructor que inicializa el controlador con la vista proporcionada
    public LaberintoControlador(LaberintoVista vista) {
        this.vista = vista;
    }

    // Método para obtener el camino usando el algoritmo BFS (Breadth-First Search)
    public List<LaberintoModelo> getPathBFS(boolean[][] laberinto, int inicioX, int inicioY, int finX, int finY) {
        int filas = laberinto.length; // Número de filas en el laberinto
        int columnas = laberinto[0].length; // Número de columnas en el laberinto
        int[][] direcciones = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // Direcciones de movimiento (derecha, abajo, izquierda, arriba)
        Queue<LaberintoModelo> cola = new LinkedList<>(); // Cola para la implementación del BFS
        Set<String> visitados = new HashSet<>(); // Conjunto para almacenar las celdas visitadas
        cola.add(new LaberintoModelo(inicioX, inicioY, finX, finY)); // Añade el nodo inicial a la cola
        visitados.add(inicioX + "," + inicioY); // Marca el nodo inicial como visitado

        while (!cola.isEmpty()) {
            LaberintoModelo actual = cola.poll(); // Obtiene el nodo actual de la cola
            vista.actualizarVista(actual); // Actualiza la vista con el nodo actual
            if (actual.getInicioX() == finX && actual.getInicioY() == finY) {
                return reconstruirCamino(actual); // Si se llega al nodo final, reconstruye el camino
            }
            for (int[] direccion : direcciones) {
                int nuevoX = actual.getInicioX() + direccion[0]; // Calcula la nueva posición X
                int nuevoY = actual.getInicioY() + direccion[1]; // Calcula la nueva posición Y
                if (esValido(nuevoX, nuevoY, filas, columnas, laberinto, visitados)) {
                    LaberintoModelo vecino = new LaberintoModelo(nuevoX, nuevoY, finX, finY); // Crea un nuevo nodo vecino
                    vecino.setPadre(actual); // Establece el nodo actual como padre del nodo vecino
                    cola.add(vecino); // Añade el nodo vecino a la cola
                    visitados.add(nuevoX + "," + nuevoY); // Marca el nodo vecino como visitado
                }
            }
        }

        return Collections.emptyList(); // Si no se encuentra un camino, devuelve una lista vacía
    }

    // Método para obtener el camino usando el algoritmo DFS (Depth-First Search)
    public List<LaberintoModelo> getPathDFS(boolean[][] laberinto, int inicioX, int inicioY, int finX, int finY) {
        int filas = laberinto.length; // Número de filas en el laberinto
        int columnas = laberinto[0].length; // Número de columnas en el laberinto
        int[][] direcciones = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // Direcciones de movimiento (derecha, abajo, izquierda, arriba)
        Stack<LaberintoModelo> pila = new Stack<>(); // Pila para la implementación del DFS
        Set<String> visitados = new HashSet<>(); // Conjunto para almacenar las celdas visitadas
        pila.add(new LaberintoModelo(inicioX, inicioY, finX, finY)); // Añade el nodo inicial a la pila
        visitados.add(inicioX + "," + inicioY); // Marca el nodo inicial como visitado

        while (!pila.isEmpty()) {
            LaberintoModelo actual = pila.pop(); // Obtiene el nodo actual de la pila
            vista.actualizarVista(actual); // Actualiza la vista con el nodo actual
            if (actual.getInicioX() == finX && actual.getInicioY() == finY) {
                return reconstruirCamino(actual); // Si se llega al nodo final, reconstruye el camino
            }
            for (int[] direccion : direcciones) {
                int nuevoX = actual.getInicioX() + direccion[0]; // Calcula la nueva posición X
                int nuevoY = actual.getInicioY() + direccion[1]; // Calcula la nueva posición Y
                if (esValido(nuevoX, nuevoY, filas, columnas, laberinto, visitados)) {
                    LaberintoModelo vecino = new LaberintoModelo(nuevoX, nuevoY, finX, finY); // Crea un nuevo nodo vecino
                    vecino.setPadre(actual); // Establece el nodo actual como padre del nodo vecino
                    pila.add(vecino); // Añade el nodo vecino a la pila
                    visitados.add(nuevoX + "," + nuevoY); // Marca el nodo vecino como visitado
                }
            }
        }

        return Collections.emptyList(); // Si no se encuentra un camino, devuelve una lista vacía
    }

    // Método para obtener el camino usando el algoritmo recursivo
    public List<LaberintoModelo> getPathRecursivo(boolean[][] laberinto, int inicioX, int inicioY, int finX, int finY) {
        Set<String> visitados = new HashSet<>(); // Conjunto para almacenar las celdas visitadas
        List<LaberintoModelo> camino = new ArrayList<>(); // Lista para almacenar el camino encontrado
        if (buscarRecursivo(laberinto, inicioX, inicioY, finX, finY, visitados, camino)) {
            return camino; // Si se encuentra un camino, lo devuelve
        }
        return Collections.emptyList(); // Si no se encuentra un camino, devuelve una lista vacía
    }

    // Método recursivo para buscar el camino en el laberinto
    private boolean buscarRecursivo(boolean[][] laberinto, int x, int y, int finX, int finY, Set<String> visitados, List<LaberintoModelo> camino) {
        if (x == finX && y == finY) { // Si se llega al destino
            camino.add(new LaberintoModelo(x, y, finX, finY)); // Añade el nodo final al camino
            return true;
        }

        int[][] direcciones = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // Direcciones de movimiento
        visitados.add(x + "," + y); // Marca la celda actual como visitada
        camino.add(new LaberintoModelo(x, y, finX, finY)); // Añade la celda actual al camino
        vista.actualizarVista(new LaberintoModelo(x, y, finX, finY)); // Actualiza la vista con la celda actual

        for (int[] direccion : direcciones) {
            int nuevoX = x + direccion[0]; // Calcula la nueva posición X
            int nuevoY = y + direccion[1]; // Calcula la nueva posición Y
            if (esValido(nuevoX, nuevoY, laberinto.length, laberinto[0].length, laberinto, visitados)) {
                if (buscarRecursivo(laberinto, nuevoX, nuevoY, finX, finY, visitados, camino)) {
                    return true; // Si se encuentra el camino desde el nodo vecino, lo devuelve
                }
            }
        }

        camino.remove(camino.size() - 1); // Si no se encuentra el camino, elimina el nodo actual del camino
        return false;
    }

    // Método para obtener el camino usando el algoritmo con cache
    public List<LaberintoModelo> getPathCache(boolean[][] laberinto, int inicioX, int inicioY, int finX, int finY) {
        Set<String> visitados = new HashSet<>(); // Conjunto para almacenar las celdas visitadas
        Map<String, List<LaberintoModelo>> cache = new HashMap<>(); // Mapa para almacenar los caminos cacheados
        if (buscarConCache(laberinto, inicioX, inicioY, finX, finY, visitados, new ArrayList<>(), cache)) {
            return cache.get(finX + "," + finY); // Devuelve el camino cacheado si se encuentra
        }
        return Collections.emptyList(); // Si no se encuentra un camino, devuelve una lista vacía
    }

    // Método recursivo para buscar el camino en el laberinto usando cache
    private boolean buscarConCache(boolean[][] laberinto, int x, int y, int finX, int finY, Set<String> visitados, List<LaberintoModelo> camino, Map<String, List<LaberintoModelo>> cache) {
        if (cache.containsKey(x + "," + y)) { // Si el camino ya está cacheado
            camino.addAll(cache.get(x + "," + y)); // Añade el camino cacheado al camino actual
            return true;
        }

        if (x == finX && y == finY) { // Si se llega al destino
            camino.add(new LaberintoModelo(x, y, finX, finY)); // Añade el nodo final al camino
            cache.put(x + "," + y, new ArrayList<>(camino)); // Cachea el camino
            return true;
        }

        int[][] direcciones = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // Direcciones de movimiento
        visitados.add(x + "," + y); // Marca la celda actual como visitada
        camino.add(new LaberintoModelo(x, y, finX, finY)); // Añade la celda actual al camino
        vista.actualizarVista(new LaberintoModelo(x, y, finX, finY)); // Actualiza la vista con la celda actual

        for (int[] direccion : direcciones) {
            int nuevoX = x + direccion[0]; // Calcula la nueva posición X
            int nuevoY = y + direccion[1]; // Calcula la nueva posición Y
            if (esValido(nuevoX, nuevoY, laberinto.length, laberinto[0].length, laberinto, visitados)) {
                if (buscarConCache(laberinto, nuevoX, nuevoY, finX, finY, visitados, camino, cache)) {
                    return true; // Si se encuentra el camino desde el nodo vecino, lo devuelve
                }
            }
        }

        camino.remove(camino.size() - 1); // Si no se encuentra el camino, elimina el nodo actual del camino
        return false;
    }

    // Método para verificar si una celda es válida para moverse
    public boolean esValido(int x, int y, int filas, int columnas, boolean[][] laberinto, Set<String> visitados) {
        return x >= 0 && x < filas && y >= 0 && y < columnas // Verifica si la celda está dentro de los límites
               && !laberinto[x][y] // Verifica si la celda no es un muro
               && !visitados.contains(x + "," + y); // Verifica si la celda no ha sido visitada
    }

    // Método para reconstruir el camino desde el nodo final hacia el nodo inicial
    private List<LaberintoModelo> reconstruirCamino(LaberintoModelo nodo) {
        List<LaberintoModelo> camino = new ArrayList<>(); // Lista para almacenar el camino
        while (nodo != null) { // Recorre el camino desde el nodo final hasta el nodo inicial
            camino.add(nodo); // Añade el nodo al camino
            nodo = nodo.getPadre(); // Avanza al nodo padre
        }
        Collections.reverse(camino); // Reversa el camino para que esté en el orden correcto
        return camino;
    }

    // Método para convertir un laberinto en una nueva instancia
    public boolean[][] convertirLaberinto(boolean[][] laberinto) {
        int filas = laberinto.length; // Número de filas en el laberinto
        int columnas = laberinto[0].length; // Número de columnas en el laberinto
        boolean[][] nuevoLaberinto = new boolean[filas][columnas]; // Crea una nueva instancia del laberinto
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                nuevoLaberinto[i][j] = laberinto[i][j]; // Copia cada celda del laberinto original
            }
        }
        return nuevoLaberinto; // Devuelve la nueva instancia del laberinto
    }
}
