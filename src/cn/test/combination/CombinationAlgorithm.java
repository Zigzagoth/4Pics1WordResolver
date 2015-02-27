package cn.test.combination;

import java.math.BigInteger;  

/** 
 * The strategy interface for implementing the combination algorithm. 
 * 
 * @date   07/08/2012 
 */  
public interface CombinationAlgorithm {  
    
  public int getMaxSupportedSize();  
    
  public BigInteger getCombinationCount(int numberOfElements, int numberToPick);  
    
  public void fetchCombination(Object[] source, Object[] target, BigInteger ordinal);  
}  
