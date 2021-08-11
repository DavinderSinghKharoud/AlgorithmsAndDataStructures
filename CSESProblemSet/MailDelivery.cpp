//g++  5.4.0
//Topic : Eular-tour

#include<bits/stdc++.h>
using namespace std;

#define ff first
#define ss second
#define int long long
#define pb push_back
#define pii pair< int,int >
#define fast ios::sync_with_stdio(0) , cin.tie(0) , cout.tie(0) ;

const int nax = 1e5+10;
set<int> g[nax];
stack<int> s;
vector<int>vis(nax,0) , eular;

void dfs(int v)
{
    vis[v] = 1;
    for(auto &i : g[v] )
        if( !vis[i] )
            dfs(i);
    return ;
}
void eular_dfs(int v,int p)
{
    s.push(v);
    if( g[v].find(p) != g[v].end() )
        g[v].erase( p );

    while( g[v].size() )
    {
        int xx = *g[v].begin();
        g[v].erase( g[v].begin() );
        eular_dfs(xx,v);
    }
    eular.pb(s.top());
    s.pop();
    return;
}
signed main()
{
    fast;
    int n , m;
    cin >> n >> m;
    for(int i=1 ; i<=m ; i++ )
    {
        int u ,v;
        cin >> u >> v;
        g[u].insert(v);
        g[v].insert(u);
    }
    dfs(1);//condition-1 : all nodes are connected
    bool f = 0;
    for(int i=1 ; i<=n ; i++ )
    {
        if( !vis[i] && g[i].size() )
        {
            f = 1;
            //cout << i << "\n";
        }
        if( g[i].size()%2 != 0 )
        {
            f = 1;
            //cout << i << "\n";
        }
    }
    if( f ) return cout << "IMPOSSIBLE\n",0;
    eular_dfs(1,0);
    for(int i=(int)(eular.size())-1 ; i>=0 ; i-- )
        cout << eular[i] << " ";
}