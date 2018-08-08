package com.myhotel.admin.overview;

import java.util.List;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import com.myhotel.domain.Booking;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.util.StringConverter;
/**
 * 
 * @author Cong Khanh Tran - trancongkhanh@gmail.com
 *
 *
 */
public class ChartAdapterImpl implements ChartAdapter {

	@Override
	public LineChart createChart(List<Booking> bookingList) {
		ChartDirector director = new ChartDirector();
		LineChart lineChart = director.constructLineChart(bookingList);	
		return lineChart;
	}
}
