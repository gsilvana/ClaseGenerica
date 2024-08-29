package clasegenericidad;

public class Main {
    public static void main(String[] args) {
        
        // Para almacenar edad y nombre
        Pair<Integer, String> persona1 = new Pair<>(20, "Silvana");
        Pair<Integer, String> persona2 = new Pair<>(19, "Carlos");

        System.out.println("Persona 1: " + persona1);
        System.out.println("Persona 2: " + persona2);

        // Para lmacenar EPS y fecha de nacimiento
        Pair<String, String> datosAdicionales1 = new Pair<>("EPS Sura", "2004-09-30");
        Pair<String, String> datosAdicionales2 = new Pair<>("EPS SaludTotal", "2005-09-12");

        System.out.println("Datos Adicionales persona 1: " + datosAdicionales1); 
        System.out.println("Datos Adicionales persona 2: " + datosAdicionales2);
        System.out.println("\n");

        // Logica para almacenar en la DB
        DB db = new DB();
        db.agregarPersona(new Persona(persona1.getSecond(), persona1.getFirst()));
        db.agregarPersona(new Persona(persona2.getSecond(), persona2.getFirst()));

        // Cuando el ID se genera automaticamente
        db.agregarDatosAdicionales(new DatosAdicionales(datosAdicionales1.getFirst(), datosAdicionales1.getSecond()), 1);
        db.agregarDatosAdicionales(new DatosAdicionales(datosAdicionales2.getFirst(), datosAdicionales2.getSecond()), 2);

        // Imprimir la base de datos
        db.imprimirDatos();
    }
}


