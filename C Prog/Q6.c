// Accept dimensions of a cylinder and print the surface area and volume (Hint: surface area = 2πr 2 + 2πrh, volume = π r 2 h). 
// Define a constant variable pi=3.14.

#include <stdio.h>

int main() {
    float radius, height;
    printf("Enter radius: ");
    scanf("%f", &radius);
    printf("Enter height: ");
    scanf("%f", &height);
    float surface_area = 2 * 3.14 * radius * height + 2 * 3.14 * radius * radius;
    float volume = 3.14 * radius * radius * height;
    printf("Surface area = %f\n", surface_area);
    printf("Volume = %f\n", volume);
}