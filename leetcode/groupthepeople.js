/**
 * @param {number[]} groupSizes
 * @return {number[][]}
 */
var groupThePeople = function(groupSizes) {
    let index = [];
    
    for(let i =0;i<groupSizes.length;i++){
        index.push({ size : groupSizes[i],
                     index : i });
    }

    index = index.sort((a,b)=>{
     return a.size - b.size;   
    });
    
    // console.log(index);
    
    let ans = [];
    let memo = [];
    let cnt = 1;
    
    index.forEach((param)=>{
        memo.push(param.index);
            
        if(param.size === cnt){
            ans.push(memo);
            cnt = 1;
            memo = [];
        }else{               
            cnt++;
        }                    
    });
    
    // console.log(ans);
    return ans;
};