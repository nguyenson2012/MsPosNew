package project.mspos.entity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by CONGHAO on 5/16/2016.
 */
public class AddressEntity {

    String country;
    String state;
    String incrementId;
    int isActive;
    String prefix;
    String firstName;
    String middleName;
    String lastName;
    String suffix;
    String company;
    String city;
    String countryId;
    String region;
    String postcode;
    String telephone;
    String fax;
    String vatId;
    String regionId;
    String street;
    int customerId;
    int id;

    public AddressEntity() {}

    public String convertObjectToJson(AddressEntity addressEntity){

        JSONObject jsonObject= new JSONObject();
        try {

            jsonObject.put("increment_id", addressEntity.getIncrementId());
            jsonObject.put("is_active", addressEntity.getIsActive());
            jsonObject.put("prefix", addressEntity.getPrefix());
            jsonObject.put("firstname", addressEntity.getFirstName());
            jsonObject.put("middlename", addressEntity.getMiddleName());
            jsonObject.put("lastname", addressEntity.getLastName());
            jsonObject.put("suffix", addressEntity.getSuffix());
            jsonObject.put("city", addressEntity.getCity());
            jsonObject.put("country_id", addressEntity.getCountryId());
            jsonObject.put("region", addressEntity.getRegion());
            jsonObject.put("postcode", addressEntity.getPostcode());
            jsonObject.put("telephone", addressEntity.getTelephone());
            jsonObject.put("fax", addressEntity.getFax());
            jsonObject.put("vatId", addressEntity.getVatId());
            jsonObject.put("region_id", addressEntity.getRegionId());
            jsonObject.put("street", addressEntity.getStreet());
            jsonObject.put("customer_id", addressEntity.getCustomerId());
            jsonObject.put("id", addressEntity.getId());

            return jsonObject.toString();
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "";
        }

    }
    public String getIncrementId() {
        return incrementId;
    }

    public void setIncrementId(String incrementId) {
        this.incrementId = incrementId;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getVatId() {
        return vatId;
    }

    public void setVatId(String vatId) {
        this.vatId = vatId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
