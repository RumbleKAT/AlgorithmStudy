#include <stdio.h>
#include <algorithm>
using namespace std;
typedef long long ll;
struct Point {
   ll x, y;
   bool operator<(const Point &p) const {
      if (y == p.y) return x < p.x;
      return y < p.y;
   }
};
Point a[200001];
Point s[200001];
int N, sp;
void input(void) {
   scanf("%d", &N);
   for (int i = 1; i <= N; i++) {
      scanf("%lld%lld", &a[i].x, &a[i].y);
   }
}
int ccw(Point p1, Point p2, Point p3) {
   ll res = p1.x*p2.y + p2.x*p3.y + p3.x*p1.y;
   res -= p1.y*p2.x + p2.y*p3.x + p3.y*p1.x;
   if (res > 0) return 1;
   else if (res == 0) return 0;
   return -1;
}
ll getDistance(Point p1, Point p2) {
   ll dx = p1.x - p2.x;
   ll dy = p1.y - p2.y;
   return dx * dx + dy * dy;
}
bool compare(Point p1, Point p2) {
   int res = ccw(a[1], p1, p2);
   if (res == 0) {
      return getDistance(a[1], p1) < getDistance(a[1], p2);
   }
   return res > 0;
}
void makeConvexHull() {
   sort(a + 1, a + N + 1);
   sort(a + 2, a + N + 1, compare);
   sp = 0;
   s[++sp] = a[1];
   s[++sp] = a[2];
   for (int i = 3; i <= N; i++) {
      while (sp >= 2 && ccw(s[sp - 1], s[sp], a[i]) <= 0) {
         sp--;
      }
      s[++sp] = a[i];
   }
}
void rotateCalipers() {
   int i, j, ni, nj;
   j = 2;
   int cc;
   ll rtn = -1;
   Point p, q;
   for (i = 1; i <= sp; i++) {
      ni = i % sp + 1;
      while (true) {
         nj = j % sp + 1;
         cc = ccw({ 0,0 }, {s[ni].x - s[i].x,s[ni].y - s[i].y }, { s[nj].x - s[j].x,s[nj].y - s[j].y });
         if (cc>0) {
            j = nj;
         }
         else break;
      }
      ll dist = getDistance(s[i], s[j]);
      if (dist > rtn) {
         rtn = dist;
         p = s[i];
         q = s[j];
      }
   }
   printf("%lld %lld %lld %lld\n", p.x, p.y, q.x, q.y);
}
int main(void) {
   int T;
   scanf("%d", &T);
   for (int t = 1; t <= T; t++) {
      input();
      makeConvexHull();
      rotateCalipers();
   }
}