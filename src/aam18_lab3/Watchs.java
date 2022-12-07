package aam18_lab3;

public class Watchs {

    int id;            
    String brand;  
    String mechanism;

    public Watchs() {
    }

    public Watchs(String brand, String mechanism) {
        this.brand = brand;
        this.mechanism = mechanism;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setMechanism(String mechanism) {
        this.mechanism = mechanism;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getMechanism() {
        return mechanism;
    }
    
    @Override
    public String toString() {
        return String.format("Бренд = %s, Механизм часов = %s", brand, mechanism);
    }
}
