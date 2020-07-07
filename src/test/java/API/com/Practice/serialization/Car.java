package API.com.Practice.serialization;

public class Car {

    private String model;
    private int year;
    private double engineSize;
    private String color;
    private int numOfDoors;

    public Car(String model, int year, double engineSize, String color, int numOfDoors) {
        this.model = model;
        this.year = year;
        this.engineSize = engineSize;
        this.color = color;
        this.numOfDoors = numOfDoors;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(double engineSize) {
        this.engineSize = engineSize;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getNumOfDoors() {
        return numOfDoors;
    }

    public void setNumOfDoors(int numOfDoors) {
        this.numOfDoors = numOfDoors;
    }
}
