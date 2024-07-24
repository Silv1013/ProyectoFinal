import java.util.List;

import Controlador.LaberintoControlador;
import Modelo.LaberintoModelo;
import Vista.LaberintoVista;

public class App {
    public static void main(String[] args) throws Exception {
        //System.out.println("Hello, World!");
        //runEjercicio();
        new LaberintoVista();
        
    }
    /*public static void runEjercicio() {

        boolean[][] laberinto = {
            { true, true, true, true },
            { false, false, false, true },
            { true, true, false, true },
            { true, true, false, true }
            };

            System.out.println("\n Patch usando recursivo: ");
             List<LaberintoModelo> path = LaberintoControlador.getPathRecursivo(laberinto);
        for (LaberintoModelo labe : path) {
            System.out.println(labe);
        }

        System.out.println("\nPath usandi BFS: ");
        List<LaberintoModelo> pathBFS = LaberintoControlador.getPathBFS(laberinto);
        for (LaberintoModelo labe : pathBFS) {
            System.out.println(labe);
        }

        System.out.println("\nPath usando DFS:");
        List<LaberintoModelo> pathDFS = LaberintoControlador.getPathDFS(laberinto);
        for (LaberintoModelo labe : pathDFS) {
            System.out.println(labe);
        }

         

    } */
      }
    


