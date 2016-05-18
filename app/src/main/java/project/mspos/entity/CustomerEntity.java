package project.mspos.entity;

public class CustomerEntity {
    private int id;
    private String name;
    private int groupId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String ZipId;
    private String Country;
    private String provice;
    private String company;
    private String fax;
    private String typeCustomer;
    private String VATId;

    public CustomerEntity(String firstName, String lastName, String phone, String email, String addressLine1, String addressLine2, String city, String zipId, String country, String provice, String company, String fax, String typeCustomer, String VATId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        ZipId = zipId;
        Country = country;
        this.provice = provice;
        this.company = company;
        this.fax = fax;
        this.typeCustomer = typeCustomer;
        this.VATId = VATId;
    }

    public CustomerEntity() {
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipId() {
        return ZipId;
    }

    public void setZipId(String zipId) {
        ZipId = zipId;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getProvice() {
        return provice;
    }

    public void setProvice(String provice) {
        this.provice = provice;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getTypeCustomer() {
        return typeCustomer;
    }

    public void setTypeCustomer(String typeCustomer) {
        this.typeCustomer = typeCustomer;
    }

    public String getVATId() {
        return VATId;
    }

    public void setVATId(String VATId) {
        this.VATId = VATId;
    }
}
