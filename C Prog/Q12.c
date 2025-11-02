// Write a c programs to display Simple interest. 

#include <stdio.h>

int main(){
    float principal, rate, time;
    printf("Enter principal amount: ");
    scanf("%f", &principal);
    printf("Enter rate of interest: ");
    scanf("%f", &rate);
    printf("Enter time (in years): ");
    scanf("%f", &time);

    float simple_interest = (principal * rate * time) / 100;
    printf("Simple Interest = %f\n", simple_interest);

    return 0;
}
