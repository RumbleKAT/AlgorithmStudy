/**
 * @param {character[][]} grid
 * @return {number}
 */
var numIslands = function(grid) {
    let cnt = 0;
    const dir = [[0,1],[0,-1],[1,0],[-1,0]];
    const height = grid.length;
    const width = grid[0].length;
    const map = new Array(height).fill().map(()=>new Array(width).fill(0));
    
    const dfs = (y,x,cnt) => {        
        for(const dirTemp of dir){
            let nextY = dirTemp[0] + y;
            let nextX = dirTemp[1] + x;
            
            if(nextY >= 0 && nextY < grid.length && nextX >=0 && nextX < grid[0].length){
                if(grid[nextY][nextX] === '1' && map[nextY][nextX] == 0){
                    map[nextY][nextX] = cnt;
                    dfs(nextY,nextX,cnt);
                }
            }
        }
    }    

    for(let i=0;i<height;i++){
        for(let j=0;j<width;j++){
            if(grid[i][j] === '1' && map[i][j] == 0){
                cnt++;
                map[i][j] = cnt;
                dfs(i,j,cnt)   
            }
        }
    }    
    
    return cnt;
};


