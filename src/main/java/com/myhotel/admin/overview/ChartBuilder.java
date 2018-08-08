package com.myhotel.admin.overview;

import java.util.List;

import com.myhotel.domain.Booking;

import javafx.collections.ObservableList;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;

/**
 * 
 * @author Cong Khanh Tran - trancongkhanh@gmail.com
 *
 *
 */
public interface ChartBuilder {
	public CategoryAxis buildXAxis();
	public NumberAxis buildYAxis();
	public ObservableList buildData(List<Booking> bookingList);
}
	