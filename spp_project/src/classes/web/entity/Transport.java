package classes.web.entity;

public class Transport {

    private int stateNumber;
    private String model;
    private int tonnage;
    private TrailerType trailerType;
    private int paymentForKilometer;

    public Transport() {}

    public Transport(int stateNumber,
                     String model,
                     int tonnage,
                     TrailerType trailerType,
                     int paymentForKilometer) {
        this.stateNumber = stateNumber;
        this.model = model;
        this.tonnage = tonnage;
        this.trailerType = trailerType;
        this.paymentForKilometer = paymentForKilometer;
    }

    public int getStateNumber() {
        return stateNumber;
    }
    public String getModel() {
        return model;
    }
    public int getTonnage() {
        return tonnage;
    }
    public TrailerType getTrailerType() {
        return trailerType;
    }
    public int getPaymentForKilometer() {
        return paymentForKilometer;
    }

    public void setStateNumber(int stateNumber) {
        this.stateNumber = stateNumber;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setTonnage(int tonnage) { this.tonnage = tonnage; }
    public void setTrailerType(int trailerType) {
        for (TrailerType element : TrailerType.values())
            if (element.getEnumValue() == trailerType)
                this.trailerType = element;
    }
    public void setPaymentForKilometer(int paymentForKilometer) {
        this.paymentForKilometer = paymentForKilometer;
    }

    /**
     * Enum of trailer types
     */
    public enum TrailerType {
        SEMI_TRAILER("Semi-trailer", 1), // 1
        REFRIGERATOR("Refrigerator", 2), // 2
        OPEN_PLATFORM("Open-platform", 3), // 3
        ROAD_TRAIN("Road train", 4), // 4
        ISOTHERM("Thermos", 5), // 5
        JUMBO("G-type", 6); // 6

        private String trailerTypeName;
        private int enumValue;

        TrailerType(String trailerTypeName, int enumValue) {
            this.trailerTypeName = trailerTypeName;
            this.enumValue = enumValue;
        }

        public String getTrailerTypeName() {
            return trailerTypeName;
        }

        public int getEnumValue() {
            return enumValue;
        }
    }
}
