package hr.tvz.smolcic.hardwareapp.util.converters;

import hr.tvz.smolcic.hardwareapp.enums.HardwareType;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class HardwareTypeAttributeConverter implements AttributeConverter<HardwareType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(HardwareType attribute) {
        if (attribute == null)
            return null;

        switch(attribute) {
            case CPU:
                return 1;
            case GPU:
                return 2;
            case MBO:
                return 3;
            case RAM:
                return 4;
            case STORAGE:
                return 5;
            case OTHER:
                return 6;
            default:
                throw new IllegalArgumentException(attribute + " not supported.");
        }
    }

    @Override
    public HardwareType convertToEntityAttribute(Integer dbData) {
        if (dbData == null)
            return null;

        switch (dbData) {
            case 1:
                return HardwareType.CPU;

            case 2:
                return HardwareType.GPU;

            case 3:
                return HardwareType.MBO;

            case 4:
                return HardwareType.RAM;

            case 5:
                return HardwareType.STORAGE;
            case 6:
                return HardwareType.OTHER;
            default:
                throw new IllegalArgumentException(dbData + " not supported.");
        }
    }
}
