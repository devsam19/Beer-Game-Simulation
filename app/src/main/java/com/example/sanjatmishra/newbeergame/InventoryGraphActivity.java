package com.example.sanjatmishra.newbeergame;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class InventoryGraphActivity extends AppCompatActivity {


    int [] rStock = new int[50];
    int [] wStock = new int[50];
    int [] dStock = new int[50];
    int [] fStock = new int[50];
    int [] weeks = new int[50];

    int n;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory_graph);


        Bundle extras = getIntent().getExtras();
        if(extras!=null){

            n = extras.getInt("num");

            weeks = extras.getIntArray("weekdata");
            rStock = extras.getIntArray("retstock");
            wStock = extras.getIntArray("whstock");
            dStock = extras.getIntArray("disstock");
            fStock = extras.getIntArray("facstock");

        }


        GraphView graph;

        LineGraphSeries<DataPoint> seriesRet,seriesW,seriesD,seriesF;       //an Object of the PointsGraphSeries for plotting scatter graphs
        graph =  findViewById(R.id.invgraph);
                                                                          //initializing/defining series to get the data from the method 'data()'
        seriesRet = new LineGraphSeries<>(dataRet());
        seriesW = new LineGraphSeries<>(dataW());
        seriesD = new LineGraphSeries<>(dataD());
        seriesF = new LineGraphSeries<>(dataF());

        graph.addSeries(seriesRet);
        seriesRet.setColor(Color.BLUE);
        graph.addSeries(seriesW);
        seriesW.setColor(Color.GREEN);
        graph.addSeries(seriesD);
        seriesD.setColor(Color.RED);
        graph.addSeries(seriesF);
        seriesF.setColor(Color.CYAN);



        // legend

        seriesRet.setTitle("Retailer Stock");
        seriesW.setTitle("Wholesaler Stock");
        seriesD.setTitle("Distributor Stock");
        seriesF.setTitle("Factory Stock");
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph.getLegendRenderer().setBackgroundColor(Color.TRANSPARENT);
        graph.getGridLabelRenderer().setHorizontalAxisTitle("WEEKS");
        graph.getGridLabelRenderer().setVerticalAxisTitle("STOCK");


        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(1000);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(40);

        graph.getViewport().setScrollableY(true);
        graph.getViewport().setScrollable(true);
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);



    }





    public DataPoint[] dataRet(){
        DataPoint[] values = new DataPoint[n];     //creating an object of type DataPoint[] of size 'n'
        for(int i=0;i<n;i++){
            DataPoint v = new DataPoint(weeks[i],rStock[i]);
            values[i] = v;
            Log.v("Values",""+v);
        }
        return values;
    }

    public DataPoint[] dataW(){
        DataPoint[] values = new DataPoint[n];     //creating an object of type DataPoint[] of size 'n'
        for(int i=0;i<n;i++){
            DataPoint v = new DataPoint(weeks[i],wStock[i]);
            values[i] = v;
            Log.v("Values",""+v);
        }
        return values;
    }

    public DataPoint[] dataD(){
        DataPoint[] values = new DataPoint[n];     //creating an object of type DataPoint[] of size 'n'
        for(int i=0;i<n;i++){
            DataPoint v = new DataPoint(weeks[i],dStock[i]);
            values[i] = v;
            Log.v("Values",""+v);
        }
        return values;
    }

    public DataPoint[] dataF(){
        DataPoint[] values = new DataPoint[n];     //creating an object of type DataPoint[] of size 'n'
        for(int i=0;i<n;i++){
            DataPoint v = new DataPoint(weeks[i],fStock[i]);
            values[i] = v;
            Log.v("Values",""+v);
        }
        return values;
    }



}
