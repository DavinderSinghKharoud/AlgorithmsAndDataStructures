
//g++  5.4.0
//topic : strongly connected component(scc)
#include<bits/stdc++.h>
using namespace std;

#define int long long
#define pb push_back
#define fast ios::sync_with_stdio(0) , cin.tie(0) , cout.tie(0) ;

const int nax = 2e5+10;
const int LN = 20;
vector<int> g[nax] , rg[nax] , cycle[nax] , pig(nax), vis1(nax,0) , vis2(nax,0) , scc_tree[nax] , indeg(nax,0) , gr(nax) , lev(nax,0);
int par[LN][nax];
int grp_no = 0 , n , q ;
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
    vis2[v] = 1;
    gr[ v ] = grp_no;
    pig[ v ] = cycle[ grp_no ].size();
    cycle[ grp_no ].pb( v );
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
            grp_no++;
            dfs_rev(s.top());
        }
        s.pop();
    }
    return ;
}

void dfs( int v,int p )
{
    lev[v] = lev[p] + 1;
    par[0][v] = p;
    for( auto &i : scc_tree[v] )
            dfs( i,v );
    return ;
}
int go(int u,int diff)
{
    for(int i = 0; i < LN; i++)
        if((1<<i) & diff)
            u = par[i][u];
    return u;
}


signed main()
{
    fast;
    cin >> n >> q;
    //build-up part
    for(int i=1 ; i<=n ; i++ )
    {
        int x;
        cin >> x;
        g[i].pb(x);
        rg[x].pb(i);
    }
    scc();
    for(int i=1 ; i<=n ; i++ )
    {
        for( auto &j : g[i] )
        {
            if( gr[j] != gr[i] )
            {
                scc_tree[ gr[j] ].pb( gr[i] );
                indeg[ gr[i] ]++;
            }
        }
    }
    for(int j=1 ; j<= grp_no ; j++ )
        if( !indeg[j] ) scc_tree[ grp_no+1 ] .pb( j );
    dfs( grp_no+1,0 );
    for(int i=1 ; i < LN ; i++ )
    {
        for(int j=1 ; j<= grp_no ; j++ )
        {
            if(par[i-1][j] != 0)
                par[i][j] = par[i-1][par[i-1][j]];
        }
    }

    // query-part
    while( q-- )
    {
        int u,v;
        cin >> u >> v;
        if( u==v )
        {
            cout << "0\n";
            continue;
        }
        if( gr[u] == gr[v] )
        {
            if( pig[v] < pig[u] ) cout << pig[u]-pig[v] << "\n";
            else cout << cycle[ gr[v] ].size()-(pig[v] - pig[u]) << "\n";
        }
        else
        {
            if( lev[gr[u]] < lev[gr[v]] ) cout << "-1\n";
            else
            {

                int diff = lev[ gr[u] ] - lev[ gr[v] ];
                if( !diff ) cout << "-1\n";
                else
                {
                    int just = go( gr[u] , diff-1 );
                    int psudo_v  = go( gr[u] , diff );
                    if( psudo_v != gr[v] ) cout << "-1\n";
                    else
                    {

                        int gn = g[ cycle[just][0] ][0];
                       // cout << gn << "\n";
                        int ans = diff;
                        if( gn != v )
                        {
                            if( pig[ v ] < pig[ gn ] ) ans += pig[gn]-pig[v] ;
                            else ans += (cycle[ gr[v] ].size()-(pig[v]- pig[gn])) ;
                        }
                        cout << ans << "\n";
                    }
                }

            }
        }
    }

}