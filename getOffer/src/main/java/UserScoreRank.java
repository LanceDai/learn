//import com.google.common.collect.TreeRangeMap;
//import com.google.common.collect.TreeRangeSet;
//
//import java.util.*;
//import java.util.concurrent.ConcurrentSkipListSet;
//
///**
// * @author LanceDai
// * @date 2019/3/15 10:29
// * @description 简易排行榜
// */
//
////        玩家分数类：
//class UserScore implements Comparable<UserScore> {
//    public int id;
//    public int scores;
//    public int count;
//
//    /**
//     * 2. 排行榜排序的规则是：
//     * 1) scores高的排前面
//     * 2) scores相同，count少的排前面
//     * 3) scores和count都相同，id小的排前面
//     */
//    @Override
//    public int compareTo(UserScore o) {
//        if (this.scores == o.scores) {
//            if (this.count == o.count) {
//                return this.id < o.id ? -1 : 1;
//            } else {
//                return this.count < o.count ? -1 : 1;
//            }
//        } else {
//            return this.scores > o.scores ? -1 : 1;
//        }
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) {
//            return true;
//        }
//        if (o == null || getClass() != o.getClass()) {
//            return false;
//        }
//
//        UserScore userScore = (UserScore) o;
//
//        if (id != userScore.id) {
//            return false;
//        }
//        if (scores != userScore.scores) {
//            return false;
//        }
//        return count == userScore.count;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id;
//        result = 31 * result + scores;
//        result = 31 * result + count;
//        return result;
//    }
//}
//
////有关于UserScore的排行类:
//public class UserScoreRank {
//    public static final int kThreshold = 10;
//    public static final TreeMap<Integer, UserScore> userScoreRank = new TreeMap<>();
//
//    //根据比赛结果，批量更新玩家信息
//    public int updateUserScoreRank(List<UserScore> src) {
//        Collections.sort(src);
//        int count = 0;
//        for (UserScore userScore : src) {
//            if (userScoreRank.containsValue(userScore)) {
//                userScoreRank.
//                        count += userScoreRank.remove(userScore) != null ? 1 : 0;
//            }
//            //如果成绩不能上排行榜
//            if (userScore.count >= kThreshold) {
//                //如果原来在，删除在更新
//                count += userScoreRank.add(userScore) ? 1 : 0;
//            }
//        }
//        return count;
//    }
//
////    //输入名次的上限和下限 ，给出玩家排行列表（包含上下界)
////    public List<UserScore> getUserScoreRank(int low_bound, int up_bound) {
////return userScoreRank.subMap()
////    }
////
////    //从排行榜删除特定的玩家
////    public int deleteUserRankByIds(List<Integer> ids) {
////    }
////}
//}
//
//
////
////
////排行榜内的数据满足以下条件:
////        1. UserScore中的Count必须要大于kThreshold
////        2. 排行榜排序的规则是：
////        1) scores高的排前面
////        2) scores相同，count少的排前面
////        3) scores和count都相同，id小的排前面
////        注：数组中元素的id不重复。
////
////        请实现 UserScoreRank 类中的方法。
//
//
