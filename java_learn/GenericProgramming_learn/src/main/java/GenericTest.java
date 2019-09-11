import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;


public class GenericTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<String> strs=new ArrayList();
		ArrayList strs2=new ArrayList<String>();
//		ArrayList<Object> objs=new ArrayList<String>();
//		ArrayList<String> objs2=new ArrayList<Object>();
//		Vector<String> lists[]=new Vector<String>[10];
		
		Vector v1=new Vector<String>();
		Vector<Object> v=v1;
		
		HashMap<String ,Integer> maps=new HashMap<String,Integer>();
		maps.put("aaa", 111);
		maps.put("bbb", 222);
		maps.put("ccc", 333);
		
		Set<Entry<String,Integer>> mapEntrySet=maps.entrySet();
		for(Entry entry:mapEntrySet){
			System.out.println(entry.getKey()+":"+entry.getValue()); 
		}
		
		int ret1=doSomethingWithT(100,200);
		Number ret2=doSomethingWithT(100.52,100);
		Object ret3=doSomethingWithT(300,"aaa");
		
		swap(new String[]{"a","b","c"},1,2);
		//swap(new int[]{1,2,3},1,2);
	}
	
	private static <T> T doSomethingWithT(T x,T y)	{
		return y;	
	}
	
	private static <T> void swap(T[] arr,int i,int j){
		T temp=arr[i];
		arr[i]=arr[j];
		arr[j]=temp;
	}

}
