import java.util.List;

import Controlador.LaberintoControlador;
import Modelo.LaberintoModelo;

public class App {
    public static void main(String[] args) throws Exception {
        //System.out.println("Hello, World!");
        runEjercicio();
        
    }
    public static void runEjercicio() {

        boolean[][] laberinto = {
            { true, true, true, true },
            { false, false, false, true },
            { true, true, false, true },
            { true, true, false, true }
            };
             List<LaberintoModelo> path = LaberintoControlador.getPathRecursivo(laberinto);
        for (LaberintoModelo labe : path) {
            System.out.println(labe);
        }

         

    }
    }
    


