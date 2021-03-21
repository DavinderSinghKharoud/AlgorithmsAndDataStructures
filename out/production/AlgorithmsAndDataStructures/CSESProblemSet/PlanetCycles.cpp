//g++  5.4.0

//topic : strongly connected component(scc)

#include<bits/stdc++.h>
using namespace std;

#define int long long
#define pb push_back
#define fast ios::sync_with_stdio(0) , cin.tie(0) , cout.tie(0) ;

const int nax = 2e5+10;
vector<int> g[nax] , rg[nax] , vis1(nax,0) , vis2(nax,0) , val(nax) , dp(nax,0) , vis3(nax,0) , gr(nax) ;
int grp_no = 0 , n , sum = 0;
stack<int> s ;

void dfs_fwd(int v)
{
    vis1[v] = 1;
    for(auto &i : g[v] )
        if( !vis1[i] )
            dfs_fwd(i);
    s.push(v);
    return ;
}
void dfs_rev(int v)
{
    vis2[ v ] = 1;
    gr[ v ] = grp_no;
    sum++;
    for(auto &i : rg[v] )
        if( !vis2[i] )
            dfs_rev(i);
    return ;
}
void scc()
{
    for(int i=1 ; i<=n ; i++ )
        if( !vis1[i] ) dfs_fwd(i);
    while( !s.empty() )
    {
        if( !vis2[s.top()] )
        {
            sum  = 0;
            grp_no++;
            dfs_rev(s.top());
            dp[ grp_no ] = sum;
        }
        s.pop();
    }
    return ;
}

int work(int v)
{
    if( vis3[v] || dp[ gr[v] ] != 1 || val[v] == v )
        return dp[ gr[v] ] ;
    vis3[ v ] = 1;
    return dp[ gr[v] ] = 1 + work( val[v] );
}
signed main()
{
    fast;
    cin >> n;
    for(int i=1 ; i<=n ; i++ )
    {
        cin >> val[i];
        g[i].pb(val[i]);
        rg[val[i]].pb(i);
    }
    scc();
    for(int i=1 ; i<=n ; i++ )
    {
        if( dp[ gr[i] ] != 1 )
            cout << dp[ gr[i] ] << " " ;
        else
        {
            if( val[i] == i || vis3[i] )
                cout << dp[ gr[i] ] << " " ;
            else
                cout << work( i ) << " " ;
        }
    }

}