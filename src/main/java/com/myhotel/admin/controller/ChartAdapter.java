package com.myhotel.admin.controller;

import java.util.List;

import com.myhotel.domain.Booking;

import javafx.scene.chart.LineChart;

public interface ChartAdapter {

	LineChart createChart(List<Booking> bookingList);

}
