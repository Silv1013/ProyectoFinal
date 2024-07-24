package Controlador;

import Modelo.LaberintoModelo;
import Vista.LaberintoVista;

import java.util.*;

public class LaberintoControlador {
    private LaberintoVista vista;

    public LaberintoControlador(LaberintoVista vista) {
        this.vista = vista;
    }

    public List<LaberintoModelo> getPathBFS(boolean[][] laberinto, int inicioX, int inicioY, int finX, int finY) {
        int filas = laberinto.length;
        int columnas = laberinto[0].length;
        int[][] direcciones = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Queue<LaberintoModelo> cola = new LinkedList<>();
        Set<String> visitados = new HashSet<>();
        cola.add(new LaberintoModelo(inicioX, inicioY, finX, finY));
        visitados.add(inicioX + "," + inicioY);

        while (!cola.isEmpty()) {
            LaberintoModelo actual = cola.poll();
            vista.actualizarVista(actual);
            if (actual.getInicioX() == finX && actual.getInicioY() == finY) {
                return reconstruirCamino(actual);
            }
            for (int[] direccion : direcciones) {
                int nuevoX = actual.getInicioX() + direccion[0];
                int nuevoY = actual.getInicioY() + direccion[1];
                if (esValido(nuevoX, nuevoY, filas, columnas, laberinto, visitados)) {
                    LaberintoModelo vecino = new LaberintoModelo(nuevoX, nuevoY, finX, finY);
                    vecino.setPadre(actual);
                    cola.add(vecino);
                    visitados.add(nuevoX + "," + nuevoY);
                }
            }
        }

        return Collections.emptyList();
    }

    public List<LaberintoModelo> getPathDFS(boolean[][] laberinto, int inicioX, int inicioY, int finX, int finY) {
        int filas = laberinto.length;
        int columnas = laberinto[0].length;
        int[][] direcciones = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Stack<LaberintoModelo> pila = new Stack<>();
        Set<String> visitados = new HashSet<>();
        pila.add(new LaberintoModelo(inicioX, inicioY, finX, finY));
        visitados.add(inicioX + "," + inicioY);

        while (!pila.isEmpty()) {
            LaberintoModelo actual = pila.pop();
            vista.actualizarVista(actual);
            if (actual.getInicioX() == finX && actual.getInicioY() == finY) {
                return reconstruirCamino(actual);
            }
            for (int[] direccion : direcciones) {
                int nuevoX = actual.getInicioX() + direccion[0];
                int nuevoY = actual.getInicioY() + direccion[1];
                if (esValido(nuevoX, nuevoY, filas, columnas, laberinto, visitados)) {
                    LaberintoModelo vecino = new LaberintoModelo(nuevoX, nuevoY, finX, finY);
                    vecino.setPadre(actual);
                    pila.add(vecino);
                    visitados.add(nuevoX + "," + nuevoY);
                }
            }
        }

        return Collections.emptyList();
    }

    public List<LaberintoModelo> getPathRecursivo(boolean[][] laberinto, int inicioX, int inicioY, int finX, int finY) {
        Set<String> visitados = new HashSet<>();
        List<LaberintoModelo> camino = new ArrayList<>();
        if (buscarRecursivo(laberinto, inicioX, inicioY, finX, finY, visitados, camino)) {
            return camino;
        }
        return Collections.emptyList();
    }

    private boolean buscarRecursivo(boolean[][] laberinto, int x, int y, int finX, int finY, Set<String> visitados, List<LaberintoModelo> camino) {
        if (x == finX && y == finY) {
            camino.add(new LaberintoModelo(x, y, finX, finY));
            return true;
        }

        int[][] direcciones = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        visitados.add(x + "," + y);
        camino.add(new LaberintoModelo(x, y, finX, finY));
        vista.actualizarVista(new LaberintoModelo(x, y, finX, finY));

        for (int[] direccion : direcciones) {
            int nuevoX = x + direccion[0];
            int nuevoY = y + direccion[1];
            if (esValido(nuevoX, nuevoY, laberinto.length, laberinto[0].length, laberinto, visitados)) {
                if (buscarRecursivo(laberinto, nuevoX, nuevoY, finX, finY, visitados, camino)) {
                    return true;
                }
            }
        }

        camino.remove(camino.size() - 1);
        return false;
    }

    public List<LaberintoModelo> getPathCache(boolean[][] laberinto, int inicioX, int inicioY, int finX, int finY) {
        Set<String> visitados = new HashSet<>();
        Map<String, List<LaberintoModelo>> cache = new HashMap<>();
        if (buscarConCache(laberinto, inicioX, inicioY, finX, finY, visitados, new ArrayList<>(), cache)) {
            return cache.get(finX + "," + finY);
        }
        return Collections.emptyList();
    }

    private boolean buscarConCache(boolean[][] laberinto, int x, int y, int finX, int finY, Set<String> visitados, List<LaberintoModelo> camino, Map<String, List<LaberintoModelo>> cache) {
        if (cache.containsKey(x + "," + y)) {
            camino.addAll(cache.get(x + "," + y));
            return true;
        }

        if (x == finX && y == finY) {
            camino.add(new LaberintoModelo(x, y, finX, finY));
            cache.put(x + "," + y, new ArrayList<>(camino));
            return true;
        }

        int[][] direcciones = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        visitados.add(x + "," + y);
        camino.add(new LaberintoModelo(x, y, finX, finY));
        vista.actualizarVista(new LaberintoModelo(x, y, finX, finY));

        for (int[] direccion : direcciones) {
            int nuevoX = x + direccion[0];
            int nuevoY = y + direccion[1];
            if (esValido(nuevoX, nuevoY, laberinto.length, laberinto[0].length, laberinto, visitados)) {
                if (buscarConCache(laberinto, nuevoX, nuevoY, finX, finY, visitados, camino, cache)) {
                    return true;
                }
            }
        }

        camino.remove(camino.size() - 1);
        return false;
    }

    public boolean esValido(int x, int y, int filas, int columnas, boolean[][] laberinto, Set<String> visitados) {
        return x >= 0 && x < filas && y >= 0 && y < columnas && !laberinto[x][y] && !visitados.contains(x + "," + y);
    }

    private List<LaberintoModelo> reconstruirCamino(LaberintoModelo nodo) {
        List<LaberintoModelo> camino = new ArrayList<>();
        while (nodo != null) {
            camino.add(nodo);
            nodo = nodo.getPadre();
        }
        Collections.reverse(camino);
        return camino;
    }

    public boolean[][] convertirLaberinto(boolean[][] laberinto) {
        int filas = laberinto.length;
        int columnas = laberinto[0].length;
        boolean[][] nuevoLaberinto = new boolean[filas][columnas];
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                nuevoLaberinto[i][j] = laberinto[i][j];
            }
        }
        return nuevoLaberinto;
    }
    
}
