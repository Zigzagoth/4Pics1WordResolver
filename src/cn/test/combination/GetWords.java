package cn.test.combination;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 // 需求：给定12个字母（可重复） ，求任意取 n(3~7)个可以组成的单词
public class GetWords {  
    
    private static File dicts = new File("C:\\Users\\Administrator\\Desktop\\test\\dicts.txt");
    private static File threeword = new File("C:\\Users\\Administrator\\Desktop\\test\\threeword.txt");
    private static File sixword = new File("C:\\Users\\Administrator\\Desktop\\test\\sixword.txt");
     
	public static void main(String[] args) throws Exception {  
		  
		getWords("xapmntfgufum4");
 
	}
	private static void getWords(String str) throws Exception {
		//1  求出任意取3个字母组合的字符串
		ArrayList<String> threeList = writeCombination(str.substring(0,str.length()-1)+"3",threeword);
		 
		//2  任意取3个字母组合字符串去除重复
		ArrayList<String> newThreeList = removeDuplicate(threeList);
		 
		ArrayList<String> shortenedDicts =  shortenDictionary(3) ;
		 
		//5   对比 2 和 4 的结果，取相同字符串
		ArrayList<String> sameStringList = getSameStrings(newThreeList,shortenedDicts);
		 
		//6  求出任意取6个字母组合的字符串
		ArrayList<String> sixList = writeCombination(str,sixword);
		 
		//7  任意取6个字母组合字符串去除重复
		//ArrayList<String> newSixList = removeDuplicate(sixList);
		  
		//8  判断 7 的结果 是否 以 5 的结果开头，并取出
		ArrayList<String> filteredList = getFilteredList(sixList,sameStringList);
		
		//9  将8 的 结果 与字典对比，取出重复单词
		ArrayList<String> dictList = getDictList();
		ArrayList<String> resultList = getResultList(filteredList,dictList);
		
		ArrayList<String> newResultList  =  removeDuplicate(resultList);
		System.out.println(newResultList);
	}
	private static ArrayList<String> getResultList(ArrayList<String> filteredList, 
			ArrayList<String> dictList) {
		ArrayList<String> resultList = new ArrayList<String>();
		
		for (int i = 0; i < filteredList.size(); i++) {
			String str = filteredList.get(i);
			if(dictList.contains(str)){
				resultList.add(str);
			}
		}
		return resultList;
	}
	private static ArrayList<String> getDictList() throws Exception {
		ArrayList<String> dictList  = new ArrayList<String>();
		 FileReader fr = new FileReader(dicts);
		 BufferedReader br = new BufferedReader(fr);
		  
		 String str= null;
		 while((str =br.readLine())!=null){
			 dictList.add(str);
		 }
		return dictList;
	}
	private static ArrayList<String> getFilteredList(ArrayList<String> newSixList, ArrayList<String> sameStringList) {
		ArrayList<String> filteredList = new ArrayList<String>();
		for (int i = 0; i < newSixList.size(); i++) {
			 for (int j = 0; j < sameStringList.size(); j++) {
				 if(newSixList.get(i).startsWith(sameStringList.get(j))){
						filteredList.add(newSixList.get(i));
					}
			}
		}
		return filteredList;
	}
	private static ArrayList<String> getSameStrings(ArrayList<String> newThreeList, ArrayList<String> shortenedDicts) {
		newThreeList.addAll(shortenedDicts);
		ArrayList<String> newList = new ArrayList<String>();
		ArrayList<String> sameStringList = new ArrayList<String>();
		for (int i = 0; i <newThreeList.size() ; i++) {
			String str = newThreeList.get(i);
			 if(!newList.contains(str)){
				 newList.add(str);
			 } else{
				 sameStringList.add(str);
			 }
		}
		return sameStringList;
	}
	private static ArrayList<String> removeDuplicate(ArrayList<String> list) {
		ArrayList<String> newList = new ArrayList<String>();
		for (int i = 0; i <list.size() ; i++) {
			String str = list.get(i);
			 if(!newList.contains(str)){
				 newList.add(str);
			 } 
		}
		return newList;
	}
		//3 取出字典中前三位字符串
	public static ArrayList<String> shortenDictionary(int len) 
			throws Exception{
		 ArrayList<String> threeDict = new ArrayList<String>();
		 ArrayList<String> shortenedDicts = new ArrayList<String>();
		 
		 FileReader fr = new FileReader(dicts);
		 BufferedReader br = new BufferedReader(fr);
		  
		 String str= null;
		 while((str =br.readLine())!=null){
			 if(str.length()>len){
				threeDict.add(str.substring(0,len));
			 }
		 }
		 //4  去除重复
		 shortenedDicts = removeDuplicate(threeDict);
				 
		return shortenedDicts;
	}
	
	private static ArrayList<String> writeCombination(String str,File file) throws Exception {
		//char a,char b,char c,char d,char e,char f,char g,char h,char j,char k,char l,char m,int x
		char[] chars =str.toCharArray();
 
		ArrayList<Character> lists = new ArrayList<Character>();
		 
		for (int i = 0; i < chars.length -1; i++) {
			lists.add(chars[i]);
		}
		 String z =String.valueOf(chars[12]);
		 int x = Integer.parseInt(z);
		 
		 
		FileWriter fw = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(fw);
		    for(List<Character> list : Permutation.of(lists, x)) { 
		    	for (int i = 0; i<list.size(); i++) {
					Character ch = list.get(i);
					bw.write(ch);
				}
		    		bw.newLine();
		    }
		   
		 bw.close();
		 fw.close();
	  
		return getList(file);
	}
	
 public static ArrayList<String> getList(File file) throws Exception{
		 
		 ArrayList<String> list  =new ArrayList<String>();
		 FileReader fr = new FileReader(file);
		 BufferedReader br = new BufferedReader(fr);
		 
		 try {
			String str= null;
			while((str =br.readLine())!=null){
				list.add(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			 if(fr!=null){
				 try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			 }
			 if(br!=null){
				 
				 try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			 }
		}
		return list;	 
	 }
}
