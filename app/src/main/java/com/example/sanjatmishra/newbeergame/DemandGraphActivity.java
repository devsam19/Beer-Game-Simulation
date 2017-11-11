package com.example.sanjatmishra.newbeergame;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class DemandGraphActivity extends AppCompatActivity {


    int [] rDemand = new int[50];
    int [] wDemand = new int[50];
    int [] dDemand = new int[50];
    int [] fDemand = new int[50];
    int [] weeks = new int[50];

    int n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demand_graph);



        Bundle extras = getIntent().getExtras();
        if(extras!=null){

            n = extras.getInt("num");

            weeks = extras.getIntArray("weekdata");
            rDemand = extras.getIntArray("retdem");
            wDemand = extras.getIntArray("whdem");
            dDemand = extras.getIntArray("disdem");
            fDemand = extras.getIntArray("facdem");

        }


        GraphView graph;

        LineGraphSeries<DataPoint> seriesRet,seriesW,seriesD,seriesF;       //an Object of the PointsGraphSeries for plotting scatter graphs
        graph =  findViewById(R.id.demandzgraph);
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

        seriesRet.setTitle("Retailer Demand");
        seriesW.setTitle("Wholesaler Demand");
        seriesD.setTitle("Distributor Demand");
        seriesF.setTitle("Factory Demand");
        graph.getLegendRenderer().setVisible(true);
        graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);
        graph.getLegendRenderer().setBackgroundColor(Color.TRANSPARENT);
        graph.getGridLabelRenderer().setHorizontalAxisTitle("WEEKS");
        graph.getGridLabelRenderer().setVerticalAxisTitle("DEMAND");

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



    public DataPoint[] dataRet(){
        DataPoint[] values = new DataPoint[n];     //creating an object of type DataPoint[] of size 'n'
        for(int i=0;i<n;i++){
            DataPoint v = new DataPoint(weeks[i],rDemand[i]);
            values[i] = v;
            Log.v("Valuesssszzz",""+v);
        }
        return values;
    }

    public DataPoint[] dataW(){
        DataPoint[] values = new DataPoint[n];     //creating an object of type DataPoint[] of size 'n'
        for(int i=0;i<n;i++){
            DataPoint v = new DataPoint(weeks[i],wDemand[i]);
            values[i] = v;
            Log.v("Valuesssszzz",""+v);
        }
        return values;
    }

    public DataPoint[] dataD(){
        DataPoint[] values = new DataPoint[n];     //creating an object of type DataPoint[] of size 'n'
        for(int i=0;i<n;i++){
            DataPoint v = new DataPoint(weeks[i],dDemand[i]);
            values[i] = v;
            Log.v("Valuessszzz",""+v);
        }
        return values;
    }

    public DataPoint[] dataF(){
        DataPoint[] values = new DataPoint[n];     //creating an object of type DataPoint[] of size 'n'
        for(int i=0;i<n;i++){
            DataPoint v = new DataPoint(weeks[i],fDemand[i]);
            values[i] = v;
            Log.v("Valuesssszz",""+v);
        }
        return values;
    }




}
