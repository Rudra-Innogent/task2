class Address {
    String city;
    String pincode;

    public Address(String city, String pincode) {
        this.city = city;
        this.pincode = pincode;
    }

    public String toCSV() { return city + "," + pincode; }
    public static Address fromCSV(String line) {
        String[] parts = line.split(",");
        return new Address(parts[0], parts[1]);
    }
    public String toString() { return city + " - " + pincode; }
}