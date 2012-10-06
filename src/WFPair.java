import java.util.ArrayList;

public class WFPair {
	public ArrayList<Integer> left = new ArrayList<Integer>();
	public ArrayList<Integer> right = new ArrayList<Integer>();
	public String leftHash = "";
	public String rightHash = "";
	public WFPair parent = null;
	public WFPair(ArrayList<Integer> left, ArrayList<Integer> right){
		this.left.addAll(left);
		this.right.addAll(right);
		leftHash = calcHash(left);
		rightHash = calcHash(right);
	}
	private String calcHash(ArrayList<Integer> list){
		StringBuilder sb = new StringBuilder();
		sb.append(',');
		for(int i=0;i<list.size();i++){
			sb.append(list.get(i)).append(',');
		}
		return sb.toString();
	}
	public WFPair(int n1, int n2){
		left.add(n1);
		right.add(n2);
		leftHash = calcHash(left);
		rightHash = calcHash(right);
	}
	public WFPair leftMerge(WFPair p){
		WFPair r = new WFPair(left, WFSet.union(right, p.right));
		return r;	
	}
	
	public WFPair rightMerge(WFPair p){
		WFPair r = new WFPair(WFSet.union(left, p.left), right);
		return r;	
	}
	public ArrayList<WFPair> merge(WFPair p2){
		ArrayList<WFPair> rtn = new ArrayList<WFPair>();
		if(leftHash == p2.leftHash){
			rtn.add(leftMerge(p2));
		} else if(rightHash == p2.rightHash){
			rtn.add(rightMerge(p2));
		}
		return rtn;
	}
	
    @Override public String toString(){
		return '('+leftHash + '|' + rightHash+')';
	}
}
