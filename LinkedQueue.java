import java.util.EmptyStackException;

public class LinkedQueue <T extends Comparable<T> & Updatable> implements QueueInterface<T>
{
    private Node firstNode;
    private Node lastNode;
    private int size;

    public LinkedQueue()
    {
        firstNode = null;
        lastNode = null;
    }

    @Override
    public void enqueue(T newEntry)
    {
        Node newNode = new Node(newEntry, null);
        if (isEmpty())
            firstNode = newNode;
        else
            lastNode.setNextNode(newNode);
        lastNode = newNode;
        size++;
    }

    @Override
    public T dequeue()
    {
        if (isEmpty())
            throw new EmptyStackException();
        T data = firstNode.data;
        firstNode = firstNode.next;
        size--;
        return data;
    }

    @Override
    public T getFront()
    {
        if (isEmpty())
            throw new EmptyQueueException();
        else
            return firstNode.getData();
    }

    @Override
    public boolean isEmpty()
    {
        return firstNode == null;
    }

    @Override
    public void clear()
    {
        firstNode = lastNode = null;
        size = 0;
    }

    @Override
    public int getNumItems()
    {
        return size;
    }

    @Override
    public void updateValues()
    {
        for(Node iterator = firstNode; iterator != null; iterator = iterator.next)
        {
            iterator.getData().update();
        }
    }

    //Check to see if the queue is currently in use: airplane object still has landing time to decrement.
    //Return true is queue is in use, return false if not.
    public boolean inUse()
    {
        T ref = null;
        Node node = new Node(ref, null);
        for(Node iterator = firstNode; iterator != null; iterator = iterator.next)
        {
            return iterator.getData().compareTo(ref) > 0;
        }
        return false;
    }

    private class Node
    {
        private T data;
        private Node next;

        private Node(T data, Node next)
        {
            this.data = data;
            this.next = next;
        }

        private T getData()
        {
            return data;
        }

        private Node getNextNode()
        {
            return next;
        }

        private void setNextNode(Node next)
        {
            this.next = next;
        }
    }
}
