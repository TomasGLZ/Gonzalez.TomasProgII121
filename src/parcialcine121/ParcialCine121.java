package parcialcine121;


import config.config;
import model.Catalogo;
import model.Genero;
import model.Pelicula;

import java.io.File;
import java.io.IOException;
import java.util.Comparator;

public class ParcialCine121 {
    public static void main(String[] args) {
        try {
            // Crear la carpeta "resources" si no existe
            File carpeta = new File("src/resources");
            if (!carpeta.exists()) {
                carpeta.mkdirs();
            }

            // Crear un catálogo de películas
            Catalogo<Pelicula> catalogoPeliculas = new Catalogo<>();
            catalogoPeliculas.agregar(new Pelicula(1, "El Padrino", "Francis Ford Coppola", Genero.DRAMA));
            catalogoPeliculas.agregar(new Pelicula(2, "La La Land", "Damien Chazelle", Genero.COMEDIA));
            catalogoPeliculas.agregar(new Pelicula(3, "Guerra Mundial Z", "Marc Forster", Genero.TERROR));
            catalogoPeliculas.agregar(new Pelicula(4, "Toy Story", "John Lasseter", Genero.ANIMACION));
            catalogoPeliculas.agregar(new Pelicula(5, "The Social Dilemma", "Jeff Orlowski", Genero.DOCUMENTAL));

            // Mostrar todas las películas
            System.out.println("Catalogo de peliculas:");
            catalogoPeliculas.paraCadaElemento(System.out::println);

            // Filtrar por género COMEDIA
            System.out.println("\nPeliculas de genero COMEDIA:");
            catalogoPeliculas.filtrar(p -> p.getGenero() == Genero.COMEDIA)
                             .forEach(System.out::println);

            // Filtrar por palabra "Guerra" en el título
            System.out.println("\nPeliculas cuyo titulo contiene 'Guerra':");
            catalogoPeliculas.filtrar(p -> p.getTitulo().toLowerCase().contains("guerra"))
                             .forEach(System.out::println);

            // Ordenar de manera natural (por id)
            System.out.println("\nPeliculas ordenadas de manera natural (por id):");
            catalogoPeliculas.ordenar();
            catalogoPeliculas.paraCadaElemento(System.out::println);

            // Ordenar por título usando Comparator
            System.out.println("\nPeliculas ordenadas por titulo:");
            catalogoPeliculas.ordenar(Comparator.comparing(Pelicula::getTitulo));
            catalogoPeliculas.paraCadaElemento(System.out::println);

            // Guardar en archivo binario
            catalogoPeliculas.guardarEnArchivo(config.RUTA_BINARIO_PELICULAS);

            // Cargar desde archivo binario
            Catalogo<Pelicula> catalogoCargado = new Catalogo<>();
            catalogoCargado.cargarDesdeArchivo(config.RUTA_BINARIO_PELICULAS);
            System.out.println("\nPeliculas cargadas desde archivo binario:");
            catalogoCargado.paraCadaElemento(System.out::println);

            // Guardar en CSV
            catalogoPeliculas.guardarEnCSV(config.RUTA_CSV_PELICULAS);

            // Cargar desde CSV
            catalogoCargado.cargarDesdeCSV(config.RUTA_CSV_PELICULAS, Pelicula::fromCSV);
            System.out.println("\nPeliculas cargadas desde archivo CSV:");
            catalogoCargado.paraCadaElemento(System.out::println);

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
