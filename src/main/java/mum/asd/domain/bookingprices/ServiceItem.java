package mum.asd.domain.bookingprices;

/**
 * @author vynguyen
 *
 */
public interface ServiceItem {
	void accept(ServiceElementVisitor serviceElementVisitor);
}
