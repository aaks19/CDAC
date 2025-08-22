// 4. Accept radius from the user and write a program having menu with the following options and 
// corresponding actions: 
// Options                      Actions 
// 1. Area of Circle            Calculate and display the area of the circle 
// 2. Circumference of Circle   Calculate and display the circumference of the circle 
// 3. Volume of Sphere          Calculate and display the volume of the sphere 

#include <stdio.h>
#include <math.h>

int main() {
    int choice;
    float radius;

    printf("Enter the radius of the circle: ");
    scanf("%f", &radius);

    printf("Options:\n");
    printf("1. Area of Circle\n");
    printf("2. Circumference of Circle\n");
    printf("3. Volume of Sphere\n");
    printf("Enter your choice (1-3): ");
    scanf("%d", &choice);

    switch (choice) {
        case 1:
            printf("Area of Circle: %.2f\n", 3.14 * radius * radius);
            break;
        case 2:
            printf("Circumference of Circle: %.2f\n", 2 * 3.14 * radius);
            break;
        case 3:
            printf("Volume of Sphere: %.2f\n", (4.0/3.0) * 3.14 * radius * radius * radius);
            break;
        default:
            printf("Invalid choice\n");
    }

    return 0;
}