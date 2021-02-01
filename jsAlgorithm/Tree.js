export class Tree{
    constructor(value){
        this.value = value;
        this.left = null;
        this.right = null;
    }

    insert(value){
        if(value <= this.value){
            if(!this.left) this.left = new Tree(value);
            else this.left.insert(value);
        }else{
            if(!this.right) this.right = new Tree(value);
            else this.right.insert(value);
        }
    }

    contains(value){
        if(value === this.value) return true;
        if(value < this.value){
            if(!this.left) return false;
            else return this.left.contains(value);
        }else{
            if(!this.right) return false;
            else return this.right.contains(value);
        }
    }
}