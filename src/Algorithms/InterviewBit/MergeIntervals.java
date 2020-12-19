package Algorithms.InterviewBit;

import java.util.ArrayList;

public class MergeIntervals {


    private static  ArrayList<Interval> merged(ArrayList<Interval> intervals, Interval newInterval) {

        ArrayList<Interval> res = new ArrayList<>();
        for( Interval interval: intervals ){
            if( newInterval == null || interval.end < newInterval.start){
                res.add(interval);
            }else if( newInterval.end < interval.start ){
                res.add(newInterval);
                newInterval = null;
                res.add(interval);
            }else{
                newInterval.start = Math.min( newInterval.start, interval.start );
                newInterval.end = Math.max( newInterval.end, interval.end );
            }

        }

        //it means already inserted
        if( newInterval == null ) return res;
        res.add(newInterval);
        return res;
    }

    public static void main(String[] args) {
        ArrayList<Interval> arr = new ArrayList<>();
//        arr.add(new Interval(1, 2));
//        arr.add(new Interval(3,5));
//        arr.add(new Interval(6, 7));
//        arr.add(new Interval(8, 10 ));
//        arr.add(new Interval(12, 16));
        arr.add(new Interval(1, 2));
        arr.add(new Interval(3, 6));

        System.out.println( merged( arr, new Interval(10, 8)));
    }


    public static class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }

        @Override
        public String toString() {
            return "Interval{" +
                    "start=" + start +
                    ", end=" + end +
                    '}';
        }
    }
}
