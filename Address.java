class Address 
{
    private int addressId;
    private int pincode;
    private String city;
    private int stdId;
	
    public Address(int addressId, int pincode, String city, int stdId) 
	{
        this.addressId = addressId;
        this.pincode = pincode;
        this.city = city;
        this.stdId = stdId;
    }
	
    public int getAddressId() 
	{
		return addressId; 
	}
	
    public int getPincode() 
	{
		return pincode; 
	}
	
    public String getCity() 
	{
		return city; 
	}
	
    public int getStdId() 
	{
		return stdId; 
	}
   
    public String toString() 
	{
        return "Address => [ addressId= " + addressId + "\t stdId= " + stdId + "\t city= " + city + "\t pincode= " + pincode + " ]";
    }
}