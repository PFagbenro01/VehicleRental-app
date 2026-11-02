public class RentalApp {

    static class Vehicle {
        private String plateNumber;
        private String modelName;
        private double dailyPrice;
        private static int vehicleCount = 0;

        public Vehicle(String plateNumber, String modelName) {
            this(plateNumber, modelName, 50.0);
        }

        public Vehicle(String plateNumber, String modelName, double dailyPrice) {
            this.plateNumber = plateNumber;
            this.modelName = modelName;
            this.dailyPrice = dailyPrice;
            vehicleCount++;
        }

        public String getPlateNumber() { return plateNumber; }
        public String getModelName() { return modelName; }
        public double getDailyPrice() { return dailyPrice; }

        public void setDailyPrice(double dailyPrice) {
            if (dailyPrice > 0) {
                this.dailyPrice = dailyPrice;
            } else {
                System.out.println("Warning: Price must be greater than 0. Keeping old price.");
            }
        }

        public double calculateCost(int days) {
            return dailyPrice * days;
        }

        public double calculateCost(int days, double discountPercent) {
            double cost = calculateCost(days);
            return cost - (cost * discountPercent / 100.0);
        }

        public static int getVehicleCount() {
            return vehicleCount;
        }

        @Override
        public String toString() {
            return "Vehicle [Plate=" + plateNumber + ", Model=" + modelName + ", Price=$" + dailyPrice + "]";
        }
    }

    static class Car extends Vehicle {
        private int seats;

        public Car(String plateNumber, String modelName, double dailyPrice, int seats) {
            super(plateNumber, modelName, dailyPrice);
            this.seats = seats;
        }

        @Override
        public String toString() {
            return "Car -> " + super.toString() + ", Seats=" + seats;
        }
    }

    static class Truck extends Vehicle {
        private double loadCapacity;

        public Truck(String plateNumber, String modelName, double dailyPrice, double loadCapacity) {
            super(plateNumber, modelName, dailyPrice);
            this.loadCapacity = loadCapacity;
        }

        @Override
        public double calculateCost(int days) {
            return (getDailyPrice() + 20) * days;
        }

        @Override
        public String toString() {
            return "Truck -> " + super.toString() + ", Load Capacity=" + loadCapacity + " tons";
        }
    }

    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle("REG123", "Standard Vehicle");
        Car car = new Car("CAR456", "Honda Civic", 80, 5);
        Truck truck = new Truck("TRK789", "Ford F-150", 100, 2.5);

        System.out.println(vehicle);
        System.out.println(car);
        System.out.println(truck);

        car.setDailyPrice(-10);

        System.out.println("\nCar 3-day cost: $" + car.calculateCost(3));
        System.out.println("Car 3-day cost with 10% discount: $" + car.calculateCost(3, 10));

        System.out.println("\nTruck 3-day cost: $" + truck.calculateCost(3));
        System.out.println("Truck 3-day cost with 10% discount: $" + truck.calculateCost(3, 10));

        System.out.println("\nTotal Vehicles: " + Vehicle.getVehicleCount());

        // Think about it:
        // Truck overrides calculateCost(). If Vehicle v = truck;
        // then v.calculateCost(3) still runs Truck's version.
    }
}
