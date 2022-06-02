package hr.tvz.smolcic.hardwareapp.model;

import hr.tvz.smolcic.hardwareapp.enums.HardwareType;
import hr.tvz.smolcic.hardwareapp.util.converters.HardwareTypeAttributeConverter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "HARDWARE")
public class Hardware {

    @Id
    @Column(name = "HARDWARE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;

    @Column(name = "NAME")
    String name;

    @Column(name = "CODE")
    String code;

    @Column(name = "PRICE")
    double price;

    @Column(name = "HARDWARE_TYPE")
    @Enumerated(EnumType.STRING)
    @Convert(converter = HardwareTypeAttributeConverter.class)
    HardwareType type;

    @Column(name = "STOCK_AMOUNT")
    int stock;

    @OneToMany(mappedBy="hardware", fetch=FetchType.EAGER)
    private List<Review> reviewList;

    public Hardware() {

    }

    public Hardware(int id, String name, String code, double price, HardwareType type, int stock) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = type;
        this.stock = stock;
    }

    public Hardware(String name, String code, double price, HardwareType hardwareType, int stockAmount) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = hardwareType;
        this.stock = stockAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public HardwareType getType() {
        return type;
    }

    public void setType(HardwareType hardwareType) {
        this.type = hardwareType;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stockAmount) {
        this.stock = stockAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
