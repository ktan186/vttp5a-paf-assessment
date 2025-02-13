package vttp.batch5.paf.movies.repositories;

import java.util.Collection;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.stereotype.Repository;

@Repository
public class MongoMovieRepository {

    @Autowired
    private MongoTemplate template;

 // TODO: Task 2.3
 // You can add any number of parameters and return any type from the method
 // You can throw any checked exceptions from the method
 // Write the native Mongo query you implement in the method in the comments
 //
 //    native MongoDB query here
 //
 public <T> T batchInsertMovies(T doc, String colName) {
    return template.insert(doc, colName);
 }
 public Object insertMovies(List<Document> docs, String colName) {
    var result = template.insert(docs, colName);
    return result;
 }
 

 // TODO: Task 2.4
 // You can add any number of parameters and return any type from the method
 // You can throw any checked exceptions from the method
 // Write the native Mongo query you implement in the method in the comments
 //
 //    native MongoDB query here
 //
 public <T> T logError(T doc, String colName) {
    return template.insert(doc, colName);
 }

 // TODO: Task 3
 // Write the native Mongo query you implement in the method in the comments
 //
 //    native MongoDB query here
 //
        // db.imdb.aggregate([
        //     {$group: {
        //         _id: "$directors",
        //         count: {$sum: 1},
        //         imdb_ids: {$push: "$_id"}
        //     }},
        //     {$sort: {count: -1}}    
        // ])

    public List<Document> getMongoDir() {
        GroupOperation groupByDirector = Aggregation.group("directors")
            .push("_id").as("imdb_ids")
            .count().as("count");
        
        SortOperation sort = Aggregation.sort(Sort.Direction.DESC,"count");

        Aggregation pipeline = Aggregation.newAggregation(groupByDirector, sort);
        List<Document> results = template.aggregate(pipeline, "imdb", Document.class).getMappedResults();

        System.out.println(results);
        return results;

    }
 

}
