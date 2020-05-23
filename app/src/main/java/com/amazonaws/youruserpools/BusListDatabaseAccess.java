package com.amazonaws.youruserpools;


import android.content.Context;
import android.util.Log;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.document.Table;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Primitive;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;


import java.util.List;



import static android.content.ContentValues.TAG;

public class BusListDatabaseAccess {
    private static final String COGNITO_POOL_ID = "ap-south-1:5806a377-205c-45fd-90f7-0d10f8177b1f";
    private static final Regions MY_REGION = Regions.AP_SOUTH_1;
    private AmazonDynamoDBClient dbClient;
    private Table dbTable;
    private Context context;
    private final String DYNAMODB_TABLE = "PLAN__JOURNEY";
    CognitoCachingCredentialsProvider credentialsProvider;



    private static volatile BusListDatabaseAccess instance;
    private BusListDatabaseAccess (Context context) {
        this.context =context;
        credentialsProvider = new CognitoCachingCredentialsProvider (context, COGNITO_POOL_ID, MY_REGION);
        dbClient = new AmazonDynamoDBClient(credentialsProvider);
        dbClient.setRegion(Region.getRegion(Regions.AP_SOUTH_1));
        dbTable = Table.loadTable(dbClient, DYNAMODB_TABLE);


    }
    public static synchronized BusListDatabaseAccess getInstance   (Context context) {
        if (instance == null) {
            instance = new BusListDatabaseAccess(context);
        }
        return instance;
    }

    public Document getItem (String source,String destination){
        //String hashKey= credentialsProvider.getCachedIdentityId();
        //Primitive range = new Primitive(destination);
        //Log.d("hashkey is:",hashKey + " " + range);
        Document result = dbTable.getItem(new Primitive(source) , new Primitive(destination));
        return result;
    }
    public List<Document> getAllItems() {

        return dbTable.query(new Primitive("source1")).getAllResults();

    }

}