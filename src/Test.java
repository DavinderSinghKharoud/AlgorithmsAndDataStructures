// JAVA program to find 
// maximum prefix sum 
class Test
{

    // two store two values in one node
    static class Node
    {
        int sum;
        int prefix;
    };

    static Node []tree = new Node[4 * 10000];
    static
    {
        for(int i = 0; i < tree.length; i++)
            tree[i] = new Node();
    }

    // function to build the segment tree
    static void build(int a[], int index, int beg, int end)
    {
        if (beg == end)
        {

            // If there is one element in array,
            // store it in current node of
            // segment tree
            tree[index].sum = a[beg];
            tree[index].prefix = a[beg];
        } else {
            int mid = (beg + end) / 2;

            // If there are more than one elements,
            // then recur for left and right subtrees
            build(a, 2 * index + 1, beg, mid);
            build(a, 2 * index + 2, mid + 1, end);

            // adds the sum and stores in the index
            // position of segment tree
            tree[index].sum = tree[2 * index + 1].sum +
                    tree[2 * index + 2].sum;

            // stores the max of prefix-sum either
            // from right or from left.
            tree[index].prefix = Math.max(tree[2 * index + 1].prefix,
                    tree[2 * index + 1].sum +
                            tree[2 * index + 2].prefix);
        }
    }

    // function to do the range query in the segment
// tree for the maximum prefix sum 
    static Node query(int index, int beg, int end, int l, int r)
    {
        Node result = new Node();
        result.sum = result.prefix = -1;

        // If segment of this node is outside the given
        // range, then return the minimum value.
        if (beg > r || end < l)
            return result;

        // If segment of this node is a part of given
        // range, then return the node of the segment
        if (beg >= l && end <= r)
            return tree[index];

        int mid = (beg + end) / 2;

        // if left segment of this node falls out of
        // range, then recur in the right side of
        // the tree
        if (l > mid)
            return query(2 * index + 2, mid + 1, end,
                    l, r);

        // if right segment of this node falls out of
        // range, then recur in the left side of
        // the tree
        if (r <= mid)
            return query(2 * index + 1, beg, mid,
                    l, r);

        // If a part of this segment overlaps with
        // the given range
        Node left = query(2 * index + 1, beg, mid,
                l, r);
        Node right = query(2 * index + 2, mid + 1,
                end, l, r);

        // adds the sum of the left and right
        // segment
        result.sum = left.sum + right.sum;

        // stores the max of prefix-sum
        result.prefix = Math.max(left.prefix, left.sum +
                right.prefix);

        // returns the value
        return result;
    }

    // Driver code
    public static void main(String[] args)
    {

        int a[] = { -1};

        // calculates the length of array
        int n = a.length;

        // calls the build function to build
        // the segment tree
        build(a, 0, 0, n - 1);

        // find the max prefix-sum between
        // 3rd and 5th index of array
        System.out.print(query(0, 0, n - 1, 1, 1).prefix
                +"\n");

    }
}

// This code is contributed by PrinciRaj1992 

