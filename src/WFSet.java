import java.util.ArrayList;

public class WFSet {
	public static ArrayList<Integer> union(ArrayList<Integer> set1, ArrayList<Integer> set2){
		ArrayList<Integer> rtn = new ArrayList<Integer>();
		rtn.addAll(set1);
		out_for:
		for(int i=0;i<set2.size();i++){
			int n1 = set2.get(i);
			for(int j=0;j<rtn.size();j++){
				int n2 = rtn.get(j);
				if(n1==n2){
					continue out_for;
				}else if(n1<n2){
					rtn.add(j, n1);
					continue out_for;
				}
			}
			rtn.add(n1);
		}
	
		return rtn;
	}
}
