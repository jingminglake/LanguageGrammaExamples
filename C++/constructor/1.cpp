#include <iostream>
using namespace std;

class A {
public:
    A(){}
    A(int _y, int _x) : y(_y), x(y){
    }
    int x;
    int y;
    int z;
};

int main() {
    A a(100, 111);
    cout << a.x << " " << a.y << endl;
    return 0;
}
