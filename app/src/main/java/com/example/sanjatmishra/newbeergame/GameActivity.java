package com.example.sanjatmishra.newbeergame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class GameActivity extends AppCompatActivity {


    String week,lbound,ubound,stockR,stockW,stockD,stockF, randInc;
    TextView t;
    int weekcnt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameactivity);

        Bundle extras = getIntent().getExtras();

        if(extras!=null)
        {
            week = extras.getString("week");
            lbound = extras.getString("lbound");
            ubound = extras.getString("ubound");
            stockR = extras.getString("cret");
            stockW = extras.getString("cwhole");
            stockD = extras.getString("cdis");
            stockF = extras.getString("cfact");
            randInc = extras.getString("rinc");
        }

        t = (TextView)findViewById(R.id.RetStock);
        t.setText(stockR.toString());

        t = (TextView)findViewById(R.id.WholeStock);
        t.setText(stockW.toString());

        t = (TextView)findViewById(R.id.DisStock);
        t.setText(stockD.toString());

        t = (TextView)findViewById(R.id.FactStock);
        t.setText(stockF.toString());


    }

    // Retailer

    int retailerBack=0,retailerStock;
    int RDemand=0;

    public void generateDemand( View v ){
        weekcnt++;

        if(weekcnt > Integer.parseInt(week.toString()))
        {
            //V b = new Button();
            View b = findViewById(R.id.GenBt);
            b.setVisibility(View.GONE);

            b = findViewById(R.id.retBt);
            b.setVisibility(View.GONE);

            b = findViewById(R.id.wholeBt);
            b.setVisibility(View.GONE);

            b = findViewById(R.id.DisBt);
            b.setVisibility(View.GONE);

            b = findViewById(R.id.FactBt);
            b.setVisibility(View.GONE);
        }

        Random r = new Random();
        int lb = Integer.parseInt(lbound.toString());
        int ub = Integer.parseInt(ubound.toString());
        int rd = Integer.parseInt(randInc.toString());

        int demand=0;
        if(weekcnt == 1)
        {
            demand = r.nextInt(ub-lb)+lb;
            RDemand = demand;
        }
        else
        {
            RDemand += r.nextInt(rd);
            demand = RDemand;
        }




        t = (TextView)findViewById(R.id.randDemand);
        t.setText(""+demand);

        t = (TextView)findViewById(R.id.weekcount);
        t.setText(""+weekcnt);

        t = (TextView)findViewById(R.id.cusDemand);
        t.setText(""+demand);

        // Reduce from Retailer Stock


        retailerStock = Integer.parseInt(stockR.toString());
        if(demand <= retailerStock){
            retailerStock = retailerStock-demand;
            t = (TextView)findViewById(R.id.RetStock);
            t.setText(""+retailerStock);
            stockR = String.valueOf(retailerStock);
        }
        else{


            retailerBack = retailerBack+  demand - retailerStock;
            retailerStock=0;

            t = (TextView)findViewById(R.id.RetStock);
            t.setText(""+retailerStock);

            stockR = String.valueOf(retailerStock);

            t = (TextView)findViewById(R.id.RetBO);
            t.setText(""+retailerBack);
        }


    }



    int retailD;
    int wholesaleStock,wholesaleBack=0;

    public void wholesalerWork(View v){
        t = findViewById(R.id.retDemand);

        retailD = Integer.parseInt(t.getText().toString());

        t = (TextView)findViewById(R.id.wholeDem);
        t.setText(""+retailD);

        wholesaleStock = Integer.parseInt(stockW.toString());

        if(retailD <= wholesaleStock ){
            wholesaleStock = wholesaleStock-retailD;
            t = (TextView)findViewById(R.id.WholeStock);
            t.setText(""+wholesaleStock);
            stockW = String.valueOf(wholesaleStock);

            t = findViewById(R.id.retSupply);
            t.setText(""+retailD);



        }
        else if(retailD > wholesaleStock){


            wholesaleBack = wholesaleBack+  retailD - wholesaleStock;

            t =findViewById(R.id.retSupply);
            t.setText(""+wholesaleStock);


            wholesaleStock=0;

            t = (TextView)findViewById(R.id.WholeStock);
            t.setText(""+wholesaleStock);

            stockW = String.valueOf(wholesaleStock);

            t = (TextView)findViewById(R.id.WholeBO);
            t.setText(""+wholesaleBack);


        }



    }

    int wholesaleD;
    int distributorStock,distributorBack=0;

    public void distributorwork(View v){
        t = findViewById(R.id.wholeDemand);

        wholesaleD = Integer.parseInt(t.getText().toString());

        t = (TextView)findViewById(R.id.DisDem);
        t.setText(""+wholesaleD);

        distributorStock = Integer.parseInt(stockD.toString());

        if(wholesaleD <= distributorStock ){
            distributorStock = distributorStock-wholesaleD;
            t = (TextView)findViewById(R.id.DisStock);
            t.setText(""+distributorStock);
            stockD = String.valueOf(distributorStock);


            t = findViewById(R.id.wholeSupply);
            t.setText(""+wholesaleD);
        }
        else if(wholesaleD > distributorStock){


            distributorBack = distributorBack+  wholesaleD - distributorStock;

            t =findViewById(R.id.wholeSupply);
            t.setText(""+distributorStock);

            distributorStock=0;

            t = (TextView)findViewById(R.id.DisStock);
            t.setText(""+distributorStock);

            stockD = String.valueOf(distributorStock);

            t = (TextView)findViewById(R.id.DisBO);
            t.setText(""+distributorBack);



        }
    }


    //Factory

    int DistributorD;
    int FactoryStock,FactoryBack=0;

    public void FactoryWork(View v)
    {
        t = findViewById(R.id.DisDemand);

        DistributorD = Integer.parseInt(t.getText().toString());

        t = (TextView)findViewById(R.id.FactDem);
        t.setText(""+DistributorD);

        FactoryStock = Integer.parseInt(stockF.toString());

        if(DistributorD <= FactoryStock ){
            FactoryStock = FactoryStock-DistributorD;
            t = (TextView)findViewById(R.id.FactStock);
            t.setText(""+FactoryStock);
            stockF = String.valueOf(FactoryStock);

            t = findViewById(R.id.DisSupply);
            t.setText(""+DistributorD);

        }
        else if(DistributorD > FactoryStock){


            FactoryBack = FactoryBack+  DistributorD - FactoryStock;

            t =findViewById(R.id.DisSupply);
            t.setText(""+FactoryStock);

            FactoryStock=0;

            t = (TextView)findViewById(R.id.FactStock);
            t.setText(""+FactoryStock);

            stockF = String.valueOf(FactoryStock);

            t = (TextView)findViewById(R.id.FactBO);
            t.setText(""+FactoryBack);

        }
    }


    int forder=0;
    int [] arr = new int[2];

    public void factorydemands(View v){

        // Add to stock for everyone

        t = findViewById(R.id.retSupply);
        retailerStock =  retailerStock + Integer.parseInt(t.getText().toString());
        stockR = String.valueOf(retailerStock);
        t.setText(""+0);

        t = findViewById(R.id.RetStock);
        t.setText(stockR.toString());

        t = findViewById(R.id.wholeSupply);
        wholesaleStock =  wholesaleStock + Integer.parseInt(t.getText().toString());
        stockW = String.valueOf(wholesaleStock);
        t.setText(""+0);

        t = findViewById(R.id.WholeStock);
        t.setText(stockW.toString());

        t = findViewById(R.id.DisSupply);
        distributorStock =  distributorStock + Integer.parseInt(t.getText().toString());
        stockD = String.valueOf(distributorStock);
        t.setText(""+0);

        t = findViewById(R.id.DisStock);
        t.setText(stockD.toString());

        t = findViewById(R.id.FactSupply);
        FactoryStock =  FactoryStock + Integer.parseInt(t.getText().toString());
        stockF = String.valueOf(FactoryStock);
        t.setText(""+0);

        t = findViewById(R.id.FactStock);
        t.setText(stockF.toString());



        t = findViewById(R.id.FactOrder);
        forder = Integer.parseInt(t.getText().toString());
        arr[(weekcnt+1)%2] = forder;

        if(weekcnt> 1 &&((weekcnt+1)%2 != 0)){
            t = findViewById(R.id.FactSupply);
            t.setText(""+arr[weekcnt%2]);
            supplyChain();
        }

        else if (weekcnt>1 && (((weekcnt+1)%2) == 0)){
            t = findViewById(R.id.FactSupply);
            t.setText(""+arr[(weekcnt)%2]);
            supplyChain();
        }

    }

    TextView outSupply;


    public void supplyChain(){

        outSupply = findViewById(R.id.FactSupply);

        if(FactoryBack > 0 && (Integer.parseInt(outSupply.getText().toString())) >= FactoryBack) {
            FactoryStock = Integer.parseInt(outSupply.getText().toString()) - FactoryBack;
            t =findViewById(R.id.FactStock);
            t.setText(""+FactoryStock);

            stockF = String.valueOf(FactoryStock);

            // Distributor

            t = findViewById(R.id.DisSupply);
            t.setText(""+FactoryBack);

            FactoryBack = 0;
            t = findViewById(R.id.FactBO);
            t.setText(""+FactoryBack);
            supplyChainD();

        }

        else if(FactoryBack > 0 && (Integer.parseInt(outSupply.getText().toString())) < FactoryBack){
            FactoryStock = 0;
            t =findViewById(R.id.FactStock);
            t.setText(""+FactoryStock);

            stockF = String.valueOf(FactoryStock);

            // Distributor

            t = findViewById(R.id.DisSupply);
            t.setText(""+Integer.parseInt(outSupply.getText().toString()));

            FactoryBack = FactoryBack - Integer.parseInt(outSupply.getText().toString());
            t = findViewById(R.id.FactBO);
            t.setText(""+FactoryBack);

            supplyChainD();
        }

        else if( FactoryBack == 0){
            FactoryStock = FactoryStock + Integer.parseInt(outSupply.getText().toString());
        }

    }


    public void supplyChainD(){


        outSupply = findViewById(R.id.DisSupply);

        if(distributorBack > 0 && (Integer.parseInt(outSupply.getText().toString())) >= distributorBack) {
            distributorStock = Integer.parseInt(outSupply.getText().toString()) - distributorBack;
            t =findViewById(R.id.DisStock);
            t.setText(""+distributorStock);

            stockD = String.valueOf(distributorStock);

            // Wholesaler

            t = findViewById(R.id.wholeSupply);
            t.setText(""+distributorBack);

            distributorBack = 0;
            t = findViewById(R.id.DisBO);
            t.setText(""+distributorBack);

            supplychainW();


        }

        else if(distributorBack > 0 && (Integer.parseInt(outSupply.getText().toString())) < distributorBack){
            distributorStock = 0;
            t =findViewById(R.id.DisStock);
            t.setText(""+distributorStock);

            stockD = String.valueOf(distributorStock);

            // Wholesaler

            t = findViewById(R.id.wholeSupply);
            t.setText(""+Integer.parseInt(outSupply.getText().toString()));

            distributorBack = distributorBack - Integer.parseInt(outSupply.getText().toString());
            t = findViewById(R.id.DisBO);
            t.setText(""+distributorBack);

            supplychainW();


        }

        else if( distributorBack == 0){
            distributorStock = distributorStock + Integer.parseInt(outSupply.getText().toString());
        }


    }

    public void supplychainW(){


        outSupply = findViewById(R.id.wholeSupply);

        if(wholesaleBack > 0 && (Integer.parseInt(outSupply.getText().toString())) >= wholesaleBack) {
            wholesaleStock = Integer.parseInt(outSupply.getText().toString()) - wholesaleBack;
            t =findViewById(R.id.WholeStock);
            t.setText(""+wholesaleStock);

            stockW = String.valueOf(wholesaleStock);

            // Retailer

            t = findViewById(R.id.retSupply);
            t.setText(""+wholesaleBack);

            wholesaleBack = 0;
            t = findViewById(R.id.WholeBO);
            t.setText(""+wholesaleBack);

            supplychainR();


        }

        else if(wholesaleBack > 0 && (Integer.parseInt(outSupply.getText().toString())) < wholesaleBack){
            wholesaleStock = 0;
            t =findViewById(R.id.WholeStock);
            t.setText(""+wholesaleStock);

            stockW = String.valueOf(wholesaleStock);

            // Retailer

            t = findViewById(R.id.retSupply);
            t.setText(""+Integer.parseInt(outSupply.getText().toString()));

            wholesaleBack = wholesaleBack - Integer.parseInt(outSupply.getText().toString());
            t = findViewById(R.id.WholeBO);
            t.setText(""+wholesaleBack);


            supplychainR();

        }


        else if( wholesaleBack == 0){
            wholesaleStock = wholesaleStock + Integer.parseInt(outSupply.getText().toString());
        }

    }

    public void supplychainR(){


        outSupply = findViewById(R.id.retSupply);

       if(retailerBack > 0 && (Integer.parseInt(outSupply.getText().toString())) >= retailerBack){
           retailerStock = retailerStock + Integer.parseInt(outSupply.getText().toString()) - retailerBack;

           t = findViewById(R.id.RetStock);
           t.setText(""+retailerStock);

           stockR = String.valueOf(retailerStock);

           retailerBack=0;
           t = findViewById(R.id.RetBO);
           t.setText(""+retailerBack);
       }

       else if(retailerBack > 0 && (Integer.parseInt(outSupply.getText().toString())) < retailerBack){

           retailerBack = retailerBack - Integer.parseInt(outSupply.getText().toString());

           t = findViewById(R.id.RetBO);
           t.setText(""+retailerBack);


       }



    }

    int TotBack=0, TotStock=0,weekCost=0;
    TextView t2,t3,t4;

    int [] costvalues = new int[50];
    int [] weekvalues = new int[50];
    int count=0;



    int retCost = 0;
    int wholesaleCost = 0;
    int distributorCost = 0;
    int factoryCost = 0;

    int [] retSend = new int[50];
    int [] wholeSend = new int[50];
    int [] distributorSend = new int[50];
    int [] factorySend = new int[50];


    int [] retSendStocks = new int[50];
    int [] wholeSendStocks = new int[50];
    int [] distributorSendStocks = new int[50];
    int [] factorySendStocks = new int[50];


    int [] retD = new int [50];
    int [] wholeD = new int [50];
    int [] disD = new int [50];
    int [] facD = new int [50];

    int st;

    public void Calweekcost(View v)
    {

        //Inventory Graph Precursor

        t = findViewById(R.id.RetStock);
        t2 = findViewById(R.id.WholeStock);
        t3 = findViewById(R.id.DisStock);
        t4 = findViewById(R.id.FactStock);

        st = Integer.parseInt(t.getText().toString());
        retSendStocks[count] = st;

        st = Integer.parseInt(t2.getText().toString());
        wholeSendStocks[count] = st;

        st = Integer.parseInt(t3.getText().toString());
        distributorSendStocks[count] = st;

        st = Integer.parseInt(t4.getText().toString());
        factorySendStocks[count] = st;



        TotStock = Integer.parseInt(t.getText().toString()) + Integer.parseInt(t2.getText().toString()) + Integer.parseInt(t3.getText().toString()) + Integer.parseInt(t4.getText().toString());

        t = findViewById(R.id.RetBO);
        t2 = findViewById(R.id.WholeBO);
        t3 = findViewById(R.id.DisBO);
        t4 = findViewById(R.id.FactBO);

        TotBack = Integer.parseInt(t.getText().toString()) + Integer.parseInt(t2.getText().toString()) + Integer.parseInt(t3.getText().toString()) + Integer.parseInt(t4.getText().toString());

        weekCost = TotStock +2*(TotBack);


        //Individual Costs

        t = findViewById(R.id.RetStock);
        t2 = findViewById(R.id.WholeStock);
        t3 = findViewById(R.id.DisStock);
        t4 = findViewById(R.id.FactStock);

        retCost = Integer.parseInt(t.getText().toString());
        wholesaleCost = Integer.parseInt(t2.getText().toString());
        distributorCost = Integer.parseInt(t3.getText().toString());
        factoryCost = Integer.parseInt(t4.getText().toString());

        t = findViewById(R.id.RetBO);
        t2 = findViewById(R.id.WholeBO);
        t3 = findViewById(R.id.DisBO);
        t4 = findViewById(R.id.FactBO);

        retCost = retCost + 2*(Integer.parseInt(t.getText().toString()));
        wholesaleCost = wholesaleCost + 2*(Integer.parseInt(t2.getText().toString()));
        distributorCost = distributorCost + 2*(Integer.parseInt(t3.getText().toString()));
        factoryCost = factoryCost + 2*(Integer.parseInt(t4.getText().toString()));


        retSend[count] = retCost;
        wholeSend[count] = wholesaleCost;
        distributorSend[count] = distributorCost;
        factorySend[count] = factoryCost;





        costvalues[count] = weekCost;
        weekvalues[count] = weekcnt;


        // Demand Graph precursor
        t = findViewById(R.id.retDemand);
        t2 = findViewById(R.id.wholeDemand);
        t3 = findViewById(R.id.DisDemand);
        t4 = findViewById(R.id.FactOrder);


        st = Integer.parseInt(t.getText().toString());
        retD[count] = st;

        st = Integer.parseInt(t2.getText().toString());
        wholeD[count] = st;

        st = Integer.parseInt(t3.getText().toString());
        disD[count] = st;

        st = Integer.parseInt(t4.getText().toString());
        facD[count] = st;

        Log.v("check checkk",""+retD[count]);
        Log.v("checkkkkss",""+wholeD[count]);
        Log.v("checkkkkss",""+disD[count]);
        Log.v("checkkkkss",""+facD[count]);



        Log.v("sanjatyyyyy",""+costvalues[count]);
        Log.v("weekkkkkk",""+weekvalues[count]);

        count++;

        t = findViewById(R.id.finCost);
        t.setText(""+weekCost);

    }

    public void plotGraph(View v){
        Intent i = new Intent(GameActivity.this,GraphActivity.class);
        Bundle bg = new Bundle();

        int numweek = Integer.parseInt(week.toString());


        bg.putIntArray("weekarr",weekvalues);




        bg.putIntArray("costarr",costvalues);
        bg.putInt("num",numweek);

        bg.putIntArray("retarr",retSend);
        bg.putIntArray("wholearr",wholeSend);
        bg.putIntArray("disarr",distributorSend);
        bg.putIntArray("factarr",factorySend);

        i.putExtras(bg);

        GameActivity.this.startActivity(i);


    }




    public void plotInventory(View v){
        Intent inv = new Intent(GameActivity.this,InventoryGraphActivity.class);

        Bundle bg = new Bundle();
        int numweek = Integer.parseInt(week.toString());
        bg.putInt("num",numweek);
        bg.putIntArray("weekdata",weekvalues);
        bg.putIntArray("retstock",retSendStocks);
        bg.putIntArray("whstock",wholeSendStocks);
        bg.putIntArray("disstock",distributorSendStocks);
        bg.putIntArray("facstock",factorySendStocks);

        inv.putExtras(bg);

        GameActivity.this.startActivity(inv);

    }




    public void plotDemand(View v){
        Intent dem = new Intent(GameActivity.this,DemandGraphActivity.class);



        Bundle bg = new Bundle();
        int numweek = Integer.parseInt(week.toString());
        bg.putInt("num",numweek);
        bg.putIntArray("weekdata",weekvalues);

        bg.putIntArray("retdem",retD);
        bg.putIntArray("whdem",wholeD);
        bg.putIntArray("disdem",disD);
        bg.putIntArray("facdem",facD);


        dem.putExtras(bg);
        GameActivity.this.startActivity(dem);



    }




}
