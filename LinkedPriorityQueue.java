public class LinkedPriorityQueue <T extends Comparable<T> & Updatable> implements QueueInterface <T>
{
    private Node firstNode;
    private Node lastNode;
    private Node sorted;
    private int size;

    public LinkedPriorityQueue()
    {
        firstNode = null;
        lastNode = null;
    }

    @Override
    public void enqueue(T newEntry)
    {
        Node newNode = new Node(newEntry, null);
        if (firstNode == null)
        {
            firstNode = newNode;
            newNode.next = null;
            return;
        }

        int compare = newNode.data.compareTo(firstNode.data);
        //Compare is negative or 0, newNode should be placed before firstNode.
        if(compare <= 0)
        {
            newNode.next = firstNode;
            firstNode = newNode;
            return;
        }

        for(Node iterator = firstNode; iterator != null; iterator = iterator.next)
        {
            if(iterator.next == null)
            {
                iterator.next = newNode;
                return;
            }

            Node nextNode = iterator.next;
            if(nextNode.data.compareTo(newNode.data) > 0)
            {
                //newNode inserted here
                iterator.next = newNode;
                newNode.next = nextNode;
                return;
            }
        }
    }

    @Override
    public T dequeue()
    {
        T front = getFront();
        if(front == null)
        {
            throw new EmptyQueueException();
        }
        firstNode.setData(null);
        firstNode = firstNode.getNextNode();
        size--;
        if(firstNode == null)
        {
            lastNode = null;
        }
        return front;
    }

        @Override
    public T getFront()
    {
        if (isEmpty())
            throw new EmptyQueueException();
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
    public void updateValues()
    {
        for(Node iterator = firstNode; iterator != null; iterator = iterator.next)
        {
            iterator.data.update();
        }
    }

    @Override
    public int getNumItems()
    {
        return size;
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

        private void setData(T data) {this.data = data; }

        private void setNextNode(Node next)
        {
            this.next = next;
        }
    }
}
