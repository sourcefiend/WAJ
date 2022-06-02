package hr.tvz.smolcic.hardwareapp.DTOs;

public class HardwareDTO {

    String name;
    double price;

    String code;

    public HardwareDTO(String name, double price, String code) {
        this.name = name;
        this.price = price;
        this.code = code;
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
}
