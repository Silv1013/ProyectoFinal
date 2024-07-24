package Modelo;

public class LaberintoModelo {
    private int inicioX;
    private int inicioY;
    private int finX;
    private int finY;
    private LaberintoModelo padre;

    public LaberintoModelo(int inicioX, int inicioY, int finX, int finY) {
        this.inicioX = inicioX;
        this.inicioY = inicioY;
        this.finX = finX;
        this.finY = finY;
    }

    public int getInicioX() {
        return inicioX;
    }

    public int getInicioY() {
        return inicioY;
    }

    public int getFinX() {
        return finX;
    }

    public int getFinY() {
        return finY;
    }

    public LaberintoModelo getPadre() {
        return padre;
    }

    public void setPadre(LaberintoModelo padre) {
        this.padre = padre;
    }
}
