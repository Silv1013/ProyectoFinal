package Modelo;

public class LaberintoModelo {
    public int [][] laberinto;
    public int filas;
    public int columnas;
    public int inicioX;
    public int inicioY;
    public int finalX;
    public int finalY;
    
    
    public LaberintoModelo(int filas, int columnas, int inicioX, int inicioY, int finalX, int finalY) {
        this.filas = filas;
        this.columnas = columnas;
        this.inicioX = inicioX;
        this.inicioY = inicioY;
        this.finalX = finalX;
        this.finalY = finalY;

        this.laberinto = new int[filas][columnas];
    }
    public LaberintoModelo(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.laberinto = new int[filas][columnas];
    }



    public int[][] getLaberinto() {
        return laberinto;
    }


    public int getFilas() {
        return filas;
    }


    public int getColumnas() {
        return columnas;
    }


    public int getInicioX() {
        return inicioX;
    }


    public int getInicioY() {
        return inicioY;
    }


    public int getFinalX() {
        return finalX;
    }


    public int getFinalY() {
        return finalY;
    }
    @Override
    public String toString() {
        return "LaberintoModelo{" +
                "filas=" + filas +
                ", columnas=" + columnas +
                '}';
    }

    

    
    

    
    
}
