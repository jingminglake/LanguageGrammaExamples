#include <iostream>
using namespace std;

class Node {
public:
  Node() {
  }
private:
  static int x;
};

//int Node::x = 1000;

int main() {
  int Node::x = 10;
  //cout << Node::x << endl;
  return 0;
}
