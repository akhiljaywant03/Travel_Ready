package com.amazonaws.youruserpools;


import android.content.Context;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.dynamodbv2.document.Table;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Document;
import com.amazonaws.mobileconnectors.dynamodbv2.document.datatype.Primitive;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;


import java.util.List;

public class DatabaseAccess {
    private static final String COGNITO_POOL_ID = "ap-south-1:8fe24f45-9098-4be2-a048-8d5d96d58649";
    private static final Regions MY_REGION = Regions.AP_SOUTH_1;
    private AmazonDynamoDBClient dbClient;
    private Table dbTable_PJ;
    private Table dbTable_Vehicle;
    private Table dbTable_rfid_details;
    private Table dbTable_route;

    private Context context;
    private final String DDB_PJ = "PLAN__JOURNEY";
    private final String DDB_V = "VEHICLE";
    private final String DDB_R = "RFID_BALANCE_TABLE";
    private final String DDB_RT = "ROUTE";

    CognitoCachingCredentialsProvider credentialsProvider;



    private static volatile DatabaseAccess instance;
    private DatabaseAccess(Context context) {
        this.context =context;
        credentialsProvider = new CognitoCachingCredentialsProvider (context, COGNITO_POOL_ID, MY_REGION);
        dbClient = new AmazonDynamoDBClient(credentialsProvider);
        dbClient.setRegion(Region.getRegion(Regions.AP_SOUTH_1));
        dbTable_PJ = Table.loadTable(dbClient, DDB_PJ);
        dbTable_Vehicle = Table.loadTable(dbClient, DDB_V);
        dbTable_rfid_details=Table.loadTable(dbClient,DDB_R);
        dbTable_route=Table.loadTable(dbClient,DDB_RT);


    }
    public static synchronized DatabaseAccess getInstance   (Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    //*****************************************************DDB_PJ


    public Document getItem_from_PJ(String source, String destination){
        //String hashKey= credentialsProvider.getCachedIdentityId();
        //Primitive range = new Primitive(destination);
        //Log.d("hashkey is:",hashKey + " " + range);
        Document result = dbTable_PJ.getItem(new Primitive(source) , new Primitive(destination));
        return result;
    }
    public List<Document> getAllItems_from_PJ() {

        return dbTable_PJ.query(new Primitive("source1")).getAllResults();

    }
    //*****************************************************DDB_PJ

    //*****************************************************DDB_V
    public Document getItem_from_V(String VehicleID){
        Document result = dbTable_Vehicle.getItem(new Primitive(VehicleID));
        return result;
    }
    public List<Document> getAllItems_from_V() {

        return dbTable_Vehicle.query(new Primitive("vh01")).getAllResults();

    }
    //*****************************************************DDB_V

    //*****************************************************DDB_R
    public Document getItem_from_R(String RFID){
        Document result = dbTable_rfid_details.getItem(new Primitive(RFID));
        return result;
    }



    //*****************************************************DDB_R


    //*****************************************************DDB_RT
    public Document getItem_from_RT(String RouteID){
        Document result = dbTable_route.getItem(new Primitive(RouteID));
        return result;
    }
    public List<Document> getAllItems_from_RT(String RouteID) {

        return dbTable_route.query(new Primitive(RouteID)).getAllResults();

    }

    //*****************************************************DDB_RT




}