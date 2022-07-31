import com.arangodb.*;
import com.arangodb.entity.BaseDocument;
import com.arangodb.mapping.ArangoJack;

public class App {
    public static void main(String[] args) {
        // Create Database
        ArangoDB arangoDB = new ArangoDB.Builder()
                .serializer(new ArangoJack())
                .password("ke7p5zE1G89pKPO4")
                .build();

        String dbName = "mydb";

        ArangoDatabase db = arangoDB.db(DbName.of(dbName));

        db.create();

        // Create Collection
        String collectionName = "firstCollection";
        ArangoCollection collection = db.collection(collectionName);
        System.out.println("Creating collection...");
        collection.create();

        // Create Document
        String key = "myKey";
        BaseDocument doc = new BaseDocument(key);
        doc.addAttribute("a", "Foo");
        doc.addAttribute("b", 42);
        System.out.println("Inserting document...");
        collection.insertDocument(doc);

        // Read Document
        System.out.println("Reading document...");
        BaseDocument readDocument = collection.getDocument(key, BaseDocument.class);
        System.out.println("Key: " + readDocument.getKey());
        System.out.println("Attribute a: " + readDocument.getAttribute("a"));
        System.out.println("Attribute b: " + readDocument.getAttribute("b"));

        // Update Document
        doc.addAttribute("c", "Bar");
        System.out.println("Updating document ...");
        collection.updateDocument(key, doc);

        // Read updated Document
        System.out.println("Reading updated document ...");
        BaseDocument updatedDocument = collection.getDocument(key, BaseDocument.class);
        System.out.println("Key: " + updatedDocument.getKey());
        System.out.println("Attribute a: " + updatedDocument.getAttribute("a"));
        System.out.println("Attribute b: " + updatedDocument.getAttribute("b"));
        System.out.println("Attribute c: " + updatedDocument.getAttribute("c"));
    }
}
