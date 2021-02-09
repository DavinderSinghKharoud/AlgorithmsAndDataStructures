#include <bits/stdc++.h>
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/tree_policy.hpp>

using namespace std;
using namespace __gnu_pbds;

typedef tree<int, null_type, less<int>, rb_tree_tag, tree_order_statistics_node_update> ordered_set;

#define ll long long
#define ld long double
#define pii pair<int, int>
#define f first
#define s second
#define readl(_s) getline(cin, (_s));
#define boost() cin.tie(0); cin.sync_with_stdio(0)

const int MN = 200005;

int n, c[MN], ans[MN];
vector<int> a[MN];
set<int> s[MN];

void dfs(int cur, int par) {
    int mx = 0, id = 0;
    for (int nxt : a[cur]) {
        if (nxt == par) continue;
        dfs(nxt, cur);
        if (s[nxt].size() > mx) mx = s[nxt].size(), id = nxt;
    }
    if (!id) {ans[cur] = 1; return;}
    swap(s[cur], s[id]);
    for (int nxt : a[cur]) {
        if (nxt == par || nxt == id) continue;
        for (int p : s[nxt]) s[cur].insert(p);
        s[nxt].clear();
    }
    s[cur].insert(c[cur]);
    ans[cur] = s[cur].size();
}

int main() {
    boost();

    cin >> n;
    for (int i = 1; i <= n; i++) cin >> c[i], s[i].insert(c[i]);
    for (int i = 1, u, v; i < n; i++) {
        cin >> u >> v;
        a[u].push_back(v);
        a[v].push_back(u);
    }
    dfs(1, 0);
    for (int i = 1; i <= n; i++) printf("%d ", ans[i]);

    return 0;
}