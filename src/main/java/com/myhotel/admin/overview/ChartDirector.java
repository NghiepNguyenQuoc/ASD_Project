package com.myhotel.admin.overview;

import java.util.List;

import com.myhotel.domain.Booking;

import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

/**
 * 
 * @author Cong Khanh Tran - trancongkhanh@gmail.com
 *
 *
 */
public class ChartDirector {
	private ChartBuilder chartBuilder;

	public ChartDirector() {
		chartBuilder = new ChartBuilderImpl();
	}
	
	public LineChart constructLineChart(List<Booking> bookingList) {
		
		CategoryAxis xAxis = chartBuilder.buildXAxis();
		NumberAxis yAxis = chartBuilder.buildYAxis();
		LineChart lineChart = new LineChart(xAxis, yAxis);
		
		ObservableList data = chartBuilder.buildData(bookingList);
		lineChart.setData(data);
		
		lineChart.setTitle("Monthly Revenue");
		return lineChart;
	}
	
}
