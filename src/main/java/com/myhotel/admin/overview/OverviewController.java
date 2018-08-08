package com.myhotel.admin.overview;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;

import com.myhotel.domain.Booking;
import com.myhotel.service.ApplicationContextHolder;
import com.myhotel.service.BookingService;
import com.myhotel.service.impl.BookingServiceImpl;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.layout.AnchorPane;

/**
 * 
 * @author Cong Khanh Tran - trancongkhanh@gmail.com
 *
 *
 */
@Controller
public class OverviewController implements Initializable {
	@FXML
	private AnchorPane anchorPane;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
//		get booking list
		List<Booking> bookingList = loadBookings();
//		create chart by sending to ChartAdapter
		ChartAdapter chartAdapter = new ChartAdapterImpl();
		LineChart lineChart = chartAdapter.createChart(bookingList);
//		add chart to GUI
		anchorPane.getChildren().add(lineChart);
	}

	private List<Booking> loadBookings() {
		List<Booking> bookings = null;
		BookingService bookingService = ApplicationContextHolder.getContext().getBean(BookingServiceImpl.class);
		bookings = bookingService.findAll();
		return bookings;
	}
}
