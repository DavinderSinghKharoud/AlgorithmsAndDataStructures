package Templates.EucleadianAlgo_Extended_Included;

public class Eucleadian {
    public static void main(String[] args) {

        GCDS gcd = eucleGcds(80, 55);
        System.out.println(gcd.gcd + " " + gcd.x + " " + gcd.y);
        // gcd = findSolMin(2, -5, 6, 0, 0);
        gcd = findSolMin(14, 19, 143, 0, 0);
        System.out.println(gcd.gcd + " " + gcd.x + " " + gcd.y);
    }

    // A should be bigger than B
    static int[] eucleGcd(int a, int b) {
        int x = 1, y = 0, x1 = 0, y1 = 1, a1 = a, b1 = b;
        while (b1 > 0) {
            int q = a1 / b1;
            int temp1 = x1;
            x1 = x - q * x1;
            x = temp1;
            int temp2 = y1;
            y1 = y - q * y1;
            y = temp2;

            int temp3 = b1;
            b1 = a1 - q * b1;
            a1 = temp3;
        }
        return new int[]{a1, x, y};
    }

    /*
     * Recursive
     **********************************/
    static GCDS eucleGcds(int a, int b) {
        if (b == 0) {
            return new GCDS(1, 0, a);
        }
        GCDS curr = eucleGcds(b, a % b);
        int x = curr.y;
        int y = curr.x - curr.y * (a / b);
        curr.x = x;
        curr.y = y;
        return curr;
    }

    /*******************************************/
    static GCDS findAnySolution(int a, int b, int c) {
        GCDS curr = eucleGcds(Math.abs(a), Math.abs(b));
        if (c % curr.gcd != 0)
            return null;
        curr.x *= (c / curr.gcd);
        curr.y *= (c / curr.gcd);
        if (a < 0)
            curr.x = -curr.x;
        if (b < 0)
            curr.y = -curr.y;
        return curr;
    }

    /**************************************************/
    static void shiftSol(GCDS obj, int a, int b, int count) {
        obj.x += count * b;
        obj.y -= count * a;
    }

    static GCDS findSolMin(int a, int b, int c, int min_x, int min_y) {
        GCDS curr = findAnySolution(a, b, c);
        if (curr == null)
            return null;
        a /= curr.gcd;
        b /= curr.gcd;

        int sign_a = (a > 0) ? 1 : -1;
        int sign_b = (b > 0) ? 1 : -1;

        shiftSol(curr, a, b, (min_x - curr.x) / b);
        if (curr.x < min_x) {
            shiftSol(curr, a, b, sign_b);
        }

        shiftSol(curr, a, b, -(min_y - curr.y) / a);
        if (curr.y < min_y) {
            shiftSol(curr, a, b, -sign_a);
        }
        if (curr.x < min_x || curr.y < min_y)
            return null;
        return curr;
    }

    static int findAllSol(int a, int b, int c, int minx, int maxx, int miny, int maxy) {
        if (a == 0 && b == 0) {
            if (c == 0) return (maxx - minx + 1) * (maxy - miny + 1);
            else return 0;
        }
        GCDS curr = findAnySolution(a, b, c);
        if (curr == null)
            return 0;
        a /= curr.gcd;
        b /= curr.gcd;

        int sign_a = (a > 0) ? 1 : -1;
        int sign_b = (b > 0) ? 1 : -1;

        shiftSol(curr, a, b, (minx - curr.x) / b);
        if (curr.x < minx) {
            shiftSol(curr, a, b, sign_b);
        }
        if (curr.x > maxx)
            return 0;
        int lxl = curr.x;

        shiftSol(curr, a, b, (maxx - curr.x) / b);
        if (curr.x > maxx) {
            shiftSol(curr, a, b, -sign_b);
        }
        int rxl = curr.x;

        shiftSol(curr, a, b, -(miny - curr.y) / a);
        if (curr.y < miny) {
            shiftSol(curr, a, b, -sign_a);
        }
        if (curr.y > maxy)
            return 0;
        int lx2 = curr.x;

        shiftSol(curr, a, b, -(maxy - curr.y) / a);
        if (curr.y > maxy)
            shiftSol(curr, a, b, sign_a);
        int rx2 = curr.x;

        if (lx2 > rx2)
            lx2 = lx2 ^ rx2 ^ (rx2 = lx2);
        int lx = Math.max(lxl, lx2);
        int rx = Math.min(rxl, rx2);
        if (lx > rx)
            return 0;

        return (rx - lx) / Math.abs(b) + 1;
    }

    static class GCDS {
        int x, y, gcd;

        public GCDS(int x, int y, int gcd) {
            this.y = y;
            this.x = x;
            this.gcd = gcd;
        }
    }
}
