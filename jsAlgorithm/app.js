// var PriorityQueue = require('./PriorityQueue');

// class Node{
//     constructor(value, priority){
//         this.value = value;
//         this.priority = priority;
//     }
// }



// var current = new Node(1,1);
// console.log(PriorityQueue);

// var pq = new PriorityQueue();

// pq.enqueue(new Node(1,1));
// pq.enqueue(new Node(5,2));
// pq.enqueue(new Node(3,0));


// while(!pq.isEmpty()){
//     let current = pq.dequeue();
//     console.log(current);
// }

var arr = new Array(5);

for(let i= 0;i<arr.length;i++){
    arr[i] = new Array(5);
}

console.log(arr)

const arr0 = Array(4).fill(null).map(()=>Array(2));
console.log(arr0);

const arr1 = Array.from(Array(5), ()=> new Array(2));
console.log(arr1);


// app.fill(1,0,5);

// console.log(app);