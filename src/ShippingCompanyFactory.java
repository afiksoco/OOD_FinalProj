
public class ShippingCompanyFactory {

	public static ShippingCompany createShippingCompany(ShippingCompanyName company,String contact, String phoneNumber ) {
		if(company == ShippingCompanyName.DHL)
			return new DHL(contact,phoneNumber);
		if(company == ShippingCompanyName.FedEx)
			return new FedEx(contact,phoneNumber);
		else throw new IllegalArgumentException();

	}
}
