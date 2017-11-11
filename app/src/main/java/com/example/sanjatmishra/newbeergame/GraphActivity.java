package com.example.sanjatmishra.newbeergame;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.PointsGraphSeries;
import android.util.Log;

public class GraphActivity extends AppCompatActivity {

    int [] cost = new int[50];
    int [] week = new int[50];

    int [] retC = new int[50];
    int [] wholeC = new int[50];
    int [] disC = new int[50];
    int [] factC = new int[50];

    int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);


        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            week = extras.getIntArray("weekarr");
            cost = extras.getIntArray("costarr");
            n = extras.getInt("num");


            retC = extras.getIntArray("retarr");
            wholeC = extras.getIntArray("wholearr");
            disC = extras.getIntArray("disarr");
            factC = extras.getIntArray("factarr");

        }

        // Graph

        Log.v("number week graph",""+n);

        for(int mm=0;mm<n;mm++){
            Log.v("cost arr graph", ""+cost[mm]);
            Log.v("cost arr graph", ""+week[mm]);
        }



        GraphView graph;

        LineGraphSeries<DataPoint> series,seriesRet,seriesW,seriesD,seriesF;       //an Object of the PointsGraphSeries for plotting scatter graphs
        graph =  findViewById(R.id.graph);
        series= new LineGraphSeries<>(data());   //initializing/defining series to get the data from the method 'data()'
        seriesRet = new LineGraphSeries<>(dataRet());
        seriesW = new LineGraphSeries<>(dataW());
        seriesD = new LineGraphSeries<>(dataD());
        seriesF = new LineGraphSeries<>(dataF());


        graph.addSeries(series);                   //adding the series to the GraphView
        series.setColor(Color.BLACK);
        graph.addSeries(seriesRet);
        seriesRet.setColor(Color.BLUE);
        graph.addSeries(seriesW);
        seriesW.setColor(Color.GREEN);
        graph.addSeries(seriesD);
        seriesD.setColor(Color.RED);
        graph.addSeries(seriesF);
        seriesF.setColor(Color.CYAN);



        // legend
        series.setTitle("Total Cost");
        seriesRet.setTitle("Retailer Cost");
        seriesW.setTitle("Wholesaler Cost");
        seriesD.setTitle("Distributor Cost");
        seriesF.setTitle("Factory Cost");
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph.getLegendRenderer().setBackgroundColor(Color.TRANSPARENT);
        graph.getGridLabelRenderer().setHorizontalAxisTitle("WEEKS");
        graph.getGridLabelRenderer().setVerticalAxisTitle("COST");
        graph.getViewport().setScrollableY(true);
        graph.getViewport().setScrollable(true);

        graph.getViewport().setYAxisBoundsManual(true);
        graph.getViewport().setMinY(0);
        graph.getViewport().setMaxY(1000);

        graph.getViewport().setXAxisBoundsManual(true);
        graph.getViewport().setMinX(0);
        graph.getViewport().setMaxX(40);

        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);





    }



    public DataPoint[] data(){
        DataPoint[] values = new DataPoint[n];     //creating an object of type DataPoint[] of size 'n'
        for(int i=0;i<n;i++){
            DataPoint v = new DataPoint(week[i],cost[i]);
            values[i] = v;
            Log.v("Values",""+v);
        }
        return values;
    }

    public DataPoint[] dataRet(){
        DataPoint[] values = new DataPoint[n];     //creating an object of type DataPoint[] of size 'n'
        for(int i=0;i<n;i++){
            DataPoint v = new DataPoint(week[i],retC[i]);
            values[i] = v;
            Log.v("Values",""+v);
        }
        return values;
    }

    public DataPoint[] dataW(){
        DataPoint[] values = new DataPoint[n];     //creating an object of type DataPoint[] of size 'n'
        for(int i=0;i<n;i++){
            DataPoint v = new DataPoint(week[i],wholeC[i]);
            values[i] = v;
            Log.v("Values",""+v);
        }
        return values;
    }

    public DataPoint[] dataD(){
        DataPoint[] values = new DataPoint[n];     //creating an object of type DataPoint[] of size 'n'
        for(int i=0;i<n;i++){
            DataPoint v = new DataPoint(week[i],disC[i]);
            values[i] = v;
            Log.v("Values",""+v);
        }
        return values;
    }

    public DataPoint[] dataF(){
        DataPoint[] values = new DataPoint[n];     //creating an object of type DataPoint[] of size 'n'
        for(int i=0;i<n;i++){
            DataPoint v = new DataPoint(week[i],factC[i]);
            values[i] = v;
            Log.v("Values",""+v);
        }
        return values;
    }



}
