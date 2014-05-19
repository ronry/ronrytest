package com.ronrytest.mongo;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoException;
import com.ronrytest.mongo.util.Converter;

public class MongoDbTest {

	/**
	 * @param args
	 * @throws MongoException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws Exception {
		
		Mongo db=new Mongo("localhost", 27017);
		
		DB testDb=db.getDB("test");
		
		DBCollection ronryCollection=testDb.getCollection("person");
		
//		ronryCollection.setObjectClass(Person2.class);
		
//		Person2 person=(Person2)ronryCollection.findOne();
		
//		BasicDBObject queryPerson=new BasicDBObject();
//		queryPerson.put("name", "ronry2");
//		
//		BasicDBObject newPerson=new BasicDBObject();
//		newPerson.put("name", "ronry");
//		newPerson.put("age", 28);
//		
//		BasicDBObject address=new BasicDBObject();
//		address.put("provience", "zhejiang");
//		address.put("city", "hangzhou");
//		newPerson.put("address", address);
//		
//		ronryCollection.insert(newPerson);
//		
//		ronryCollection.update(queryPerson, newPerson);
		
		DBObject  resut=ronryCollection.findOne();
//		
		Person3 person=Converter.toObject(Person3.class, resut);
		System.out.println(person.getAddress().getProvience());
		
		System.out.println("success!!");
	}

}
