#ifndef B__H
#define B__H

#include "a.h"
class A;

class B {
public:
  bool g();
private:
  A* a;
};
#endif
