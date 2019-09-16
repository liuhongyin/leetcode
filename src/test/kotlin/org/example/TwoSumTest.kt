package org.example

import org.junit.Assert
import org.junit.Test
import java.util.*


class TwoSumTest {
    @Test
    fun twoSumTest(){
        val nums: IntArray = intArrayOf(2, 7, 11, 15)
        val target =9
//        println(twoSum(nums,target).toList())
        Assert.assertTrue(twoSum(nums,target) contentEquals intArrayOf(1,0))
    }
    }


//fun twoSum(nums: IntArray, target: Int): IntArray {
//    for (i in 0 until nums.size){
//        for (j in i+1 until nums.size){
//            if (nums[i]==target-nums[j]){
//                return intArrayOf(i,j)
//            }
//        }
//    }
//    throw IllegalArgumentException("No two sum solution")
//}

fun twoSum(nums: IntArray, target: Int): IntArray {
    var map:MutableMap<Int,Int> = mutableMapOf()
    for (i in 0 until nums.size){
        map[nums[i]]=i
    }
    for (i in 0 until nums.size){
        if (map.containsKey(target -nums[i]) && map[target-nums[i]] !=nums[i]){

            return intArrayOf(map[target - nums[i]]!!.toInt(),i)
        }
    }
    throw IllegalArgumentException("No two sum solution")
}

