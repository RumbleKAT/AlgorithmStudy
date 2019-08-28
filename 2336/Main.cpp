#include <cstdio>
#include <algorithm>
#include <iostream>
#define INF 987654321
#define MAX_N 500000
using namespace std;
struct student {
    int x, y, z;
};
bool cmp(student a, student b) {
    return a.x < b.x;
}
student s[MAX_N];
int n, seg[4 * MAX_N], res, t;
int update(int pos, int val, int node, int x, int y) {
    if (y < pos || pos < x)
        return seg[node];
    if (x == y)
        return seg[node] = val;
    int mid = (x + y) >> 1;
    return seg[node] = min(update(pos, val, node * 2, x, mid), update(pos, val, node * 2 + 1, mid + 1, y));
}
int query(int lo, int hi, int node, int x, int y) {
    if (y < lo || hi < x)
        return INF;
    if (lo <= x&&y <= hi)
        return seg[node];
    int mid = (x + y) >> 1;
    return min(query(lo, hi, node * 2, x, mid), query(lo, hi, node * 2 + 1, mid + 1, y));
}
int main() {
    scanf("%d", &n);
    for (int i = 1; i <= n; i++) {
        scanf("%d", &t);
        s[t].x = i;
    }
    for (int i = 1; i <= n; i++) {
        scanf("%d", &t);
        s[t].y = i;
    }
    for (int i = 1; i <= n; i++) {
        scanf("%d", &t);
        s[t].z = i;
    }
    sort(s + 1, s + 1 + n, cmp);
    for (int i = 1; i <= n; i++)
        update(i, INF, 1, 1, n);

    cout << res << endl;
    for (int i = 1; i <= n; i++) {
        if (query(1, s[i].y, 1, 1, n)>s[i].z)
            res++;
        update(s[i].y, s[i].z, 1, 1, n);
    }
    printf("%d\n", res);
    return 0;
}


