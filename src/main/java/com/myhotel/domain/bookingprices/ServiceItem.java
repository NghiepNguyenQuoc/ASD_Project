package com.myhotel.domain.bookingprices;

public interface ServiceItem {
	void accept(ServiceElementVisitor serviceElementVisitor);
}
