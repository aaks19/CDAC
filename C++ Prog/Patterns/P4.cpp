/*         Pattern2

            1
            2 2
            3 3 3
            4 4 4 4
            5 5 5 5 5         

*/


#include<bits/stdc++.h>
using namespace std;

void print(int n){
    for(int i=1;i<=n;i++){
        for (int j = 1; j <= i; j++)
        {
            cout << i << " ";
        }
        cout << endl;
    }
}

int main(){
    int n;
    cout << "Enter the value of n: ";
    cin >> n;

    print(n);  //pattern 2
}