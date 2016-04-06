package classes.web.entity;

public class Transport {

    private int stateNumber;
    private String model;
    private int tonnage;
    private TrailerType trailerType;
    private int paymentForKilometer;
    private boolean isOrdered = false;

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
    public boolean isOrdered() {return isOrdered; }

    public void setStateNumber(int stateNumber) {
        this.stateNumber = stateNumber;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setTonnage(int tonnage) { this.tonnage = tonnage; }
    public void setTrailerType(TrailerType trailerType) {
        this.trailerType = trailerType;
    }
    public void setPaymentForKilometer(int paymentForKilometer) {
        this.paymentForKilometer = paymentForKilometer;
    }
    public void order() { isOrdered = true; }

    /**
     * Enum of trailer types
     */
    private enum TrailerType {
        SEMI_TRAILER("Semi-trailer"), // 1
        REFRIGERATOR("Refrigerator"), // 2
        OPEN_PLATFORM("Open-platform"), // 3
        ROAD_TRAIN("Road train"), // 4
        ISOTHERM("Thermos"), // 5
        JUMBO("G-type"); // 6

        private String trailerTypeName;

        TrailerType(String trailerTypeName) {
            this.trailerTypeName = trailerTypeName;
        }

        @Override
        public String toString() {
            return this.trailerTypeName;
        }
    }
}
