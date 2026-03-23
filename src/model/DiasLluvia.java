package model;/* LIBRERIAS */

import java.time.LocalDate;
import java.util.*;
import static java.util.Map.entry;

public class DiasLluvia {
    /* ATRIBUTOS */

    // Dia-mes, true/false
    private Map<LocalDate, Boolean> dias = new HashMap<>();

    /* METODO TOSTRING */

    @Override
    public String toString() {
        return "model.DiasLluvia{" +
                "dias=" + dias +
                '}';
    }

    /* OTROS METODOS */

    public boolean registroDia(int dia, int mes, boolean lluvia) {
        // Probar a guardar la fecha (si puede 'true', si no 'false')
        try {
            // Crear objeto fecha
            LocalDate fecha = LocalDate.of(LocalDate.now().getYear(), mes, dia);

            // Guardar fecha y si ha llovido o no
            dias.put(fecha, lluvia);
            return true;
        } catch (Exception e) {
            System.out.println("No se ha podido guardar la fecha.");
            return false;
        }
    }

    public boolean consultarDia(int dia, int mes) {
        try {
            // Crear objeto fecha
            LocalDate fecha = LocalDate.of(LocalDate.now().getYear(), mes, dia);

            // Obtener clave en base a la fecha y devolver el 'true' o 'false' almacenado
            return dias.get(fecha);
        } catch (Exception e) {
            // No puede retornar que un dia es lluvioso si la fecha no es valida
            System.out.println("Fecha no válida.");
            return false;
        }
    }

    public int contarDiasLluviosos() {
        // Inicializar cuenta de dias lluviosos a cero
        int diasLluviosos = 0;

        // Recorrer el Map e incrementar la variable 'diasLluviosos' por cada 'true'
        for (Map.Entry<LocalDate, Boolean> fecha : dias.entrySet()) {
            if (fecha.getValue()) {
                diasLluviosos++;
            }
        }

        // Retornar numero de dias lluviosos
        return diasLluviosos;
    }

    public int trimestreLluvioso() {
        // Inicializar Map con el trimestre como clave y valores a 0 (los dias lluviosos por defecto)
        Map<Integer, Integer> trimestres = new HashMap<>(Map.ofEntries(
                entry(1, 0),
                entry(2, 0),
                entry(3, 0),
                entry(4, 0)
        ));

        // Recorrer el Map de 'dias' e incrementar en 1 el valor de 'trimestre' en base al trimestre dado
        for (Map.Entry<LocalDate, Boolean> fecha : dias.entrySet()) {
            // Obtener mes
            int mes = fecha.getKey().getMonthValue();

            // TODO: comprobar porque intellij llora
            // Rango de meses
            if (mes >= 1 && mes <= 3) {
                // Primer trimestre: enero-marzo
                trimestres.put(1, trimestres.get(1) + 1);
            } else if (mes >= 4 && mes <= 6) {
                // Segundo trimestre: abril-junio
                trimestres.put(2, trimestres.get(2) + 1);
            } else if (mes >= 7 && mes <= 9) {
                // Tercer trimestre: julio-septiembre
                trimestres.put(3, trimestres.get(3) + 1);
            } else if (mes >= 10 && mes <= 12) {
                // Cuarto trimestre: octubre-diciembre
                trimestres.put(4, trimestres.get(4) + 1);
            }
        }

        // Retornar trimestre con el valor (numero de dias lluviosos) mas alto
        return Collections.max(trimestres.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public int primerDiaLluvia() {
        // Inicializar primer dia lluvioso a 0 (si la funcion retorna 0, es que no ha habido ningun dia lluvioso)
        int primerDia = 0;

        // Ordenar pasandolo a TreeMap
        TreeMap<LocalDate, Boolean> diasOrdenados = new TreeMap<>(dias);

        // Recorrer Map y devolver el primer dia que encuentre con un 'true'
        for (Map.Entry<LocalDate, Boolean> fecha : diasOrdenados.entrySet()) {
            if (fecha.getValue()) {
                // Guardar primer dia lluvioso del anno
                primerDia = fecha.getKey().getDayOfYear();
                break;
            }
        }

        // Retornar primer dia del anno con lluvia
        return primerDia;
    }
}
