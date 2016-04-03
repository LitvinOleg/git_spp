package java.web.entity;

public class Load {

    private int loadID;
    private int weight;
    private int costOfDelivery;
    private LoadType loadType;
    private String loadDescription;
    private boolean isOrdered = false;

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
    public boolean isOrdered() {return isOrdered; }

    public void setLoadID(int loadID) {
        this.loadID = loadID;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public void setCostOfDelivery(int costOfDelivery) {
        this.costOfDelivery = costOfDelivery;
    }
    public void setLoadType(LoadType loadType) {
        this.loadType = loadType;
    }
    public void setLoadDescription(String loadDescription) {
        this.loadDescription = loadDescription;
    }
    public void order() { isOrdered = true; }

    /**
     * Enum for goods
     */
    public enum LoadType {
        DANGEROUS("Dangerous"), // 1
        PERISHABLE("Perishable"), // 2
        SUPERHEAVY("Superheavy"), // 3
        ALIVE("Alive"), // 4
        BULKY("Bulky"); // 5

        private String loadTypeName;

        private LoadType(String loadTypeName) {
            this.loadTypeName = loadTypeName;
        }


        @Override
        public String toString() {
            return this.loadTypeName;
        }
    }
}
