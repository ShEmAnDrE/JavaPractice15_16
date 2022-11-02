package com.company;

import java.lang.reflect.Array;

public class CloseLinkedListForInternetOrder {
    private Node first;
    private Node last;
    private int size;

    CloseLinkedListForInternetOrder() {
    }

    CloseLinkedListForInternetOrder(Item... values) {
        addAll(values);
    }

    public void clear() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public boolean add(Item value) {
        addLast(value);
        return true;
    }

    public void addFirst(Item value) {
        if (value != null) {
            if (this.first == null) {
                this.first = new Node(value);
                this.first.prev = this.first.next = this.first;
                this.last = this.first;
            } else {
                Node node = new Node(this.first, this.last, value);
                this.last.next = this.first.prev = node;
                this.first = node;
            }
            this.size++;
        }
    }

    public void addLast(Item value) {
        if (value != null) {
            if (this.first == null) {
                addFirst(value);
            } else {
                Node node = new Node(this.first, this.last, value);
                this.first.prev = this.last.next = node;
                this.last = node;
                this.size++;
            }
        }
    }
    public boolean addAll(Item... values) {
        boolean result = (values != null);
        if (result) {
            for (Item value : values) {
                result = result && add(value);
            }
        }
        return result;
    }

    public void add(int index, Item value) {
        if (value != null) {
            if (index != 0) {
                checkIndex(index);
            }
            Node node = getNodeByIndex(index);
            if (node == null || node == this.first) {
                addFirst(value);
            } else if (node == this.last) {
                addLast(value);
            } else {
                Node addNode = new Node(node, node.prev, value);
                node.prev = node.prev.next = addNode;
                this.size++;
            }
        }
    }

    public int indexOf(Item value) {
        int index = -1;
        if (value != null && !isEmpty()) {
            if (this.first.value.equals(value)) {
                index = 0;
            } else {
                int count = 1;
                for (Node node = this.first.next; node != this.first; node = node.next) {
                    if (node.value.equals(value)) {
                        index = count;
                        break;
                    } else {
                        count++;
                    }
                }
            }
        }
        return index;
    }

    public boolean contains(Item value) {
        return indexOf(value) != -1;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Incorrect index output...");
        }
    }

    private Node getNodeByIndex(int index) {
        Node result = null;
        if (index == 0) {
            result = this.first;
        } else if (index == this.size - 1) {
            result = this.last;
        } else {
            if (this.size / 2 > index) {
                for (Node node = this.first.next; node != this.first; node = node.next) {
                    if (--index == 0) {
                        result = node;
                        break;
                    }
                }
            } else {
                index = this.size - index - 1;
                for (Node node = this.last.prev; node != this.last; node = node.prev) {
                    if (--index == 0) {
                        result = node;
                        break;
                    }
                }
            }
        }
        return result;
    }

    public double getCostTotal() {
        double sum = 0.0;
        for (Node node = this.first.next; node != this.first; node = node.next) {
            sum += node.value.getCost();
        }
        return sum;
    }

    public int lastIndexOf(Item value) {
        int index = -1;
        if (!isEmpty()) {
            if (this.last.value.equals(value)) {
                index = this.size - 1;
            } else {
                int count = this.size - 2;
                for (Node node = this.last.prev; node != this.last; node = node.prev) {
                    if (node.value.equals(value)) {
                        index = count;
                        break;
                    } else {
                        count--;
                    }
                }
            }
        }
        return index;
    }

    public String[] namesOfItems() {
        String[] names = new String[size];
        int i = 0;
        for (Node node = this.first.next; node != this.first; node = node.next) {
            names[i] = node.value.getName();
            i++;
        }
        return names;
    }

    public int quantityOfItem(String itemName) {
        int count = 0;
        for (Node node = this.first.next; node != this.first; node = node.next) {
            if (node.value.getName().equals(itemName)) {
                count++;
            }
        }
        return count;
    }

    public int lastIndexOfItemName(String itemName) {
        int index = -1;
        if (!isEmpty()) {
            if (this.last.value.getName().equals(itemName)) {
                index = this.size - 1;
            } else {
                int count = this.size - 2;
                for (Node node = this.last.prev; node != this.last; node = node.prev) {
                    if (node.value.getName().equals(itemName)) {
                        index = count;
                        break;
                    } else {
                        count--;
                    }
                }
            }
        }
        return index;
    }

    public Item get(int index) {
        checkIndex(index);
        return getNodeByIndex(index).value;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Item oldElement;
        if (this.size == 1) {
            oldElement = this.first.value;
            clear();
        } else {
            oldElement = this.first.value;
            Node newFirst = this.first.next;
            newFirst.prev = this.last;
            this.last.next = newFirst;
            this.first = newFirst;
            this.size--;
        }
        return oldElement;
    }

    public Item removeLast() {
        if (isEmpty()) {
            return null;
        }
        Item oldElement;
        if (this.size == 1) {
            oldElement = this.first.value;
            clear();
        } else {
            oldElement = this.last.value;
            Node newLast = this.last.prev;
            newLast.next = this.first;
            this.first.prev = newLast;
            this.last = newLast;
            this.size--;
        }
        return oldElement;
    }

    public boolean remove(Item value) {
        boolean result = value != null;
        if (result) {
            result = !isEmpty();
            if (result) {
                result = this.first.value.equals(value);
                if (result) {
                    removeFirst();
                } else {
                    Node delete = null;
                    for (Node node = this.first.next; node != this.first; node = node.next) {
                        if (node.value.equals(value)) {
                            delete = node;
                        }
                    }
                    result = delete != null;
                    if (result) {
                        if (delete == this.last) {
                            removeLast();
                        } else {
                            delete.prev.next = delete.next;
                            delete.next.prev = delete.prev;
                            this.size--;
                        }
                    }
                }
            }
        }
        return result;
    }

    public Item remove(int index) {
        checkIndex(index);
        Item old = null;
        if (index == 0) {
            old = this.first.value;
            removeFirst();
        } else if (index == this.size - 1) {
            old = this.last.value;
            removeLast();
        } else {
            Node node = getNodeByIndex(index);
            if (node != null) {
                old = node.value;
                node.prev.next = node.next;
                node.next.prev = node.prev;
                this.size--;
            }
        }
        return old;
    }

    public Item set(int index, Item value) {
        Item result = null;
        if (value != null) {
            checkIndex(index);
            Node set = getNodeByIndex(index);
            result = set.value;
            set.value = value;
        }
        return result;
    }

    public Item[] toArray() {
        Item[] items = new Item[size];
        int i = 0;
        for (Node node = this.first.next; node != this.first; node = node.next) {
            items[i] = node.value;
            i++;
        }
        return items;
    }

    public Item getFirst() {
        if (this.first == null) {
            return null;
        }
        return this.first.value;
    }

    public Item getLast() {
        if (this.last == null) {
            return null;
        }
        return this.last.value;
    }

    @Override
    public String toString() {
        String result = new String();
        for (Node node = this.first.next; node != this.first; node = node.next) {
            result += node.value;
        }
        return result;
    }

    private class Node {
        private Node next;
        private Node prev;
        private Item value;

        Node(Node next, Node prev, Item value) {
            this.next = next;
            this.prev = prev;
            this.value = value;
        }

        Node(Item value) {
            this(null, null, value);
        }
    }
}
