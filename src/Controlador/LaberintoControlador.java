package Controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Modelo.LaberintoModelo;

public class LaberintoControlador {

     public static List<LaberintoModelo> getPathRecursivo(boolean[][] laberinto) {
        //4x4
        List<LaberintoModelo> path = new ArrayList<>();

        if (laberinto == null || laberinto.length == 0 || laberinto[0].length == 0) {
            return path;
            
        }
        if(pathFinderRecursivo(laberinto, 0, 0, path)){
            return path;

        }

        return new ArrayList<>();



       
    }
    private static boolean pathFinderRecursivo(boolean[][] laberinto, int filas, int columnas, List<LaberintoModelo> path){

        int m = laberinto.length;
        int n = laberinto[0].length;

        if(filas >= m || columnas >= n || !laberinto[filas][columnas]){
            return false;
        }
        path.add(new LaberintoModelo(filas, columnas));

        if(filas == m -1 && columnas == n-1){
            return true;
        }
        if(pathFinderRecursivo(laberinto, filas, columnas + 1, path)){
            return true;
        }
        if(pathFinderRecursivo(laberinto, filas + 1, columnas, path)){
            return true;
        }

        path.remove(path.size()-1);

        return false;


    }

    public boolean getPathCache(Boolean [][] laberinto, int filas, int columnas, List<LaberintoModelo> path, Map<LaberintoModelo, Boolean> cache ){
        if(filas >= laberinto.length || columnas >= laberinto[0].length || !laberinto[filas][columnas]){
            return false;
        }
        LaberintoModelo point = new LaberintoModelo(filas, columnas);
        if(cache.containsKey(point)){
            return cache.get(point);
            }
        boolean isAtEnd = (filas == laberinto.length-1 && columnas == laberinto[0].length-1);
        boolean success = false;
        if(isAtEnd || getPathCache(laberinto, filas +1, columnas, path, cache) || getPathCache(laberinto, filas, columnas + 1, path, cache)){
            path.add(point);
            success = true;
        }

        cache.put(point, success);
        return success;

        


    }


    
}
