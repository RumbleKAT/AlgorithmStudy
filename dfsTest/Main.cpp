#include<iostream>
#define min(a,b) (a)<(b)?a:b
using namespace std;
int lazy[4][400001];
int N, Q;
long long totalsum[400001];
long long sum;
int sizes = 1;
long long sales[100001];
int k;
int value;
int l, r;

void updatelazy(int s, int e, int node) {
    cout << " Node : " << node << " start : " << s << " " << l << " end : "  << e  << " " << r << endl;

    if (s > r || e< l)
    {
        return;
    }
    if (s >= l && e <= r)
    {
        cout << " Node : " << node << " start : " << s << " " << l << " end : "  << e  << " " << r << endl;
        lazy[k][node] += value;
        return;
    }
    int m = (s + e) / 2;
    updatelazy(s, m, node * 2);
    updatelazy(m + 1, e, node * 2 + 1);
}
long long lazyseg(int x) {
    x = sizes + x - 1;
    cout << " x  : " << x << endl;
    long long pri = 0;
    while (x > 0)
    {
        pri += lazy[k][x];
        x /= 2;
    }
    return pri;
}
void update(int a, int b) {
    if (a == 1)
    {
        totalsum[a] += b;
    }
    else
    {
        totalsum[a] += b;
        update(a / 2, b);
    }
}
int main() {
    ios::sync_with_stdio(false);
    int t;
    cin >> t;
    for (int idx = 1; idx <= t; idx++)
    {
        sum = 0;
        cin >> N >> Q;
        sizes = 1;
        while (sizes < N)
        {
            sizes *= 2;
        }
        cout << "!!!" << sizes << endl;
        int ss = sizes * 2;
        int i = 1;
        while (i <= ss)
        {
            totalsum[i] = 0;
            lazy[1][i] = 0;
            lazy[2][i] = 0;
            lazy[3][i] = 0;
            i++;
        }

        int x, y, K, c, q;
        for (int mm = 0; mm < Q; mm++)
        {
            cin >> q;
            if (q == 1)
            {
                cin >> x >> y >> K >> c;
                k = K;
                value = c;
                l = x;
                r = y;
                updatelazy(1, N, 1);

                cout << " K : " << k << endl;
                 for(int i = 1; i<20;i++){
                    cout << " " << lazy[k][i];
                }cout << endl;
            }
            else if (q == 2)
            {
                cin >> x >> c;
                long long a, b, d;

                // for(int i = 0; i<16;i++){
                //     cout << " " << totalsum[i];
                // }cout << endl;

                // cout << (sizes + x - 1) << endl;                

                k = 1;
                a = lazyseg(x) - totalsum[sizes + x - 1];
                k = 2;
                b = lazyseg(x) - totalsum[sizes + x - 1];
                k = 3;
                d = lazyseg(x) - totalsum[sizes + x - 1];

                long long minvalue = min(min(a, min(b, d)), c);
                update(sizes + x - 1, minvalue);
            }
            else
            {
                cin >> x >> y;
                int l = x + sizes - 1, r = y + sizes - 1;
                while (l < r)
                {
                    if (l % 2 == 1)
                    {
                        sum += totalsum[l];
                        l = (l + 1) / 2;
                    }
                    else
                    {
                        l /= 2;
                    }
                    if (r % 2 == 0)
                    {
                        sum += totalsum[r];
                        r = (r - 1) / 2;
                    }
                    else
                    {
                        r /= 2;
                    }
                }
                if (l == r)
                {
                    sum += totalsum[l];
                }
            }
        }

        cout << "#" << idx << " " << sum << endl;
    }
}
