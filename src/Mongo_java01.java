import com.mongodb.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Prueba para realizar conexi�n con MongoDB.
 * @author j
 *
 */
public class Mongo_java01 {
    /**
     * Main del proyecto.
     * @param args
     */
//METODO PRINCIPAL
    public static void main(String[] args) {
        System.out.println("Prueba conexión MongoDB");
        MongoClient mongo = connectionDDBB();
 
        if (mongo != null) {
            System.out.println("CONSULTAS: ");
            printDatabases(mongo);
        } else {
            System.out.println("Error: Conexión no establecida");
        }
    }
 
    /**
     * Clase para crear una conexion a MongoDB.
     * @return MongoClient conexion
     */

//ESTABLECER CONEXION
    private static MongoClient connectionDDBB() {
        MongoClient mongo = null;
        try {
            mongo = new MongoClient("localhost", 27017);
            System.out.println("Conexión establecida.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mongo;
    }

    private static void printDatabases(MongoClient mongo) {
    	DB db = mongo.getDB("restaurantes");
        DBCollection collection = db.getCollection("restaurantes");

       /* System.out.println("------1-------");
        consultOne(collection);

        System.out.println("\n------2-------");
        consultTwo(collection);

        System.out.println("\n------3-------");
        consultThree(collection);*/

        System.out.println("\n------4-------");
        consultFour(collection);

        /*System.out.println("\n------5-------");
        consultFive(collection);*/

        System.out.println("\n------6-------");
        consultSix(collection);

        System.out.println("\n------7-------");
        consultSeven(collection);

    }

//CONSULTAS
    private static void consultOne(DBCollection collection){
        BasicDBObject fields = new BasicDBObject();
        fields.put("restaurant_id", 1);
        fields.put("name", 1);
        fields.put("borough", 1);
        fields.put("cuisine", 1);

        DBCursor cursor = collection.find(null,fields);

        while(cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    private static void consultTwo(DBCollection collection){
        BasicDBObject conditions = new BasicDBObject();
        conditions.put("borough", "Bronx");

        BasicDBObject fields = new BasicDBObject();
        fields.put("name", 1);
        fields.put("borough", 1);

        DBCursor cursor = collection.find(conditions, fields);
        while(cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    private static void consultThree(DBCollection collection){
        BasicDBObject condition = new BasicDBObject();
        condition.put("grades.score", new BasicDBObject("$gte", "80").append("$lte", "100"));

        DBCursor cursor = collection.find(condition);
        while(cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    private static void consultFour(DBCollection collection) {}
    
    private static void consultFive(DBCollection collection) {
        BasicDBObject fields = new BasicDBObject();
        fields.put("restaurant_id", 1);
        fields.put("name", 1);
        fields.put("borough", 1);
        fields.put("cuisine", 1);

        BasicDBObject regexQuery = new BasicDBObject();
        regexQuery.put("name",
               new BasicDBObject("$regex", "^Wil"));

        DBCursor cursor = collection.find(regexQuery,fields);
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    private static void consultSix(DBCollection collection) {}

    private static void consultSeven(DBCollection collection) {}
    
    

    
    }
