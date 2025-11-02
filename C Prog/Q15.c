// 3. Write a program, which accepts annual basic salary of an employee and calculates and displays the 
// Income tax as per the following rules. 
// Basic: < 1, 50,000 Tax = 0 
// 1, 50,000 to 3,00,000 Tax = 20% 
// > 3,00,000 Tax = 30% 

#include <stdio.h>

int main(){
    float salary, tax;
    printf("Enter annual basic salary: ");
    scanf("%f", &salary);

    if (salary < 150000) {
        tax = 0;
    } else if (salary >= 150000 && salary <= 300000) {
        tax = (salary - 150000) * 0.2;
    } else {
        tax = (salary - 300000) * 0.3 + 30000;
    }

    printf("Income Tax = %.2f\n", tax);
    return 0;
}
