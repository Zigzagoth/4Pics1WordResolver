# **4Pics1WordResolver**
A simple resolver to 4 pics 1 word game.Note that a English dictionary
file is needed to compare combination of characters with words existed
in the dictionary.

4 pics 1 word 手机游戏的简单解决方案。
思路如下：<br/>
**需求**：给定12个字母（可重复） ，求任意取 n 个字母(可重复)所能组成的单词，优化了n>6的算法。<br/>
**注意**：需要字典文件dicts.txt<br/>

1. 求出任意取3个字母组合的字符串
2. 任意取3个字母组合字符串去除重复
3. 取出字典中前三位字符串
4. 去除重复
5. 对比 2 和 4 的结果，取相同字符串 
6. 求出任意取n个字母组合的字符串
7. 任意取n个字母组合字符串去除重复
8. 判断 7 的结果 是否 以 5 的结果开头，并取出
9. 将8 的 结果 与字典对比，取出重复单词
