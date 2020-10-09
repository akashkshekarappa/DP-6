/*********************************Optimal(DP)******************************/
//Time Complexity : O(n)
//Space Complexity : O(n)
//Did this code successfully run on Leetcode : yes
//Any problem you faced while coding this : none

import java.util.*;
class Ugly_NumberII_DP {
	public int nthUglyNumber(int n) {
		if(n == 1)
			return 1;
		int[] arr = new int[n];
		arr[0] = 1;  //1st ugly number is 1
		int pt2 = 0; int pt3 = 0; int pt5 = 0;
		int n2  = 0; int n3  = 0; int n5  = 0;
		for(int i=1; i<n; i++){
			//for each index from 1 to n
			//find n2 = 2 * arr[pt2], n3 = 3 * arr[pt3], n5 = 5 * arr[pt5]
			//arr[i] = min(n2,n3,n5)
			//if arr[i] = n2 increase pt2 by 1, if arr[i] = n3 increase pt3 by 1 and if arr[i] = n5 increase pt5 by 1
			n2 = arr[pt2] * 2;
			n3 = arr[pt3] * 3;
			n5 = arr[pt5] * 5;
			arr[i] = Math.min(n2, Math.min(n3, n5));
			if(n2 == arr[i]){
				pt2++;
			}
			if(n3 == arr[i]){
				pt3++;
			}
			if(n5 == arr[i]){
				pt5++;
			}
		}
		return arr[n-1];       //return last value
	}
}


/*********************************Using PQ and Hashset******************************/
//Time Complexity : O(nlogn)
//Space Complexity : O(n)
//Did this code successfully run on Leetcode : yes
//Any problem you faced while coding this : none

class Ugly_NumberII_PQ {
	public int nthUglyNumber(int n) {
		if(n == 1)
			return 1;
		HashSet<Long> set = new HashSet<>();    //to maintain the ugly numbers found so far
		PriorityQueue<Long> pq = new PriorityQueue<>(); //by default min heap, to get the next smallest number for which factors has to be found
		long ugly = 1;
		set.add(ugly);  pq.add(ugly);   //add 1 initially to both set and pq
		int count=1;
		int[] primes = new int[] {2, 3, 5};
		while(count <= n){
			ugly = pq.poll();   //get the next ugly number from pq and increment count
			count++;

			//find multiple of ugly & add it to set and pq if they are not already present in set
			for(int prime: primes){
				long newNumber = prime * ugly;
				if(!set.contains(newNumber)){
					set.add(newNumber);
					pq.add(newNumber);
				}
			}
		}
		return Math.toIntExact(ugly);
	}
}
