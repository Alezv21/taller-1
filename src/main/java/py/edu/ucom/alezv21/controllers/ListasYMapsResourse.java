package py.edu.ucom.alezv21.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/Clase-3")

public class ListasYMapsResourse {

    public List<Integer> getLista(){
        List<Integer> listaInteger = new ArrayList<>();
        listaInteger.add(1);
        listaInteger.add(2);
        listaInteger.add(3);

        return listaInteger;
    }
    
    @GET
    @Path("maps")
    public Map<String,Object> getMaps(){
        Map<String,Object> maps = new HashMap<>();
        List<Integer> listaInteger = new ArrayList<>();
        listaInteger.add(1);
        listaInteger.add(2);
        listaInteger.add(3);

        Map<String,Object> mapsItem = new HashMap<>();
        mapsItem.put("clase-3", "25/09/2023");
        mapsItem.put("clase-4", 1000000);

        
        maps.put("clase-3", "25/09/2023");
        maps.put("clase-4", 1000000);
        maps.put("clase-5", new Date());
        maps.put("clase-6", listaInteger);
        maps.put("clase-7", listaInteger);

        return maps;

    }
    @GET
    @Path("ejercicio-1")

    public Map<String,Integer> getFrecuencias(){
        

        return contadorDePalabras();

    }

    public Map<String,Integer> contadorDePalabras(){
        String texto = "Ejemplo para contar palabras";

        String[] palabras = texto.split("\\s+");
        List<String> listaPalabras = new ArrayList<>();

        for (int i = 0; i < palabras.length; i++) {
            palabras[i] = palabras[i].replaceAll("^[a-zA-Z]", "").toLowerCase();
            
            if (!palabras[i].isEmpty()) {
                listaPalabras.add(palabras[i]);
            }
        }
        Map<String, Integer> contadorPalabras = new HashMap<>();

        for (String palabra : listaPalabras) {
            if(contadorPalabras.containsKey(palabra)){

                int frecuencia = contadorPalabras.get(palabra);
                contadorPalabras.put(palabra, frecuencia + 1);
            }else{
                contadorPalabras.put(palabra, 1);
            }
        }

        return contadorPalabras;

    }




}
