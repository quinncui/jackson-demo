package com.jackson.databinding;

import java.util.*;

/**
 * Created by frankun on 2016/9/20.
 */
public class Country {
    private String country_id;
    private Date birthDate;
    private List<String> nation = new ArrayList<String>();
    private String[] lakes;
    private List<Province> provinces = new ArrayList<Province>();
    private Map<String, Integer> traffic = new HashMap<String, Integer>();

    public Country(){
        // TODO: 2016/9/20
    }

    public Country(String countryId){
        this.country_id = countryId;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<String> getNation() {
        return nation;
    }

    public void setNation(List<String> nation) {
        this.nation = nation;
    }

    public String[] getLakes() {
        return lakes;
    }

    public void setLakes(String[] lakes) {
        this.lakes = lakes;
    }

    public List<Province> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<Province> provinces) {
        this.provinces = provinces;
    }

    public Map<String, Integer> getTraffic() {
        return traffic;
    }

    public void setTraffic(Map<String, Integer> traffic) {
        this.traffic = traffic;
    }

    //添加键值
    public void addTraffic(String key, Integer value) {
        traffic.put(key, value);
    }

    @Override
    public String toString(){
        return "Country [country_id=" + country_id + ", birthDate=" + birthDate
                + ", nation=" + nation + ", lakes=" + Arrays.toString(lakes)
                + ", provinces=" + provinces + ", traffic=" + traffic + "]";
    }
}
