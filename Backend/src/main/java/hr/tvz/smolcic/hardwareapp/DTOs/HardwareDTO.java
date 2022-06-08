package hr.tvz.smolcic.hardwareapp.DTOs;

public class HardwareDTO {


    String name;
    double price;

    String code;

    Integer stock;

    public HardwareDTO(String name, double price, String code) {
        this.name = name;
        this.price = price;
        this.code = code;
    }

    public HardwareDTO(String name, double price, String code, Integer stock) {
        this.name = name;
        this.price = price;
        this.code = code;
        this.stock = stock;
    }

    public Integer getStock() {
        return this.stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return this.name + " - " + this.stock;
    }
}
