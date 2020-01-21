public class FroshWeek {

  public static long count = 0;
  public static int[] B;

  public static void main(String [] args) {
    Kattio io = new Kattio(System.in, System.out);
    int n = io.getInt();
    int[] A = new int[n];
    for (int i = 0; i < n; i++) {
      A[i] = io.getInt();
    }
    B = new int[n]; // to hold as a copy of A, but for its segments to be updated later during mergeSort
    // mergeSort is an edited method to increase count, ie number of inversions while sorting
    FroshWeek.mergeSort(A, 0, n - 1);
    io.println(count);
    io.flush();
  }

  public static void mergeSort(int[] A, int low, int high) {
    if (low < high) {
      int mid = (low + high)/2;
      FroshWeek.mergeSort(A, low, mid);
      FroshWeek.mergeSort(A, mid + 1, high);
      FroshWeek.merge(A, low, mid, high); // returns inversion count for current, and previous sorts
    }
  }

  public static void merge(int[] A, int low, int mid, int high) {
    // we will increment public static int count for each inversion
    // Using buffer array B to make a deep copy, to refer for inserting later on
    for (int z = low; z <= high; z++) {
      B[z] = A[z];
    }

    // pointers for choosing which parts of the array to take from and place in
    int i = low;
    int j = mid + 1;
    int k = low;
    while (i <= mid && j <= high) {
      if (B[i] <= B[j]) {
        A[k] = B[i];
        i++;
      } else {
        // note we only increase the count counter here as an inversion occurs
        // when there is a pair of elements to swap
        A[k] = B[j];
        j++;
        count += mid - i + 1;
      }
      k++;
    }
    // fill up the remaining
    // note: do not need to do for j <= high due to
    // case 1: j > high so exit initial loop. Left only i <= mid so accept leftovers
    // case 2: i > mid so exit initial loop. What's left is j, which is already sorted and
    // on the higher indices. Hence no need to do shifting of elements.
    while (i <= mid) {
      A[k] = B[i];
      k++;
      i++;
    }
  }
}
