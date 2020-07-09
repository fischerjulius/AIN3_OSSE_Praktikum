package de.hfu;

public class Queue {
    int[] queue;
    int head;
    int tail;
    private final int maxqueuelength;

    public Queue(int max){
        if (max < 1) throw new IllegalArgumentException("Queue Arraylength " + max);
        maxqueuelength = max;
        queue = new int[max];
        head = 0;
        tail = -1;
    }

    public void enqueue(int a){
        if((tail - head) < maxqueuelength -1 ){
            tail++;
        }
        queue[tail % maxqueuelength] = a;
    }

    public int dequeue(){
        int temp;
        if(tail < head){
            throw new IllegalStateException("dequeue on empty queue");
        }
        temp =queue[(head) % maxqueuelength];
        queue[(head) % maxqueuelength] = 0;
        head++;
        return temp;
    }

}