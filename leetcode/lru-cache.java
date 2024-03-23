class LRUCache {

    private int capacity;
    private LinkedList<Integer> list;
    private HashMap<Integer,Integer> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.list = new LinkedList<>();
        this.map = new HashMap<>();
    }
    
    public int get(int key) {
        if(map.containsKey(key)){
            list.remove(Integer.valueOf(key));
            list.addFirst(key);
            return map.get(key);
        }else{
            return -1;
        }
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
            list.remove(Integer.valueOf(key)); // Integer로 넣으면 int로 인식한다.
        }else if(map.size() >= this.capacity){
            int oldestKey = list.removeLast();
            map.remove(oldestKey);
        }
        list.addFirst(key);
        map.put(key,value);
    }
}