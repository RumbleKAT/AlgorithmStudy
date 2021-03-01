/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @param {number} k
 * @return {number[][]}
 */
var kSmallestPairs = function(nums1, nums2, k) {

    let a = [];
    let ans = [];
    
    for(const i of nums1){
        for(const j of nums2){
            a.push({
                     arr : [i,j],
                     sum : (i+j)
                   }
                  );
        }
    }

    a.sort(function(t,b){
        if(t.sum > b.sum){
            return 1;
        }else if(t.sum < b.sum){
            return -1;
        }
        return 0;
    }); 
    
    console.log(a);
    
    let count = 0;
    for(const temp of a){
        if(count < k){
            count++;
            ans.push(temp.arr);
        }
    }
    
    return ans;
};