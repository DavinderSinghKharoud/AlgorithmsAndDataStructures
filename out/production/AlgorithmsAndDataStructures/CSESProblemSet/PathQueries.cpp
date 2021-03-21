/*
Problem Name: Path Queries
Problem Link: https://cses.fi/problemset/task/1138
Author: Sachin Srivastava (mrsac7)
*/
#include<bits/stdc++.h>
#include <ext/pb_ds/assoc_container.hpp>
#include <ext/pb_ds/tree_policy.hpp>
using namespace __gnu_pbds;
using namespace std;
template<typename... T>
void see(T&... args) { ((cin >> args), ...);}
template<typename... T>
void put(T&&... args) { ((cout << args << " "), ...);}
template<typename... T>
void putl(T&&... args) { ((cout << args << " "), ...); cout<<'\n';}
#define error(args...) { string _s = #args; replace(_s.begin(), _s.end(), ',', ' '); stringstream _ss(_s); istream_iterator<string> _it(_ss); err(_it, args); }
void err(istream_iterator<string> it) {}
template<typename T, typename... Args>
void err(istream_iterator<string> it, T a, Args... args) {cerr << *it << "=" << a << ", "; err(++it, args...);}
#define int long long
#define pb push_back
#define F first
#define S second
#define ll long long
#define ull unsigned long long
#define ld long double
#define pii pair<int,int>
#define tiii tuple<int,int,int>
#define vi vector<int>
#define vii vector<pii>
#define vc vector
#define L cout<<'\n';
#define E cerr<<'\n';
#define all(x) x.begin(),x.end()
#define rep(i,a,b) for (int i=a; i<b; ++i)
#define rev(i,a,b) for (int i=a; i>b; --i)
#define IOS ios_base::sync_with_stdio(false);cin.tie(0);cout.tie(0);
#define setpr(x) cout<<setprecision(x)<<fixed
#define sz size()
#define seea(a,x,y) for(int i=x;i<y;i++){cin>>a[i];}
#define seev(v,n) for(int i=0;i<n;i++){int x; cin>>x; v.push_back(x);}
#define sees(s,n) for(int i=0;i<n;i++){int x; cin>>x; s.insert(x);}
#define ordered_set tree<int, null_type,less<int>, rb_tree_tag,tree_order_statistics_node_update>

const ll inf = 1LL<<62;
const ld ep = 0.0000001;
const ld pi = acos(-1.0);
const ll md = 1000000007;

vi adj[200005];
int trav[200005];
int v[200005];
int n;
int seg[1000005];
void upd(int s, int x) {
    s+=n; seg[s] += x;
    s>>=1;
    while(s>0) {
        seg[s] = seg[2*s + 1] + seg[2*s];
        s>>=1;
    }
}
int query(int x, int y) {
    int s = 0;
    x+=n; y+=n;
    while(x<=y) {
        if (x&1) s+= seg[x++];
        if (~y&1) s+= seg[y--];
        // error(s);E;
        x>>=1, y>>=1;
    }
    return s;
}

int ind = 0;
int c[200005];
void dfs(int s, int p) {
    trav[s] = ind++;
    c[s] = 1;
    for (auto i: adj[s]) {
        if (i!=p) {
            dfs(i,s);
            c[s] += c[i];
        }
    }
}
void solve(){
    int q; see(n,q);
    seea(v,1,n+1);
    rep(i,0,n-1){
        int x,y; see(x,y);
        adj[x].pb(y), adj[y].pb(x);
    }
    dfs(1,0);
    int nn = n;
    n = pow(2,ceil(log2(n)));
    rep(i,1,nn+1) {
        upd(trav[i], v[i]);
        upd(trav[i]+c[i], - v[i]);
    }
    while(q--) {
        int z; see(z);
        if (z==1) {
            int s,x; see(s,x);
            x -= v[s]; v[s] += x;
            upd(trav[s],x);
            upd(trav[s]+c[s], -x);
        }
        else {
            int x; see(x);
            put(query(0,trav[x]));
        }
    }
}
signed main(){
    IOS;
    #ifdef LOCAL
    freopen("input.txt", "r" , stdin);
    freopen("output.txt", "w", stdout);
    #endif
    int t=1;
    //cin>>t;
    rep(i,1,t+1){
        solve();
        cout<<'\n';
    }
    #ifdef LOCAL
    clock_t tStart = clock();
    cerr<<fixed<<setprecision(10)<<"\nTime Taken: "<<(double)(clock()- tStart)/CLOCKS_PER_SEC<<endl;
    #endif
}