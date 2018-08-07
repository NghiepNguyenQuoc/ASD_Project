package com.myhotel.admin.controller;

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
		CategoryAxis xAxis = new CategoryAxis();

		NumberAxis yAxis = new NumberAxis();
		yAxis.setTickLabelFormatter(new StringConverter<Number>() {
			@Override
			public String toString(Number object) {
				NumberFormat formatter = NumberFormat.getCurrencyInstance();
				String moneyString = formatter.format(object);
				return moneyString;
			}

			@Override
			public Number fromString(String string) {
				NumberFormat formatter = NumberFormat.getCurrencyInstance();
				try {
					return formatter.parse(string);
				} catch (ParseException e) {
					e.printStackTrace();
					return 0;
				}
			}
		});
		LineChart lineChart = new LineChart(xAxis, yAxis);
		lineChart.setData(getChartData(bookingList));
		lineChart.setTitle("Monthly Revenue");
		return lineChart;
	}

	private ObservableList getChartData(List<Booking> bookingList) {
		double aValue = 10;
		double cValue = 20;
		ObservableList<XYChart.Series<String, Double>> answer = FXCollections.observableArrayList();
		Series<String, Double> aSeries = new Series<String, Double>();
		aSeries.setName("Revenue");
		Map<String, Double> data = calculateMonthlyRevenue(bookingList);
		Iterator<String> it = data.keySet().iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			double value = data.get(key);
			aSeries.getData().add(new XYChart.Data(key, value));
		}
		answer.addAll(aSeries);
		return answer;
	}

	private Map<String, Double> calculateMonthlyRevenue(List<Booking> bookingList) {
		Map<String, Double> monthlyData = new TreeMap<>();
		Calendar c = Calendar.getInstance();
		for (Booking b : bookingList) {
			c.setTime(b.getEndDate());
			String strYearMonth = c.get(Calendar.YEAR) + "-" + String.format("%02d", c.get(Calendar.MONTH) + 1);
			double total = 0;
			if (monthlyData.containsKey(strYearMonth)) {
				total = monthlyData.get(strYearMonth);
			}
			if (b.getPayment() != null) {
				total += b.getPayment().getAmount();
			}
			monthlyData.put(strYearMonth, total);
		}
		return monthlyData;
	}
}
