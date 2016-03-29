package java.web.entity;

public class Transport {
    private int stateNumber;
    private String model;
    private TrailerType trailerType;
    private int paymentForKilometer;

    public Transport() {}

    public Transport(int stateNumber,
                     String model,
                     TrailerType trailerType,
                     int paymentForKilometer) {
        this.stateNumber = stateNumber;
        this.model = model;
        this.trailerType = trailerType;
        this.paymentForKilometer = paymentForKilometer;
    }

    public int getStateNumber() {
        return stateNumber;
    }
    public String getModel() {
        return model;
    }
    public TrailerType getTrailerType() {
        return trailerType;
    }
    public int getPaymentForKilometer() {
        return paymentForKilometer;
    }



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
