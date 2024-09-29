/*432. All O`one Data Structure
Design a data structure to store the strings' count with the ability to return the strings with minimum and maximum counts.

Implement the AllOne class:

AllOne() Initializes the object of the data structure.
inc(String key) Increments the count of the string key by 1. If key does not exist in the data structure, insert it with count 1.
dec(String key) Decrements the count of the string key by 1. If the count of key is 0 after the decrement, remove it from the data structure. It is guaranteed that key exists in the data structure before the decrement.
getMaxKey() Returns one of the keys with the maximal count. If no element exists, return an empty string "".
getMinKey() Returns one of the keys with the minimum count. If no element exists, return an empty string "".
Note that each function must run in O(1) average time complexity.

Example 1:
Input
["AllOne", "inc", "inc", "getMaxKey", "getMinKey", "inc", "getMaxKey", "getMinKey"]
[[], ["hello"], ["hello"], [], [], ["leet"], [], []]
Output
[null, null, null, "hello", "hello", null, "hello", "leet"]

Explanation
AllOne allOne = new AllOne();
allOne.inc("hello");
allOne.inc("hello");
allOne.getMaxKey(); // return "hello"
allOne.getMinKey(); // return "hello"
allOne.inc("leet");
allOne.getMaxKey(); // return "hello"
allOne.getMinKey(); // return "leet"
 
Constraints:
1 <= key.length <= 10
key consists of lowercase English letters.
It is guaranteed that for each call to dec, key is existing in the data structure.
At most 5 * 104 calls will be made to inc, dec, getMaxKey, and getMinKey.*/


class Node {
    Node next;
    Node prev;
    int freq;
    HashSet<String> keys;
    Node(int f){
        next = null;
        prev = null;
        freq = f;
        keys = new HashSet<>();
    }
}
class AllOne {
    HashMap<String, Node> map;
    Node head;
    Node tail;
    public AllOne() {
        head = new Node(0);
        tail = new Node(0);
        map = new HashMap<>();
        head.next = tail;
        tail.prev = head;
    }
    
    public void inc(String key) {
        Node cur = head;
        int newFreq = 1;
        if(map.containsKey(key)){// new node added
            cur = map.get(key);
            newFreq = cur.freq + 1;
            cur.keys.remove(key); //removing the previous node
        }
        if(cur.next.freq == newFreq){
            cur.next.keys.add(key);
        }else{ // insert a node with newFreq
            Node node = new Node(newFreq);
            node.keys.add(key);
            Node nextNode = cur.next;
            node.next = nextNode;
            nextNode.prev = node;
            cur.next = node;
            node.prev = cur;
        }
        map.put(key, cur.next);
        if(cur.keys.size()==0 && cur!=head){
            removeNode(cur);
        }
    }
    
    public void dec(String key) {
        Node cur = map.get(key);
        int newFreq = cur.freq - 1;
        cur.keys.remove(key);
        if(newFreq == 0){ //if no value in cur node remove it then
            if(cur.keys.size()==0){
                removeNode(cur);
            }
            map.remove(key);
            return;
        }
    
        if(cur.prev.freq == newFreq){
            cur.prev.keys.add(key);
        }else{ // insert a node with newFreq
            Node node = new Node(newFreq);
            node.keys.add(key);
            Node prevNode = cur.prev;
            node.prev = prevNode;
            prevNode.next = node;
            node.next = cur;
            cur.prev = node;
        }
        map.put(key, cur.prev);
        if(cur.keys.size()==0 && cur!=head){
            removeNode(cur);
        }
    }
    
    public String getMaxKey() {
        if(tail.prev == head){
            return "";
        }
        return tail.prev.keys.iterator().next();
    }
    
    public String getMinKey() {
        if(head.next == tail){
            return "";
        }
        return head.next.keys.iterator().next();
    }
    private void removeNode(Node cur){
        Node nextNode = cur.next;
        Node prevNode = cur.prev;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        cur.next = null;
        cur.prev = null;
    }
}
