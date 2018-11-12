package databases;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Databases 
{

    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        Connector connector;
        
        try {
            char option;
            do {
                clearScreen();
                System.out.println("Selecciona el tipo de servidor de Base de datos:\n"
                        + "1) MariaDB\n2) PostgreSQL\ns) Salir");

                option = (char) System.in.read();
                scanner.nextLine();
                
                switch(option)
                {
                    case '1': case '2':
                        clearScreen();
                        String host = "", database = "", user = "", password = "", port = "";
                        boolean isCorrect = false;
                        
                        while(!isCorrect)
                        {
                            System.out.print("Ingresa el host: ");
                            host = scanner.nextLine();
                                
                            System.out.print("Ingresa la base de datos (ENTER para default): ");
                            database = scanner.nextLine();
                            
                            System.out.print("Ingresa el usuario: ");
                            user = scanner.nextLine();
                            
                            System.out.print("Ingresa la contraseña: ");
                            password = scanner.nextLine();
                            
                            System.out.print("Ingresa el puerto (Enter para default): ");
                            port = scanner.nextLine();
                            
                            if(host.length() > 0 || user.length() > 0 || password.length() > 0)
                            {
                                isCorrect = true;
                                if(port.length() == 0)
                                    port = option == '1' ? "3306" : "5432";
                            }
                            else
                                System.out.println("Valores incorrectos, intenta nuevamente");
                        }
                        /*DELETECODE*/
                        System.out.println("User: " + user);
                        System.out.println("host: " + host);
                        System.out.println("password: " + password);
                        System.out.println("database: " + database);
                        System.out.println("port: " + port);
                        
                        connector = option == '1' ? new MariaDB(host, database, user, password, port) : null;
                        
                        clearScreen();
                        connector.openConnection();
                        ArrayList<String> databases = connector.getDatabases();
                        
                        System.out.println("Selecciona una base de datos:");
                        
                        for (int i = 0; i < databases.size(); i++) 
                        {
                            System.out.println(i + ") " + databases.get(i));
                        }
                        
                        int databaseOption;
                        while(true)
                        {
                            databaseOption = System.in.read() - 1;
                            if(databaseOption >= 0 && databaseOption < databases.size())
                                System.out.println("Base de datos incorrecta, intenta con otra");
                            else 
                                break;
                        }
                        
                    default:
                        System.out.println(option + " no es una opción válida");
                        continue;
                }
            } while(option != 's');
        } catch (IOException ex) {
            Logger.getLogger(Databases.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Databases.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Databases.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void clearScreen() 
    {
        try {
            Runtime.getRuntime().exec("clear");
        } catch (IOException ex) {
            Logger.getLogger(Databases.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public static void maria()
    {
        MariaDB mariaConnector = new MariaDB("localhost", "test", "repoIo", "repoIoPass", "3306");
        try {
            mariaConnector.openConnection();

            ArrayList<String> databases = mariaConnector.getDatabases();

            databases.size();

            mariaConnector.close();
            Scanner keyboard = new Scanner(System.in);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Databases.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Databases.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
