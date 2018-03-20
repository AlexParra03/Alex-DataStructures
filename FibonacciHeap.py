"""
@author: David A. Parra
"""

class FibonacciHeap:

    _slots_ = ['min', 'n', 'root']
    def __init__(self):
        self.min = None
        self.n = 0
        self.root = None


    class Node:
        """ Inner class/struct used as a node """
        _slots_ = ['key', 'p', 'child', 'left', 'right', 'degree', 'mark']

        def __init__(self, key, data):
            # Value to be compared against
            self.key = key
            #Extra data mapped to the node (Ex. predecessor vertex in dijkstra algorithm, or vertex id in prim MST)
            self.data = data
            self.p = None
            self.left = None
            self.right = None
            self.child = None
            self.degree = 0
            self.mark = False

    def __len__(self):
        return self.n

    def _for_each(self, node):
        """ Creates iterable of a circular linked list based on the node passed in """
        start = node
        #saving the current state for iteration, prevents errors if the state changes while iterating
        state = [node]
        while node.right != start:
            node = node.right
            state.append(node)

        for node in state:
            yield node
        raise StopIteration

    def _add_to_root_list(self,new_node):
        """ Adds a node to the root linked list """
        if self.root is None:
            self.root = new_node.left = new_node.right = new_node
        else:
            new_node.left = self.root.left
            new_node.right = self.root
            self.root.left.right = new_node
            self.root.left = new_node

    # merges two linked list using nodes
    def _join_linked_list(node_one, node_two):
        """ Takes two nodes from different linked lists and joins their linked lists together"""
        hold = node_one.right
        node_one.right = node_two.right
        node_two.right.left = node_one
        hold.left = node_two
        node_two.right = hold



    def print_root(self):
        """ Iterates and prints keys (or values) in the root """
        print(" - ", end="")
        for node in self._for_each(self.root):
            print(node.key, end=" - ")
        print()

    def insert(self,key, data=None):
        """ Adds a element to the Fibonacci Heap. Key needs to be comparable
            Returns the node added, pass this as reference when decreasing key
        """
        node = self.Node(key,data);
        if self.root is None:
            self.min = node
        else:
            if self.min.key > node.key:
                self.min = node
        self._add_to_root_list(node)
        self.n += 1
        return node

    def union(self, second_heap):
        """ Joins a second Fibonacci Heap to the Heap that called the method, also returns itself when the process is done """
        #Both Heaps are not empty
        if self.min is not None and second_heap.min is not None:
            if self.min.key > second_heap.min.key:
                self.min = second_heap.min
            FibonacciHeap._join_linked_list(self.root, second_heap.root)

        elif self.min is None:
            self.min = second_heap.min

        self.n += second_heap.n
        del second_heap
        return self

    def extract_min(self):
        """ Removes and returns the minimum node from Fibonacci heap """
        z = self.min
        if z is not None:
            if z.child is not None:
                # Add each child to root list
                for node in self._for_each(z.child):
                    self._add_to_root_list(node)
                    node.p = None
            self._remove_from_root(z)
            if z.right == z:
                self.min = None
            else:
                self.min = z.right
                self._consolidate()
            self.n -= 1

        class Public_Node:
            __slots__ = ["key", "data"]
            def __init__(key, data):
                self.key = key
                self.data = data
        return z

    def _consolidate(self):
        """ Merges trees of same degree together """
        A = [None for i in range(self.n)]
        for w in self._for_each(self.root):
            x = w
            d = x.degree
            while A[d] is not None:
                y = A[d]
                if x.key > y.key:
                    x, y = y, x
                self._fib_heap_link(y, x)
                A[d] = None
                d += 1
            A[d] = x
        self.min = None
        for node in A:
            if node is not None:
                if self.min is None:
                    self.min = node
                    self.root = node.left = node.right = node
                else:
                    self._add_to_root_list(node)
                    if node.key < self.min.key:
                        self.min = node


    def _fib_heap_link(self, y, x):
        """ Removes node from root list and appends it as a child node"""
        self._remove_from_root(y)
        self._add_child(x, y)
        y.mark = False

    def _remove_from_root(self, node):
        """ Removes node from root list"""
        if node == self.root:
            if node.left == node:
                self.root = None
                del node
                return
            self.root = node.right
        node.left.right = node.right
        node.right.left = node.left

    def _add_child(self, parent, child):
        """ Adds a node as a child node """
        parent.degree += 1
        child.p = parent
        if parent.child is None:
            parent.child = child.left = child.right = child
        else:
            child.left = parent.child
            child.right = parent.child.right
            parent.child.right.left = child
            parent.child.right = child


    def decrease_key(self, x, k):
        """ Decreases the key of target node"""
        if k > x.key:
            raise Exception('New key is greater than current key')
        x.key = k
        y = x.p
        if y is not None and x.key < y.key:
            self._cut(x,y)
            self._cascading_cut(y)
        if x.key < self.min.key:
            self.min = x

    def peek_min(self):
        return self.min

    def _cut(self, x, y):
        """ Removes a node from the child linked list of a target node """
        # Remove from x child list of y
        if y is None:
            return
        if y.child == y.child.right:
            y.child = None
        elif y.child == x:
            y.child = y.child.right
            y.child.p = y
        x.left.right = x.right
        x.right.left = x.left
        y.degree -= 1
        self._add_to_root_list(x)
        x.p = None
        x.mark = False

    def _cascading_cut(self, y):
        """ Recursive function that prevents unbalanced trees"""
        if y is None:
            return
        z = y.p
        if z is not None:
            if not y.mark:  # y.mark == False
                y.mark = True
        else:
            self._cut(y, z)
            self._cascading_cut(z)

