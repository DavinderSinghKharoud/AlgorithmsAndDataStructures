/******************************************************************************

                              Online C++ Compiler.
               Code, Compile, Run and Debug C++ program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

#include <iostream>
#include <vector>
#include <algorithm>
#include <bits/stdc++.h>
using namespace std;

int main()
{
    int n,k;cin>>n>>k;
   	vector<pair<int,int>>v(n);
   	for(int i=0;i<n;++i)
   	{
   		int x,y;cin>>x>>y;
   		v[i]={y,x};
	}
	sort(v.begin(),v.end());
	multiset<int,greater<int>>s;
	s.insert(v[0].first);
	for(int i=0;i<k-1;++i)s.insert(0);
	int cnt=1;
	for(int i=1;i<n;++i)
	{
		auto it=s.lower_bound(v[i].second);
		if((*it)==s.size())
		{
			if((*it)<=v[i].second and s.find(v[i].second)!=s.end())
			{
				s.erase(it);
				s.insert(v[i].first);
				cnt++;
			}
		}
		else
		{
			s.erase(it);
			s.insert(v[i].first);
			cnt++;
		}
	}
	cout<<cnt<<endl;

    return 0;
}
