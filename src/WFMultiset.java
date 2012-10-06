
public class WFMultiset {
	public String name;
	public int id;
	public int num = 0;
	/**
	 * posts 必须是已经从小到大排好序。
	 */
	public int[] posts;
	public WFMultiset(int id, int num, int[] posts){
		this(id, num, posts, "t"+id);
	}
	public WFMultiset(int id, int num){
		this(id, num, "t"+id);
	}
	public WFMultiset(int id, int num, int[] posts, String name){
		this.id = id;
		this.num = num;
		this.posts = posts;
		this.name = name;
	}
	public WFMultiset(int id, int num, String name){
		this.id = id;
		this.num = num;
		this.name = name;
		this.posts = new int[]{};
	}
	@Override public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.id).append('|').append(this.num).append('|');
		for(int i=0;i<this.posts.length;i++){
			sb.append(this.posts[i]);
			if(i<this.posts.length-1)
				sb.append(',');
		}
		return sb.toString();
	}
}
