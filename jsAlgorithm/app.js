var PriorityQueue = require('./PriorityQueue');

class Node{
    constructor(value, priority){
        this.value = value;
        this.priority = priority;
    }
}



var current = new Node(1,1);
console.log(PriorityQueue);

var pq = new PriorityQueue();

pq.enqueue(new Node(1,1));
pq.enqueue(new Node(5,2));
pq.enqueue(new Node(3,0));


while(!pq.isEmpty()){
    let current = pq.dequeue();
    console.log(current);
}