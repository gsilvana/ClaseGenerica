package clasegenericidad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
    private Connection connection;

    public DB() {
        try {
            String url = "jdbc:mysql://localhost:3306/usuarios";
            String user = "silvana";
            String password = "";

            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    public void agregarPersona(Persona persona) {
        
        String sql = "INSERT INTO personas (Nombre, Edad) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, persona.getNombre());
            statement.setInt(2, persona.getEdad());
            statement.executeUpdate();


            // Para btener el ID generado
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int personaId = generatedKeys.getInt(1);
                    System.out.println("Persona guardardada correctamente con ID " + personaId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void agregarDatosAdicionales(DatosAdicionales datosAdicionales, int personaId) {
        String sql = "INSERT INTO `datos adicionales` (Eps, Fecha, persona_id) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, datosAdicionales.getEps());
            statement.setString(2, datosAdicionales.getFecha());
            statement.setInt(3, personaId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void imprimirDatos() {
        String sqlPersonas = "SELECT * FROM personas";
        String sqlDatos = "SELECT * FROM `datos adicionales`";

        try (PreparedStatement statementPersonas = connection.prepareStatement(sqlPersonas);
             PreparedStatement statementDatos = connection.prepareStatement(sqlDatos);
             ResultSet rsPersonas = statementPersonas.executeQuery();
             ResultSet rsDatos = statementDatos.executeQuery()) {

            System.out.println("\nPersonas registradas en base de datos:");
            while (rsPersonas.next()) {
                System.out.println("ID: " + rsPersonas.getInt("id") + ", Nombre: " + rsPersonas.getString("Nombre") + ", Edad: " + rsPersonas.getInt("Edad"));
            }

            System.out.println("\nDatos Adicionales registrados en base de datos:");
            while (rsDatos.next()) {
                System.out.println("ID: " + rsDatos.getInt("id") + ", EPS: " + rsDatos.getString("Eps") + ", Fecha: " + rsDatos.getDate("Fecha") + ", Persona ID: " + rsDatos.getInt("persona_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


