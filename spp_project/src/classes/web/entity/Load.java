package classes.web.entity;

public class Load {

    private int loadID;
    private int weight;
    private int costOfDelivery;
    private LoadType loadType;
    private String loadDescription;

    public Load() {}

    public Load(int loadID,
                int weight,
                int costOfDelivery,
                LoadType loadType,
                String loadDescription) {
        this.loadID = loadID;
        this.weight = weight;
        this.costOfDelivery = costOfDelivery;
        this.loadType = loadType;
        this.loadDescription = loadDescription;
    }

    public int getLoadID() {
        return loadID;
    }
    public int getWeight() {
        return weight;
    }
    public int getCostOfDelivery() {
        return costOfDelivery;
    }
    public LoadType getLoadType() {
        return loadType;
    }
    public String getLoadDescription() {
        return loadDescription;
    }

    public void setLoadID(int loadID) {
        this.loadID = loadID;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public void setCostOfDelivery(int costOfDelivery) {
        this.costOfDelivery = costOfDelivery;
    }
    public void setLoadType(int enumValue) {
        for (LoadType element : LoadType.values())
            if (element.getEnumValue() == enumValue)
                this.loadType = element;
    }
    public void setLoadDescription(String loadDescription) {
        this.loadDescription = loadDescription;
    }

    /**
     * Enum for goods
     */
    public enum LoadType {
        DANGEROUS("Dangerous", 1), // 1
        PERISHABLE("Perishable", 2), // 2
        SUPERHEAVY("Superheavy", 3), // 3
        ALIVE("Alive", 4), // 4
        BULKY("Bulky", 5); // 5

        private String loadTypeName;
        private int enumValue;

        LoadType(String loadTypeName, int enumValue) {
            this.loadTypeName = loadTypeName;
            this.enumValue = enumValue;
        }

        public int getEnumValue() {
            return enumValue;
        }

        public String getLoadTypeName() {
            return loadTypeName;
        }
    }
}
