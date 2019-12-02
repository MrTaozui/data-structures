package algorithm.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author taojj .
 * 马踏棋盘算法，骑士周游问题
 * <p>
 * 用深度优先遍历的方式，递归回溯
 */
public class HorseChessboard {
    private static boolean[] isVisited;//是否已经访问
    private static int X;//棋盘行坐标(列数)
    private static int Y;//棋盘列坐标(行数)
    private static boolean finished;//是否已经完成
    private static int[][] chessboard;//棋盘  记录走的痕迹


    public static void main(String[] args) {
        System.out.println("骑士周游算法，开始运行~~");
        //测试骑士周游算法是否正确
        X = 8;
        Y = 8;
        int row = 1; //马儿初始位置的行，从1开始编号
        int column = 1; //马儿初始位置的列，从1开始编号
        //创建棋盘
        chessboard = new int[X][Y];
        isVisited = new boolean[X * Y];//初始值都是false
        //测试一下耗时
        long start = System.currentTimeMillis();
        Point startPoint = new Point(0,0);
        run(chessboard, 1,startPoint);
        long end = System.currentTimeMillis();
        System.out.println("共耗时: " + (end - start) + " 毫秒");
        show();

    }


    /**
     * @param chessboard 棋盘
     * @param step       当前步数
     * @param point      当前的马儿所在的点
     */
    public static void run(int[][] chessboard, int step, Point point) {
        isVisited[point.y * X + point.x] = true;
        ArrayList<Point> next = next(point);
        sort(next);//用贪心算法优化
        chessboard[point.y][point.x] = step;
        while (!next.isEmpty()) {
            Point np = next.remove(0);//下一个要走的
            if(!isVisited[np.y * X + np.x]) {
                run(chessboard, step + 1, np);
            }
        }
        //回溯发生，因为会走到最后一步，这里是最后一步先发生，然后 回溯到最上面一层，
        //思考方式倒推:当走到最后一步的时候 才会进到这里，然后回溯，上面的每一层都会进到这里
        if (step < X * Y && !finished) {//走到了所有的步数
            chessboard[point.y][point.x] = 0;//重新置为未走过
            isVisited[point.y * X + point.x] = false;

        } else {
            finished = true;
        }

    }


    /**
     * 功能： 根据当前位置(Point对象)，计算马儿还能走哪些位置(Point)，
     * 同时也是行走的规律，路线
     * 并放入到一个集合中(ArrayList), 最多有8个位置
     *
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint) {
        //创建一个ArrayList
        ArrayList<Point> ps = new ArrayList<Point>();
        //创建一个Point
        Point p1 = new Point();
        //表示马儿可以走5这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走6这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走7这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走0这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走1这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走2这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走3这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        //判断马儿可以走4这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }

    public static void show(){
       for (int[] c:chessboard){
           for (int i = 0; i < c.length; i++) {
               System.out.printf("%5d",c[i]);
           }
           System.out.println();
       }
        
    }

    //根据当前这个一步的所有的下一步的选择位置，进行非递减排序, 减少回溯的次数
    public static void sort(ArrayList<Point> ps) {
        ps.sort(new Comparator<Point>() {

            @Override
            public int compare(Point o1, Point o2) {
                //获取到o1的下一步的所有位置个数
                int count1 = next(o1).size();
                //获取到o2的下一步的所有位置个数
                int count2 = next(o2).size();
                if(count1 < count2) {
                    return -1;
                } else if (count1 == count2) {
                    return 0;
                } else {
                    return 1;
                }
            }

        });
    }
}
