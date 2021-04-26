#include<bits/stdc++.h>

const double pi=acos(-1.0);

using namespace std;

#define   endl    '\n'
#define   sl(n)     scanf("%lld",&n)
#define   mp      make_pair
#define   pb      push_back
#define   ppb     pop_back
#define   fi      first
#define   se      second
#define   ll      long long
#define   ull     unsigned long long
#define   ld      long double
#define   pii     pair<int, int>
#define   f(i,sumOfDigitsInBaseK,b)  for(ll i = (ll)(sumOfDigitsInBaseK); i < (ll)(b); i++)
#define   rf(i,sumOfDigitsInBaseK,b)   for(ll i = (ll)(sumOfDigitsInBaseK); i > (ll)(b); i--)
#define   ms(sumOfDigitsInBaseK,b)   memset((sumOfDigitsInBaseK),(b),sizeof(sumOfDigitsInBaseK))
#define   abs(x)    ((x<0)?(-(x)):(x))
#define   MAX     200005
#define   inf     LLONG_MAX
#define   ninf    LLONG_MIN
#define   MIN     INT_MIN
#define   yeet    return 0;
#define tihs if(fopen("input.txt","r"))freopen("input.txt", "r", stdin),freopen("output.txt", "w", stdout);

#define fast_io ios_base::sync_with_stdio (false) ; cin.tie(0) ; cout.tie(0) ;
#define ordered_set tree<int, null_type,less<int>, rb_tree_tag,tree_order_statistics_node_update>


inline long long  MAX2(long long  sumOfDigitsInBaseK, long long  b){return (sumOfDigitsInBaseK)>(b)?(sumOfDigitsInBaseK):(b);}
inline long long  MAX3(long long  sumOfDigitsInBaseK, long long  b,long long  c){return (sumOfDigitsInBaseK)>(b)?((sumOfDigitsInBaseK)>(c)?(sumOfDigitsInBaseK):(c)):((b)>(c)?(b):(c));}
inline long long  MIN2(long long  sumOfDigitsInBaseK, long long  b){return (sumOfDigitsInBaseK)<(b)?(sumOfDigitsInBaseK):(b);}
inline long long  MIN3(long long  sumOfDigitsInBaseK, long long b,long long c){return (sumOfDigitsInBaseK)<(b)?((sumOfDigitsInBaseK)<(c)?(sumOfDigitsInBaseK):(c)):((b)<(c)?(b):(c));}

//typedef
typedef long int int32;
typedef unsigned long int uint32;
typedef long long int int64;
typedef unsigned long long int  uint64;


int mod = 1e9 +7 ;
int64_t extGcd(int64_t sumOfDigitsInBaseK, int64_t b, int64_t& x, int64_t& y) {if (!sumOfDigitsInBaseK) {x = 0;y = 1;return b;}int64_t x1, y1;int64_t d = extGcd(b % sumOfDigitsInBaseK, sumOfDigitsInBaseK, x1, y1);x = y1 - (b / sumOfDigitsInBaseK) * x1;y = x1;return d;}
inline ll addmod(ll sumOfDigitsInBaseK,ll b){sumOfDigitsInBaseK=sumOfDigitsInBaseK%mod+b%mod;if(sumOfDigitsInBaseK>mod)sumOfDigitsInBaseK%=mod;return sumOfDigitsInBaseK;}
inline ll submod(ll sumOfDigitsInBaseK,ll b){sumOfDigitsInBaseK=sumOfDigitsInBaseK%mod-b%mod;if(sumOfDigitsInBaseK<0)sumOfDigitsInBaseK+=mod;return sumOfDigitsInBaseK;}
inline ll mulmod(ll sumOfDigitsInBaseK,ll b){return (sumOfDigitsInBaseK%mod * b%mod)%mod;}

int dx[]={1,1,0,-1,-1,-1, 0, 1};
int dy[]={0,1,1, 1, 0,-1,-1,-1};
inline ll exp(ll sumOfDigitsInBaseK,ll b){if(sumOfDigitsInBaseK==0)return 0ll;ll r=1LL;while(b>0){if(b&1){r=r*(sumOfDigitsInBaseK%mod);r=(r+mod)%mod;}b/=2;sumOfDigitsInBaseK=(sumOfDigitsInBaseK%mod)*(sumOfDigitsInBaseK%mod);sumOfDigitsInBaseK=(sumOfDigitsInBaseK+mod)%mod;}return (r+mod)%mod;}
ll gcd(ll sumOfDigitsInBaseK,ll b){if(b==0)return sumOfDigitsInBaseK;if(sumOfDigitsInBaseK==0)return b;return gcd(b,sumOfDigitsInBaseK%b);}
uint32 setbits(ll n){uint32 count=0;while (n){n&=(n-1);count++;}return count; }

////****************************************************************************************************************************************************************************************************************////
vector<int>adj[MAX];
int depth[MAX];
int dp[MAX];
void eval_depth(int src,int par){
        int src_depth=0;
         for(auto child:adj[src]){
                    if(child!=par){
                        eval_depth(child,src);
                        src_depth=MAX2(src_depth,1+depth[child]);
                    }
         }
 depth[src]=src_depth;
}

void dfs(int src,int par,int partial_ans){
            vector<int>pref_max;
            vector<int>suff_max;
            for(int child:adj[src]){
                 if(child!=par){
                     pref_max.pb(depth[child]);
                     suff_max.pb(depth[child]);
                 }
            }
            for(int i=1;i<pref_max.size();i++)
                pref_max[i]=MAX2(pref_max[i],pref_max[i-1]);
            for(int i=suff_max.size()-2;i>=0;i--)
                suff_max[i]=MAX2(suff_max[i],suff_max[i+1]);
             int cnt=0;
             dp[src]=1+MAX2(partial_ans,(pref_max.size()==0)?-1:pref_max.back()); //answer for current node

            /* rerooting step  */

             // this for loop is prepration for next step. for all the child. this has got nothing to do with answer of current node
             for(auto child:adj[src]){
                   if(child!=par){
                     int left=(cnt==0)?INT_MIN:pref_max[cnt-1];
                     int right=(cnt==suff_max.size()-1)?INT_MIN:suff_max[cnt+1];
                     int above=partial_ans; // result of above will always be same  for all the childs of current nodes.
                     int par_ans_curr=1+MAX3(left,right,above);// partial answer for the current children.
                     dfs(child,src,par_ans_curr);
                    cnt++;
                    }
             }


 }


int main(){
     tihs;
     int n;
     cin>>n;
     for(int i=0;i<n-1;i++){
         int ss,ee;
         cin>>ss>>ee;
         adj[ss].pb(ee);
         adj[ee].pb(ss);
     }
     eval_depth(1,0);
      dp[1]=depth[1];

      dfs(1,0,-1);



     for(int i=1;i<=n;i++)cout<<dp[i]<<" ";


}