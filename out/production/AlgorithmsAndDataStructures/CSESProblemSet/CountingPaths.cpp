#include<bits/stdc++.h>
using namespace std;
const int mxN =2e5+2 ;
typedef vector<int> vi;
#define pb push_back
#define For(i,n) for(int i=0;i<n;i++)
vi adj[mxN] ;
int anc[mxN][18],d[mxN],n,m,a[mxN],c[mxN];
void dfs2(int v,int p){
  anc[v][0]=p ;d[v]=d[p]+1 ;
  for(int i=1;i<18;i++)
    anc[v][i]=~anc[v][i-1]?anc[anc[v][i-1]][i-1]:-1 ;
  for(int x:adj[v])
    if(x^p)
      dfs2(x,v) ;
}
int lca(int u,int v){
  if(d[u]<d[v])
    swap(u,v) ;
  for(int i=17;~i;--i)
    if(d[u]-(1<<i)>=d[v])
      u=anc[u][i] ;
  if(u==v)
    return u ;
  for(int i =17;~i;--i){
    if(anc[u][i]^anc[v][i]){
      u=anc[u][i],v=anc[v][i] ;
    }
  }
  return anc[u][0] ;
}
int dfs3(int v,int p){
  c[v]=a[v] ;
  for(int x:adj[v])
    if(x^p)
      c[v]+=dfs3(x,v);
  return c[v] ;
}
signed main() {
  memset(a,0,sizeof(a)) ;
  cin >> n >> m ;
  For(i,n-1){
    int u,v ;
    cin >>  u >> v ;
    adj[v].pb(u) ;adj[u].pb(v) ;
  }
  dfs2(1,0) ;
  vector<int>k(n+1);
  For(i,m){
    int u,v,l;cin >> u >> v ;
    l = lca(u,v) ;
    k[l]++ ;
    ++a[u];++a[v];a[l]-=2 ;
  }
  dfs3(1,0) ;
  for(int i=1;i<=n;i++)
    cout << c[i]+k[i] << " " ;
}