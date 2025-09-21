//count vowels and occurrence of vowel

#include<iostream>
using namespace std;

int countVowel(char *s, int freq[5]){
    int count = 0;
    while((*s) != '\0'){
        if (*s == 'a' || *s == 'A') { count++; freq[0]++; }
        else if (*s == 'e' || *s == 'E') { count++; freq[1]++; }
        else if (*s == 'i' || *s == 'I') { count++; freq[2]++; }
        else if (*s == 'o' || *s == 'O') { count++; freq[3]++; }
        else if (*s == 'u' || *s == 'U') { count++; freq[4]++; }
        s++;
    }
    return count;
}

int main(){
    char str[100];
    cout<<"Enter character: "<<endl;
    cin>>str;
    int freq[5] = {0};  // frequency for a, e, i, o, u
    int vowels = countVowel(str, freq);    
    cout<<"Vowels = "<<vowels<<endl;
    cout << "a: " << freq[0] << endl;
    cout << "e: " << freq[1] << endl;
    cout << "i: " << freq[2] << endl;
    cout << "o: " << freq[3] << endl;
    cout << "u: " << freq[4] << endl;

}