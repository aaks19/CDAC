//  Accept two numbers and swap two numbers using 
// i) Third variable 
// ii) By performing arithmetic operations 

// by third variable

#include <stdio.h>

int main() {
    int a, b, c;
    printf("enter first number 'a': ");
    scanf("%d", &a);
    printf("enter second number 'b': ");
    scanf("%d", &b);
    printf("Before swapping a = %d , b = %d \n",a, b);
    c = a;
    a = b;
    b = c;
    printf("After swapping a = %d , b = %d \n",a, b);
}

// by arithmetic operations

#include <stdio.h>

int main() {
    int a, b;
    printf("enter first number 'a': ");
    scanf("%d", &a);
    printf("enter second number 'b': ");
    scanf("%d", &b);
    printf("Before swapping a = %d , b = %d \n",a, b);
    a = a + b;
    b = a - b;
    a = a - b;
    printf("After swapping a = %d , b = %d \n",a, b);
}