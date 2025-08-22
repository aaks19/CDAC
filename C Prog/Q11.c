//  Write a program Multiply two Floating Point Numbers. 

#include <stdio.h>

int main(){
    float num1, num2;
    printf("Enter first number: ");
    scanf("%f", &num1);
    printf("Enter second number: ");
    scanf("%f", &num2);

    float product = num1 * num2;
    printf("Product = %f\n", product);

    return 0;
}
