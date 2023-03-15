import com.mongodb.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Prueba para realizar conexi�n con MongoDB.
 * @author j
 *
 */
public class Mongo_Example01 {
    /**
     * Main del proyecto.
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Prueba conexión MongoDB");
        MongoClient mongo = crearConexion();
 
        if (mongo != null) {
            System.out.println("Lista de bases de datos: ");
            printDatabases(mongo);
 
        } else {
            System.out.println("Error: Conexión no establecida");
        }
    }
 
    /**
     * Clase para crear una conexi�n a MongoDB.
     * @return MongoClient conexi�n
     */
    private static MongoClient crearConexion() {
        MongoClient mongo = null;
        try {
            mongo = new MongoClient("localhost", 27017);
            System.out.println("Conexion establecida");
        } catch (Exception e) {
            e.printStackTrace();
        }
 
        return mongo;
    }

    private static void printDatabases(MongoClient mongo) {
    	DB db = mongo.getDB("restaurantes");
    	 DBCollection collection = db.getCollection("restaurantes");
    	 //selectAllRecordsFromACollection(collection);
        //System.out.println("------1-------");
        //selectFirstRecordInCollection(collection);
        //System.out.println("------2-------");
        //selectAllRecordByRecordNumber(collection);
        //System.out.println("------3-------");
        //andLogicalComparison_Example(collection);
        //System.out.println("------4-------");
        lessThan_GreaterThan_Example(collection);
        //System.out.println("------5-------");
        //expresionRegular(collection);
    }
    
    private static void selectFirstRecordInCollection(DBCollection collection) 
    {
        DBObject dbObject = collection.findOne();
        System.out.println(dbObject);
    }
    
    private static void selectAllRecordsFromACollection(DBCollection collection) //devuelve todos los registros
    {
        DBCursor cursor = collection.find();
        while(cursor.hasNext())
        {
            System.out.println(cursor.next());
        }
    }
    
    private static void selectAllRecordByRecordNumber(DBCollection collection)
    {
        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("address.building", "6409");
        BasicDBObject fields = new BasicDBObject();
        fields.put("borough", 1);
        DBCursor cursor = collection.find(whereQuery, fields);
        while(cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }
    
    private static void lessThan_GreaterThan_Example(DBCollection collection) 
    {
        BasicDBObject getQuery = new BasicDBObject();
        getQuery.put("address.zipcode", new BasicDBObject("$gte", "11000").append("$lte", "11002"));
        DBCursor cursor = collection.find(getQuery);
        while(cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }
    
    
    private static void andLogicalComparison_Example(DBCollection collection){
        List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
        obj.add(new BasicDBObject("borough", "Bronx"));
        obj.add(new BasicDBObject("cuisine", "American "));

        BasicDBObject andQuery = new BasicDBObject();
        andQuery.put("$and", obj);

        DBCursor cursor = collection.find(andQuery);
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }
    
    
    private static void expresionRegular(DBCollection collection) 
    {
        BasicDBObject andQuery = new BasicDBObject();
        List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
        BasicDBObject regexQuery = new BasicDBObject();
        regexQuery.put("name",
               new BasicDBObject("$regex", "^A|^B"));// --> "^A"==/^A/
        DBCursor cursor = collection.find(regexQuery);
        while (cursor.hasNext()) {
            System.out.println(cursor.next());
        }
    }

    }
