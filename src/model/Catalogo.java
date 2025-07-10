package model;

import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Catalogo<T extends CSVSerializable & Comparable<T>> implements Serializable {
    private List<T> elementos = new ArrayList<>();

    public void agregar(T elemento) {
        elementos.add(elemento);
    }

    public T obtener(int indice) {
        return elementos.get(indice);
    }

    public void eliminar(int indice) {
        elementos.remove(indice);
    }

    public List<T> filtrar(Predicate<T> criterio) {
        List<T> resultado = new ArrayList<>();
        for (T elem : elementos) {
            if (criterio.test(elem)) {
                resultado.add(elem);
            }
        }
        return resultado;
    }

    public void ordenar() {
        Collections.sort(elementos);
    }

    public void ordenar(Comparator<T> comparador) {
        elementos.sort(comparador);
    }

    public void paraCadaElemento(Consumer<T> consumidor) {
        elementos.forEach(consumidor);
    }

    public void guardarEnArchivo(String ruta) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ruta))) {
            out.writeObject(elementos);
        }
    }

    public void cargarDesdeArchivo(String ruta) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ruta))) {
            elementos = (List<T>) in.readObject();
        }
    }

    public void guardarEnCSV(String ruta) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            for (T elemento : elementos) {
                writer.write(elemento.toCSV());
                writer.newLine();
            }
        }
    }

    public void cargarDesdeCSV(String ruta, Function<String, T> desdeCSV) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                elementos.add(desdeCSV.apply(linea));
            }
        }
    }
}
