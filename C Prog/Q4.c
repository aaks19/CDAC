//  Accept the basic salary of an employee and compute the net salary after adding earnings and subtracting 
// deductions. 
// PF is 2 % of basic 
// Tax is 3 % of basic 
// HRA is 5 % basic 
// DA is 8 % of basic 

#include <stdio.h>

int main() {
    float basic_sal , pf, tax, hra, da, net_sal;
    printf("Enter basic salary: ");
    scanf("%f", &basic_sal);
    pf = 0.02 * basic_sal;
    tax = 0.03 * basic_sal; 
    hra = 0.05 * basic_sal; 
    da = 0.08 * basic_sal; 
    net_sal = basic_sal + hra + da - pf - tax; 
    printf("Net salary = %.2f\n", net_sal);
}