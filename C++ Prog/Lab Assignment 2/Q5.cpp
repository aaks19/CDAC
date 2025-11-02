#include<iostream>
using namespace std;

int main(){
    int n;
    cout << "Enter number of elements: ";
    cin >> n;
    int arr[n];
    for(int i = 0; i < n; i++){
        cout << "Enter element: ";
        cin >> arr[i];
    }
    cout << "Original array: " << endl;
    for(int i = 0; i < n; i++){
        cout << arr[i] << "  ";
    }
    cout << endl;

    int temp[n];
    for(int i = 0; i < n; i++){
        temp[i] = arr[n - i - 1];
    }

    cout << "Reversed array: " << endl;
    for(int i = 0; i < n; i++){
        cout << temp[i] << "  ";
    }
    cout << endl;

    return 0;
}
