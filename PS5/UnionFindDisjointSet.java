public class UnionFindDisjointSet {

    //////////////////////////////////////////////////////
    // Declare any additional variables as you see fit. //
    //////////////////////////////////////////////////////

    // variables added

    public int[] parent;
    public int[] size;
    public int temp, rootU, rootV;

    /**
     * Constructs a new Union-Find Disjoint Set with the specified number of vertices,
     * indexed from 0 to (numVertices - 1).
     *
     * @param numVertices The number of vertices in this Union-Find Disjoint Set
     */
    public UnionFindDisjointSet(int numVertices) {
        //////////////////////////////////
        // Implement your solution here //
        //////////////////////////////////
        parent = new int[numVertices]; // begins from vertex 0, to numVertices-1.
        size = new int[numVertices];
        // this is a constructor to store all nodes and vertices of the MST here!
        for (int n = 0; n < numVertices; n++) {
          parent[n] = n;
          size[n] = 1;
        }

    }

    /**
     * Unions the set containing vertex u with the set containing vertex v. If both
     * vertices u and v are in the same set to begin with, the sets should remain
     * unchanged.
     */
    public void union(int u, int v) {
        //////////////////////////////////
        // Implement your solution here //
        //////////////////////////////////

        // This is a union operation! Literally join them into same set
        // for Weighted Union with Path Compression, need to find root
        // then set all traversed nodes' parent = root!

        // 1 identify true rootU then do path compression
        rootU = u;
        while (parent[rootU] != rootU) {
          // root's parent will be itself
          rootU = parent[rootU];
        }
        while (parent[u] != u) {
          // set all traversed nodes to have parent == root
          temp = parent[u]; // pause traversal
          parent[u] = rootU; // set parent to be root
          u = temp; // continue traversal
        }

        // 2. identify true rootV then do path compression
        rootV = v;
        while (parent[rootV] != rootV) {
          // root's parent will be itself
          rootV = parent[rootV];
        }
        while (parent[v] != v) {
          // set all traversed nodes to have parent == root
          temp = parent[v]; // pause traversal
          parent[v] = rootV; // set parent to be root
          v = temp; // continue traversal
        }
        // 3. actually join them
        if (size[rootU] >= size[rootV]) {
          // u's tree is larger, should be the ultimate root
          parent[rootV] = rootU;
          size[rootU] += size[rootV];
        } else {
          // v's tree is larger, should be ultimate root
          parent[rootU] = rootV;
          size[rootV] += size[rootU];
        }

    }

    /**
     * Checks if vertices u and v belong to the same set.
     *
     * @return True if vertices u and v belong to the same set, false otherwise.
     */
    public boolean isSameSet(int u, int v) {
        //////////////////////////////////
        // Implement your solution here //
        //////////////////////////////////

        // this is a find operation!

        // 1. identify true rootU, do not need path compression
        rootU = u;
        while (parent[rootU] != rootU) {
          // root's parent will be itself
          rootU = parent[rootU];
        }

        // 2. identify true rootV, do not need path compression
        rootV = v;
        while (parent[rootV] != rootV) {
          // root's parent will be itself
          rootV = parent[rootV];
        }

        return (rootU == rootV);
    }
}
