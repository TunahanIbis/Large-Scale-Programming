#include <iostream>
using namespace std;

// Part I
// Task: Write a C++ class named Vehicle with properties fuelAmount and fuelUsage.
// Implement a method that calculates how far the vehicle can go using the fuel.

class Vehicle {
protected:
    double fuelAmount, fuelUsage;

public:
    Vehicle(double fuelAmount, double fuelUsage) {
        this->fuelAmount = fuelAmount;
        this->fuelUsage = fuelUsage;
    }

    virtual double distance() {
        return fuelAmount / fuelUsage;
    }

    virtual double getfuel() {
        return fuelAmount;
    }

    virtual double getUsage() {
        return fuelUsage;
    }
};

// Part II
// Task: Write two child classes for the class Vehicle, namely Truck and Car.
// Override the method that you previously wrote for these child classes however you want.

class Truck : public Vehicle {
public:
    Truck(double fuelAmount, double fuelUsage) : Vehicle(fuelAmount, fuelUsage) {}

    double distance() override {
        return fuelAmount / (fuelUsage * 1.5); // Adjust the truck to make it use more fuel than car
    }

    double getfuel() override {
        return fuelAmount;
    }

    double getUsage() override {
        return fuelUsage;
    }
};

class Car : public Vehicle{
public:
    Car(double fuelAmount, double fuelUsage) : Vehicle(fuelAmount, fuelUsage) {}

    double distance() override {
        return fuelAmount / fuelUsage;
    }

    double getfuel() override {
        return fuelAmount;
    }

    double getUsage() override {
        return fuelUsage;
    }
};

// Main Method
int main() {
    Truck t1(450.0, 30.0);
    Truck t2(573.4, 33.8);
    Car c1(270.0, 9.0);
    Car c2(364.1, 26.4);

    cout << "\n";
    cout << "****INFOS****" << endl;
    cout << "First Truck - Fuel Amount: " << t1.getfuel() << " litres, Fuel Usage: " << t1.getUsage() << " litres per km" << endl;
    cout << "Second Truck - Fuel Amount: " << t2.getfuel() << " litres, Fuel Usage: " << t2.getUsage() << " litres per km" << endl;
    cout << "\n";
    cout << "First Car - Fuel Amount: " << c1.getfuel() << " litres, Fuel Usage: " << c1.getUsage() << " litres per km" << endl;
    cout << "Second Car - Fuel Amount: " << c2.getfuel() << " litres, Fuel Usage: " << c2.getUsage() << " litres per km" << endl;

    cout << "\n";
    cout << "****DISTANCES****" << endl;
    cout << "First truck can go " << t1.distance() << " kilometers." << endl;
    cout << "Second truck can go " << t2.distance() << " kilometers." << endl;
    cout << "\n";

    cout << "First car can go " << c1.distance() << " kilometers." << endl;
    cout << "Second car can go " << c2.distance() << " kilometers." << endl;
    cout << "\n";

    return 0;
}
