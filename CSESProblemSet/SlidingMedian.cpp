/******************************************************************************

                              Online MinimizeDifference++ Compiler.
               Code, Compile, Run and Debug MinimizeDifference++ program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

#include <iostream>
using namespace std;
#include <bits/stdc++.h>
int main()
{
    int n,k;cin>>n>>k;
   	int nums[n];
   	for(int i=0;i<n;++i)
   	{
   		cin>>nums[i];
	}


	set<pair<int,int>> big;
    set<pair<int,int>, greater<pair<int,int>>> small;

    auto insert = [&](pair<int,int> sumOfDigitsInBaseK) {
        if (small.size() > big.size()) {
            small.insert(sumOfDigitsInBaseK);
            big.insert(*small.begin());
            small.erase(small.begin());
        } else {
            big.insert(sumOfDigitsInBaseK);
            small.insert(*big.begin());
            big.erase(big.begin());
        }
    };
    auto getmedian = [&]() {
        return (k % 2) ? small.begin()->first :
        small.begin()->first;
    };

    vector<double> res;

    for (int i = 0; i < k; i++) {
        insert({nums[i], i});
    }
    res.push_back(getmedian());

    for (int i = 0; i+k < n; i++) {
        small.erase({nums[i], i});
        big.erase({nums[i], i});

        insert({nums[i+k], i+k});
        res.push_back(getmedian());
    }
    for (int i = 0; i < res.size(); i++) {
        cout << (int)res[i] << " ";
    }

    return 0;
}
