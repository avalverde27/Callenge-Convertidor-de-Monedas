import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HistorialDeConversiones {
    private final List<String> historial;

    public HistorialDeConversiones() {
        this.historial = new ArrayList<>();
    }

    public void agregarConversion(String registro) {
        historial.add(registro);
    }

    public void guardarHistorialEnJson(String archivo) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(archivo)) {
            gson.toJson(historial, writer);
        } catch (IOException e) {
            System.err.println("Error al guardar el historial: " + e.getMessage());
        }
    }
}



