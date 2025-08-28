#include <iostream>
using namespace std;

template <class T>
class Calculator {
	private:
		T num1, num2, result;
	public:
		Calculator(T num1, T num2) {
			this->num1 = num1;
			this->num2 = num2;
		}
		void add() {
			result = num1 + num2;
			cout << "Result is = " << result << endl;
		}
		void sub() {
			result = num1 - num2;
			cout << "Result is = " << result << endl;
		}
		void mul() {
			result = num1 * num2;
			cout << "Result is = " << result << endl;
		}
		void div() {
			if (num2 == 0) {
				cout << "Division by zero error!" << endl;
			} else {
				result = num1 / num2;
				cout << "Result is = " << result << endl;
			}
		}
};

int main() {
	

	int ch;
	do{
		cout << "Enter your choice : \n1.addition\n2.subtraction\n3.multiplication\n4.division\n5.exit" << endl;
		cin >> ch;

		if (ch >= 1 && ch <= 4) {
			cout << "Choose data type:\n1. int\n2. double" << endl;
			int dtype;
			cin >> dtype;

			if (dtype == 1) {
				int a, b;
				cout << "Enter two numbers: " << endl;
				cin >> a >> b;
				Calculator calc(a, b);
				switch (ch) {
					case 1: calc.add(); break;
					case 2: calc.sub(); break;
					case 3: calc.mul(); break;
					case 4: calc.div(); break;
				}
			} else if (dtype == 2) {
				double a, b;
				cout << "Enter two numbers: " << endl;
				cin >> a >> b;
				Calculator calc(a, b);
				switch (ch) {
					case 1: calc.add(); break;
					case 2: calc.sub(); break;
					case 3: calc.mul(); break;
					case 4: calc.div(); break;
				}
			} else {
				cout << "Invalid data type choice." << endl;
			}
		} else if (ch == 5) {
			cout << "Exit" << endl;
		} else {
			cout << "Invalid choice." << endl;
		}
	}while(ch!=5);
	return 0;
}
