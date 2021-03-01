/**
 * @param {character[][]} board
 * @return {void} Do not return anything, modify board in-place instead.
 */
var solve = function(board) {
    const dir =[[0,1],[0,-1],[1,0],[-1,0]];
    const width = board[0].length;
    const height = board.length;
    const visited = new Array(height).fill().map(()=> new Array(width).fill(false));
    
    const isSurrounded = (row, column) =>{
        const markedMap = new Array(height).fill().map(() => new Array(width).fill(false));
        const dfs = (r,c) =>{
            if(r < 0 || r >= height || c <0 || c>= width) return false;
            if(markedMap[r][c] || board[r][c] === 'X') return true;
            markedMap[r][c] = true;
            for(const temp of dir){
                if(!dfs(temp[0] + r , temp[1] + c)) return false;
            }
            return true;
        }
        
        return dfs(row, column);
    }
    
    const markBoard = (r,c,shouldMarked) =>{
        if(r< 0 || r>= height || c <0 || c>=width) return;
        if(visited[r][c] || board[r][c] === 'X') return;
        
        visited[r][c] = true;
        
        if(shouldMarked){
            board[r][c] = 'X';
        }
        
        for(const temp of dir){
            markBoard(temp[0] + r, temp[1] + c, shouldMarked);
        }
    }
    
    
    for(let i=0;i<height;i++){
        for(let j=0;j<width;j++){
            if(!visited[i][j] && board[i][j] === 'O'){
                if(isSurrounded(i,j)){
                    markBoard(i,j,true)
                }else{
                    markBoard(i,j,false)
                }
            }
        }
    }

    return board;

  
};

