package hr.tvz.smolcic.hardwareapp.command;

import hr.tvz.smolcic.hardwareapp.enums.HardwareType;
import hr.tvz.smolcic.hardwareapp.util.validators.HardwareTypeEnumValue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

public class HardwareCommand {

    @NotBlank(message = "Hardware name must not be empty.")
    String name;

    @NotBlank(message = "Hardware code must not be empty.")
    String code;

    @NotNull(message = "Price must not be null.")
    @PositiveOrZero(message = "Stock amount must be entered as a positive number.")
    double price;

    @NotNull(message = "Hardware type must not be null.")
    @NotBlank(message = "Hardware type must not be blank.")
    @HardwareTypeEnumValue(enumClass = HardwareType.class)
    String type;

    @NotNull(message = "Stock amount must not be null.")
    @PositiveOrZero(message = "Stock amount must be entered as a positive integer.")
    Integer stock;

    public HardwareCommand(String name, String code, double price, String hardwareType, Integer stockAmount) {
        this.name = name;
        this.code = code;
        this.price = price;
        this.type = hardwareType;
        this.stock = stockAmount;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public double getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

    public Integer getStock() {
        return stock;
    }
}
