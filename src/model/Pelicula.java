package model;

import java.io.Serializable;

public class Pelicula implements Comparable<Pelicula>, CSVSerializable, Serializable {
    private int id;
    private String titulo;
    private String director;
    private Genero genero;

    public Pelicula(int id, String titulo, String director, Genero genero) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.genero = genero;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDirector() { return director; }
    public Genero getGenero() { return genero; }

    @Override
    public int compareTo(Pelicula o) {
        return Integer.compare(this.id, o.id);
    }

    @Override
    public String toCSV() {
        return id + "," + titulo + "," + director + "," + genero.name();
    }

    public static Pelicula fromCSV(String csv) {
        String[] datos = csv.split(",");
        return new Pelicula(Integer.parseInt(datos[0]), datos[1], datos[2], Genero.valueOf(datos[3]));
    }

    @Override
    public String toString() {
        return id + " - " + titulo + " - " + director + " - " + genero;
    }
}
