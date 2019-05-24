


/*
    There are two sorted arrays nums1 and nums2 of size m and n respectively.
    Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
    You may assume nums1 and nums2 cannot be both empty.
    Example :
    nums1 = [1]
    nums2 = [20，21]
    The median is 20
    O(log (m+n))
    分治
      *首先在随机位置将A分成两部分：
      *                     left_A | right_A
      * A [0]，A [1]，...，A [i-1] | A [i]，A [i + 1]，...，A [m-1]
      * 由于A有m个元素，所以有m + 1种切割（i = 0〜m）。
      * 其中：left_A.size() = i，right_A.size() = m-i。注意：当i = 0时，left_A为空，当i = m时，right_A为空。
      *
      * 同样，在随机位置将B切成两部分：
      * 其中：left_B.size() = j，right_B.size() = n-j。
      *                     left_B | right_B
      * B [0]，B [1]，...，B [j-1] | B [j]，B [j + 1]，...，B [n-1]
      *
      * 将left_A和left_B放入一个集合，并将right_A和right_B放入另一个集合。把它们命名为left_part和right_part：
      *                  left_part | right_part
      * A [0]，A [1]，...，A [i-1] | A [i]，A [i + 1]，...，A [m-1]
      * B [0]，B [1]，...，B [j-1] | B [j]，B [j + 1]，...，B [n-1]
      *
      * 思路：如果我们可以确保：
      * left_part.size() == right_part.size()
      * max（left_part）<= min（right_part）
      *
      * 那么将{A，B}中的所有元素划分为两个长度相等的部分，right_part总是大于left_part。
      * 若 A.length + B.length 为奇数
      * 将多余的一个数放在右边
      * 中值= min（right_part）。
      *
      * 若 A.length + B.length 为偶数，则左右长度相等
      * 中值=（max（left_part）+ min（right_part））/ 2。
      * 为了确保这两个条件，只需要确保：
      *
      * （1）i + j == m - i + n - j
      *  即    2*j == m + n - 2*i
      *          j == (m + n)/2 - i
      *   (m + n)/2 向下取整，m+n为奇时，保证了j切割偏左，使多余的数放在右边
      *
      * （2）确保 max（left_part）<= min（right_part）：
      *      B [j-1] <= A [i]   -- MaxLeftB <= MinRightA
      *      A [i-1] <= B [j]   -- MaxLeftA <= MinRightB
      *
      *  在a[0:m]中搜索i，找到一个切分点i（j =（m + n）/2-i）：
      *  使得B [j-1] <= A [i] 且 A [i-1] <= B[j]。
      *
      *
      *步骤：
      * <1>设置imin = 0，imax = m，然后开始搜索[imin，imax]
      * <2>设置i =（imin + imax）/ 2，j =（m + n）/ 2-i
      * <3>现在left_part.size() == right_part.size()。而且只有3种情况：
      * (1) B[j-1] <= A [i]和A [i-1] <= B [j]
      * 意味着找到了切分点i，停止搜索。
      * (2) B[j-1]> A [i]
      * 意味着A [i]太小。必须调整i得到B [j-1] <= A [i]。
      * 调整搜索范围为[i + 1，imax]。因此，imin = i + 1和然后回到第<2>步。
      * (3) A [i-1]> B [j]
      * 意味着A [i-1]太大，必须减少i得到A [i-1] <= B [j]。
      * 设置imax = i-1然后回到第<2>步。
      *
      * 当找到切分点i时，中值为：
      * max（A [i-1]，B [j-1]）（当m + n是奇数时）
      * （max（A [i-1]，B [j-1]）+ min（A [i]，B [j]））/ 2（当m + n为偶数时）
      *
      */
public static double findMedianSortedArrays(int[] a, int[] b) {
        if(a.length>b.length)
            return findMedianSortedArrays(b,a);

        int aLeft = 0;
        int aRight = a.length - 1;
        int cutA = 0; // number of elements in left part of A after cutting
        int cutB; // number of elements in left part of B after cutting
        while(cutA<=a.length){
            // left[0 : cut-1] right[cut : length - 1]
            cutA = (aRight - aLeft + 1)/2 + aLeft;
            cutB = (a.length + b.length)/2 - cutA;

            //if left side of A is not empty, maxLeftA = a[cutA-1]
            double maxLeftA = (cutA == 0) ? Integer.MIN_VALUE : a[cutA-1];
            //if right side of A is not empty, minRightA = a[cutA-1]
            double minRightA = (cutA == a.length) ? Integer.MAX_VALUE : a[cutA];
            //if left side of B is not empty, maxLeftB = b[cutB-1]
            double maxLeftB = (cutB == 0) ? Integer.MIN_VALUE : b[cutB-1];
            //if right side of B is not empty, minRightB = b[cutB-1]
            double minRightB = (cutB == b.length) ? Integer.MAX_VALUE : b[cutB];


            if(maxLeftA > minRightB){
                aRight = cutA - 1;
            }else if(maxLeftB > minRightA){
                aLeft = cutA + 1;
            }else{
                if((a.length + b.length)%2==0)
                    return (Math.max(maxLeftA,maxLeftB)+Math.min(minRightA,minRightB))/2;
                else
                    return Math.min(minRightA,minRightB);
            }
        }
        return -1;
    }
