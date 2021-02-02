export default class PriorityQueue{
    constructor(){
        this.store = [];
    }

    isEmpty(){
        return this.store.length === 0 ? true : false;
    }

    enqueue(item){
        this.store.push(item);
    }

    dequeue(){
        let entry = 0;
        this.store.forEach((item,index)=>{
            if(this.store[entry].score < this.store[index].score){
                entry = index;
            }
        });
        return this.store.splice(entry,1);
    }
}