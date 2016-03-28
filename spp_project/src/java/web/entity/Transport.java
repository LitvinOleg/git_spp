package java.web.entity;

public class Transport {
    private int stateNumber;
    private String model;
    private TrailerType trailerType;
    private int paymentForKilometer;

    public Transport(String model, int paymentForKilometer, int stateNumber, TrailerType trailerType) {
        this.model = model;
        this.paymentForKilometer = paymentForKilometer;
        this.stateNumber = stateNumber;
        this.trailerType = trailerType;
    }

    public String getModel() {
        return model;
    }
    public int getPaymentForKilometer() {
        return paymentForKilometer;
    }
    public int getStateNumber() {
        return stateNumber;
    }
    public TrailerType getTrailerType() {
        return trailerType;
    }

    /**
     * Enum of trailer types
     */
    private enum TrailerType {
        SEMI_TRAILER("Semi-trailer"),
        REFRIGERATOR("Refrigerator"),
        OPEN_PLATFORM("Open-platform"),
        ROAD_TRAIN("Road train"),
        ISOTHERM("Thermos"),
        JUMBO("G-type");

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
