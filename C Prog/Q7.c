// Accept temperatures in Fahrenheit (F) and print it in Celsius(C) and Kelvin (K) (Hint: C=5/9(F-32), K = C + 273.15) 

#include <stdio.h>

int main() {
    float fahrenheit, celsius, kelvin;
    printf("Enter temperature in Fahrenheit: ");
    scanf("%f", &fahrenheit);
    celsius = 5.0 / 9.0 * (fahrenheit - 32);
    kelvin = celsius + 273.15;
    printf("Temperature in Celsius: %f\n", celsius);
    printf("Temperature in Kelvin: %f\n", kelvin);
    return 0;
}